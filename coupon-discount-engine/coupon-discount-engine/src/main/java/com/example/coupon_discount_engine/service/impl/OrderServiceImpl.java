package com.example.coupon_discount_engine.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coupon_discount_engine.dto.OrderRequestDto;
import com.example.coupon_discount_engine.dto.OrderResponseDto;
import com.example.coupon_discount_engine.entity.Coupon;
import com.example.coupon_discount_engine.entity.IdempotencyRequest;
import com.example.coupon_discount_engine.entity.Order;
import com.example.coupon_discount_engine.entity.OrderDiscount;
import com.example.coupon_discount_engine.entity.User;
import com.example.coupon_discount_engine.entity.UserCoupon;
import com.example.coupon_discount_engine.enums.RequestStatus;
import com.example.coupon_discount_engine.exception.CouponAlreadyUsedException;
import com.example.coupon_discount_engine.exception.CouponExpiredException;
import com.example.coupon_discount_engine.exception.DuplicateCouponException;
import com.example.coupon_discount_engine.exception.InvalidCouponException;
import com.example.coupon_discount_engine.exception.ResourceNotFoundException;
import com.example.coupon_discount_engine.repository.CouponRepository;
import com.example.coupon_discount_engine.repository.IdempotencyRequestRepository;
import com.example.coupon_discount_engine.repository.OrderDiscountRepository;
import com.example.coupon_discount_engine.repository.OrderRepository;
import com.example.coupon_discount_engine.repository.UserCouponRepository;
import com.example.coupon_discount_engine.repository.UserRepository;
import com.example.coupon_discount_engine.service.OrderService;
import com.example.coupon_discount_engine.strategy.DiscountStrategy;
import com.example.coupon_discount_engine.strategy.DiscountStrategyFactory;
import com.example.coupon_discount_engine.util.MapperUtil;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private UserCouponRepository userCouponRepository;
	@Autowired
	private OrderDiscountRepository orderDiscountRepository;
	@Autowired
	private IdempotencyRequestRepository idempotencyRequestRepository;
	@Autowired
	private DiscountStrategyFactory strategyFactory;
	@Autowired
	private MapperUtil mapperUtil;
	@Override
	public OrderResponseDto createOrder(@Valid OrderRequestDto request) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(request.getUserId()).orElseThrow(() ->
		new ResourceNotFoundException("User not found"));
		
		Order order = new Order();
		order.setUser(user);
		order.setOrderNumber(request.getOrderNumber());
		order.setTotalAmount(request.getTotalAmount());
		order.setFinalAmount(request.getTotalAmount());
		order.setOrderStatus(request.getOrderStatus());
		order.setProductCategory(request.getProductCategory());
		order.setIdempotencyKey(request.getIdempotencyKey());
		order.setCreatedAt(LocalDateTime.now());
		order.setUpdatedAt(LocalDateTime.now());
		
		Order savedOrder = orderRepository.save(order);
		return mapperUtil.toOrderResponse(savedOrder);
	}
	@Override
	public OrderResponseDto getOrderById(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Order not found with id : "+id));
		return mapperUtil.toOrderResponse(order);
	}
	@Override
	public List<OrderResponseDto> getAllOrders() {
		// TODO Auto-generated method stub
		List<Order> orders = orderRepository.findAll();
		
		return orders.stream().map(MapperUtil::toOrderResponse)
				.collect(Collectors.toList());
		
	}
	@Override
	public OrderResponseDto updateOrder(Long id, 
			@Valid OrderRequestDto request) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Order not found with id : "+id));
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() ->
				new ResourceNotFoundException("User not found with id : "
						+request.getUserId()));
		order.setUser(user);
		order.setOrderNumber(request.getOrderNumber());
		order.setTotalAmount(request.getTotalAmount());
		order.setFinalAmount(request.getTotalAmount());
		order.setProductCategory(request.getProductCategory());
		order.setIdempotencyKey(request.getIdempotencyKey());
		order.setOrderStatus(request.getOrderStatus());
		order.setUpdatedAt(LocalDateTime.now());
		Order updatedOrder = orderRepository.save(order);
		return MapperUtil.toOrderResponse(updatedOrder);
	}
	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Order not found with id : "+id));
		
		orderRepository.delete(order);
		
	}
	@Override
	public OrderResponseDto applyCoupons(Long orderId, 
			List<String> couponCodes) {
		Order order = orderRepository.findById(orderId).orElseThrow(() ->
		new ResourceNotFoundException("Order not found with id : "+orderId));
		
		BigDecimal totalDiscount = BigDecimal.ZERO;
		// TODO Auto-generated method stub
		for(String code : couponCodes) {
			Coupon coupon = couponRepository.findByCode(code).orElseThrow(
					() -> new ResourceNotFoundException("Coupon not found : "+code));
			if (!coupon.getActive()) {
				throw new InvalidCouponException("Coupon is inactive");
			}
			if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
				throw new CouponExpiredException("Coupon expired");
			}
			if (coupon.getUsedCount()>=coupon.getUsageLimit()) {
				throw new InvalidCouponException("Coupon usage limit exceeded");
			}
			if (order.getTotalAmount().compareTo(coupon.getMinimumOrderAmount())<0) {
				throw new InvalidCouponException("Minimum order amount not satisfied");
			}
			if(coupon.getProductCategory()!=null && !coupon.getProductCategory()
					.equalsIgnoreCase(order.getProductCategory())) {
				throw new InvalidCouponException("Coupon not applicable for this category");
			}
			
			if (!coupon.getReusable()) {
				UserCoupon userCoupon = userCouponRepository
						.findByUserAndCoupon(order.getUser(), coupon)
						.orElse(null);
				if (userCoupon != null && Boolean.TRUE.equals(userCoupon
						.getUsedFlag())) {
					throw new CouponAlreadyUsedException("Coupon already used");
				}
			}
			DiscountStrategy strategy = strategyFactory.getStrategy(coupon.getType());
			BigDecimal discount = strategy.applyDiscount( coupon,order.getTotalAmount());
			totalDiscount = totalDiscount.add(discount);
			OrderDiscount orderDiscount = new OrderDiscount();
			orderDiscount.setOrder(order);
			orderDiscount.setCoupon(coupon);
			orderDiscount.setDiscountApplied(discount);
			orderDiscountRepository.save(orderDiscount);
			
			coupon.setUsedCount(coupon.getUsedCount()+1);
			couponRepository.save(coupon);
			
			UserCoupon userCoupon = userCouponRepository.findByUserAndCoupon(order.getUser(), coupon)
					.orElse(new UserCoupon());
			userCoupon.setUser(order.getUser());
			userCoupon.setCoupon(coupon);
			userCoupon.setUsedFlag(true);
			userCoupon.setUsedAt(LocalDateTime.now());
			userCouponRepository.save(userCoupon);
		}
			
		BigDecimal finalAmount = order.getTotalAmount().subtract(totalDiscount);
		if (finalAmount.compareTo(BigDecimal.ZERO)<0) {
				finalAmount = BigDecimal.ZERO;
		}
		order.setFinalAmount(finalAmount);
		orderRepository.save(order);
			
		return mapperUtil.toOrderResponse(order);
	}
	
//	private	void validateIdempotency(String idempotencyKey) {
//		if (idempotencyRequestRepository.existsByIdempotencyKey(idempotencyKey)) {
//			throw new DuplicateCouponException("Duplicate request."
//					+ "Idempotency Key already exists");
//			
//		}
//	}
//	
//	private void saveIdempotency(OrderRequestDto request,OrderResponseDto response) {
//		IdempotencyRequest entity = new IdempotencyRequest();
//		entity.setIdempotencyKey(request.getIdempotencyKey());
//		entity.setRequesthash(generateRequestHash(request));
//		entity.setResponseData(response.toString());
//		entity.setStatus(RequestStatus.SUCCESS);
//		idempotencyRequestRepository.save(entity);
//	}
//	private String generateRequestHash(OrderRequestDto request) {
//		// TODO Auto-generated method stub
//		return request.getUserId()+"-"+request.getOrderNumber()+"-"+
//				request.getTotalAmount()+"-"+request.getProductCategory();
//	}
//	private void validateCoupon(Coupon coupon, Order order) {
//
//	    if (!coupon.getActive()) {
//	        throw new InvalidCouponException("Coupon is inactive.");
//	    }
//
//	    if (coupon.getExpiryDate().isBefore(LocalDate.now())) {
//	        throw new CouponExpiredException("Coupon has expired.");
//	    }
//
//	    if (coupon.getUsedCount() >= coupon.getUsageLimit()) {
//	        throw new InvalidCouponException("Coupon usage limit exceeded.");
//	    }
//
//	    if (order.getTotalAmount()
//	            .compareTo(coupon.getMinimumOrderAmount()) < 0) {
//
//	        throw new InvalidCouponException(
//	                "Minimum order amount not satisfied.");
//	    }
//
//	    if (coupon.getProductCategory() != null &&
//	            !coupon.getProductCategory()
//	            .equalsIgnoreCase(order.getProductCategory())) {
//
//	        throw new InvalidCouponException(
//	                "Coupon is not applicable for this product category.");
//	    }
//
//	}
//	private void validateUserCoupon(User user, Coupon coupon) {
//
//	    if (!coupon.getReusable()) {
//
//	        UserCoupon userCoupon = userCouponRepository
//	                .findByUserAndCoupon(user, coupon)
//	                .orElse(null);
//
//	        if (userCoupon != null &&
//	                Boolean.TRUE.equals(userCoupon.getUsedFlag())) {
//
//	            throw new CouponAlreadyUsedException(
//	                    "Coupon already used by this user.");
//	        }
//
//	    }
//
//	}
//	private void updateCouponUsage(Coupon coupon) {
//		coupon.setUsedCount(coupon.getUsedCount()+1);
//		couponRepository.save(coupon);
//	}
//	private void saveUserCoupon(User user, Coupon coupon) {
//
//	    UserCoupon userCoupon = userCouponRepository
//	            .findByUserAndCoupon(user, coupon)
//	            .orElse(new UserCoupon());
//
//	    userCoupon.setUser(user);
//
//	    userCoupon.setCoupon(coupon);
//
//	    userCoupon.setUsedFlag(true);
//
//	    userCoupon.setUsedAt(LocalDateTime.now());
//
//	    userCouponRepository.save(userCoupon);
//
//	}
//	private void saveOrderDiscount(Order order,
//            Coupon coupon,
//            BigDecimal discount) {
//
//		OrderDiscount orderDiscount = new OrderDiscount();
//		
//		orderDiscount.setOrder(order);
//		
//		orderDiscount.setCoupon(coupon);
//		
//		orderDiscount.setDiscountApplied(discount);
//		
//		orderDiscountRepository.save(orderDiscount);
//		
//		}
//	
//	private void calculateFinalAmount(Order order,
//            BigDecimal totalDiscount) {
//
//		BigDecimal finalAmount =
//		order.getTotalAmount().subtract(totalDiscount);
//		
//		if (finalAmount.compareTo(BigDecimal.ZERO) < 0) {
//		finalAmount = BigDecimal.ZERO;
//		}
//		
//		order.setFinalAmount(finalAmount);
//		
//		}
	
	
}
