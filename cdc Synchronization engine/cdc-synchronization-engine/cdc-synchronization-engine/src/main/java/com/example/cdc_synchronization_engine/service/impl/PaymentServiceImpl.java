package com.example.cdc_synchronization_engine.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cdc_synchronization_engine.dto.PaymentRequest;
import com.example.cdc_synchronization_engine.dto.PaymentResponse;
import com.example.cdc_synchronization_engine.entity.Payment;
import com.example.cdc_synchronization_engine.exception.ErrorCode;
import com.example.cdc_synchronization_engine.exception.ResourceAlreadyExistsException;
import com.example.cdc_synchronization_engine.exception.ResourceNotFoundException;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.mapper.PaymentMapper;
import com.example.cdc_synchronization_engine.repository.OrderRepository;
import com.example.cdc_synchronization_engine.repository.PaymentRepository;
import com.example.cdc_synchronization_engine.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    private final PaymentMapper paymentMapper;

    private final CDCEventProducer cdcEventProducer;

    @Override
    @CacheEvict(value = "payments", allEntries = true)
    public PaymentResponse createPayment(PaymentRequest request) {

        if (!orderRepository.existsById(request.getOrderId())) {

            throw new ResourceNotFoundException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    "Order not found with ID: " + request.getOrderId()
            );
        }

        if (request.getPaymentReference() != null
                && !request.getPaymentReference().isBlank()) {

            if (paymentRepository.existsByPaymentReference(
                    request.getPaymentReference())) {

                throw new ResourceAlreadyExistsException(
                        ErrorCode.RESOURCE_ALREADY_EXISTS,
                        "Payment already exists with reference: "
                                + request.getPaymentReference()
                );
            }
        }

        Payment payment = paymentMapper.toEntity(request);

        Payment savedPayment = paymentRepository.save(payment);

        PaymentResponse response =
                paymentMapper.toResponse(savedPayment);

        cdcEventProducer.publishEvent(
                "payments-events",
                "PAYMENT",
                savedPayment.getId(),
                "CREATE",
                response
        );

        return response;
    }

    @Override
    @CacheEvict(value = "payments", allEntries = true)
    public PaymentResponse updatePaymentStatus(
            Long id,
            String status) {

        Payment payment = findPaymentById(id);

        if (status == null || status.isBlank()) {

            throw new IllegalArgumentException(
                    "Payment status cannot be empty"
            );
        }

        if ("SUCCESS".equalsIgnoreCase(
                payment.getPaymentStatus())) {

            throw new IllegalArgumentException(
                    "Successful payment status cannot be changed"
            );
        }

        payment.setPaymentStatus(status);

        Payment updatedPayment =
                paymentRepository.save(payment);

        PaymentResponse response =
                paymentMapper.toResponse(updatedPayment);

        cdcEventProducer.publishEvent(
                "payments-events",
                "PAYMENT",
                updatedPayment.getId(),
                "UPDATE",
                response
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "payments", key = "#id")
    public PaymentResponse getPayment(Long id) {

        Payment payment = findPaymentById(id);

        return paymentMapper.toResponse(payment);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("payments")
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    private Payment findPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "Payment not found with ID: " + id
                        )
                );
    }
}