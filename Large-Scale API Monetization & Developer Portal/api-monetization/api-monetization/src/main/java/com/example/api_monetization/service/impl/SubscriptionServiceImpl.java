package com.example.api_monetization.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api_monetization.dto.subscription.DeveloperSubscriptionRequest;
import com.example.api_monetization.dto.subscription.DeveloperSubscriptionResponse;
import com.example.api_monetization.dto.subscription.QuotaResponse;
import com.example.api_monetization.dto.subscription.SubscriptionHistoryResponse;
import com.example.api_monetization.dto.subscription.SubscriptionPlanRequest;
import com.example.api_monetization.dto.subscription.SubscriptionPlanResponse;
import com.example.api_monetization.entity.ConsumerApplication;
import com.example.api_monetization.entity.DeveloperProfile;
import com.example.api_monetization.entity.DeveloperSubscription;
import com.example.api_monetization.entity.QuotaLimit;
import com.example.api_monetization.entity.QuotaUsage;
import com.example.api_monetization.entity.SubscriptionHistory;
import com.example.api_monetization.entity.SubscriptionPlan;
import com.example.api_monetization.enums.ActionType;
import com.example.api_monetization.enums.BillingCycle;
import com.example.api_monetization.enums.SubscriptionStatus;
import com.example.api_monetization.exception.ResourceAlreadyExistsException;
import com.example.api_monetization.exception.ResourceNotFoundException;
import com.example.api_monetization.mapper.QuotaMapper;
import com.example.api_monetization.mapper.SubscriptionHistoryMapper;
import com.example.api_monetization.mapper.SubscriptionMapper;
import com.example.api_monetization.repository.ConsumerApplicationRepository;
import com.example.api_monetization.repository.DeveloperProfileRepository;
import com.example.api_monetization.repository.DeveloperSubscriptionRepository;
import com.example.api_monetization.repository.QuotaLimitRepository;
import com.example.api_monetization.repository.QuotaUsageRepository;
import com.example.api_monetization.repository.SubscriptionHistoryRepository;
import com.example.api_monetization.repository.SubscriptionPlanRepository;
import com.example.api_monetization.repository.UserRepository;
import com.example.api_monetization.service.SubscriptionService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService{

	@Autowired
	private SubscriptionPlanRepository subscriptionPlanRepository;
	@Autowired
	private DeveloperSubscriptionRepository developerSubscriptionRepository;
	@Autowired
	private SubscriptionHistoryRepository subscriptionHistoryRepository;
	@Autowired
	private QuotaUsageRepository quotaUsageRepository;
	@Autowired
	private DeveloperProfileRepository developerProfileRepository;
	@Autowired
	private ConsumerApplicationRepository consumerApplicationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SubscriptionMapper subscriptionMapper;
	@Autowired
	private SubscriptionHistoryMapper subscriptionHistoryMapper;
	@Autowired
	private QuotaLimitRepository quotaLimitRepository;
	@Autowired
	private QuotaMapper quotaMapper;
	@Override
	public SubscriptionPlanResponse createPlan(SubscriptionPlanRequest request) {
		// TODO Auto-generated method stub
		if (subscriptionPlanRepository
                .existsByPlanNameIgnoreCase(request.getPlanName())) {

            throw new ResourceAlreadyExistsException(
                    "Subscription Plan already exists.");
        }

        SubscriptionPlan plan =
                subscriptionMapper.toPlanEntity(request);

        SubscriptionPlan saved =
                subscriptionPlanRepository.save(plan);

        return subscriptionMapper.toPlanResponse(saved);
	}
	@Override
	public SubscriptionPlanResponse updatePlan(Long planId, SubscriptionPlanRequest request) {
		// TODO Auto-generated method stub
		SubscriptionPlan plan =
                subscriptionPlanRepository.findById(planId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subscription Plan not found."));

        subscriptionMapper.updatePlan(request, plan);

        SubscriptionPlan updated =
                subscriptionPlanRepository.save(plan);

        return subscriptionMapper.toPlanResponse(updated);
	}
	@Override
	public SubscriptionPlanResponse getPlan(Long planId) {
		// TODO Auto-generated method stub
		SubscriptionPlan plan =
                subscriptionPlanRepository.findById(planId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subscription Plan not found."));

        return subscriptionMapper.toPlanResponse(plan);
	}
	@Override
	public List<SubscriptionPlanResponse> getPlans() {
		// TODO Auto-generated method stub
		return subscriptionPlanRepository.findAll().stream()
				.map(subscriptionMapper::toPlanResponse)
				.collect(Collectors.toList());
	}
	@Override
	public DeveloperSubscriptionResponse subscribe(
	        DeveloperSubscriptionRequest request) {

	    DeveloperProfile developer =
	            developerProfileRepository.findById(request.getDeveloperId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Developer not found"));

	    ConsumerApplication application =
	            consumerApplicationRepository.findById(
	                    request.getApplicationId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Application not found"));

	    SubscriptionPlan plan =
	            subscriptionPlanRepository.findById(request.getPlanId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Subscription Plan not found"));

	    if (developerSubscriptionRepository
	            .existsByApplicationId(application.getId())) {

	        throw new ResourceAlreadyExistsException(
	                "Application already subscribed.");
	    }

	    DeveloperSubscription subscription =
	            subscriptionMapper.toSubscriptionEntity(request);

	    subscription.setDeveloper(developer);

	    subscription.setApplication(application);

	    subscription.setPlan(plan);

	    subscription.setSubscriptionStatus(
	            SubscriptionStatus.ACTIVE);

	    subscription.setStartDate(LocalDate.now());

	    if (subscription.getBillingCycle() == null) {

	        subscription.setBillingCycle(
	                BillingCycle.MONTHLY);
	    }

	    if (subscription.getBillingCycle()
	            == BillingCycle.MONTHLY) {

	        subscription.setNextBillingDate(
	                LocalDate.now().plusMonths(1));

	    } else {

	        subscription.setNextBillingDate(
	                LocalDate.now().plusYears(1));
	    }

	    DeveloperSubscription saved =
	            developerSubscriptionRepository.save(subscription);

	    createSubscriptionHistory(saved);

	    createQuotaLimit(saved);

	    createQuotaUsage(saved);

	    return subscriptionMapper.toSubscriptionResponse(saved);
	}
	private void createSubscriptionHistory(
	        DeveloperSubscription subscription) {

	    SubscriptionHistory history =
	            new SubscriptionHistory();

	    history.setSubscription(subscription);

	    history.setPreviousPlan(null);

	    history.setNewPlan(subscription.getPlan());

	    history.setActionType(ActionType.SUBSCRIBED);

	    history.setRemarks("New Subscription");

	    subscriptionHistoryRepository.save(history);
	}
	private void createQuotaLimit(
	        DeveloperSubscription subscription) {

	    SubscriptionPlan plan = subscription.getPlan();

	    QuotaLimit quota = new QuotaLimit();

	    quota.setSubscription(subscription);
	    quota.setDailyLimit(plan.getRequestLimit() / 30);
	    quota.setWeeklyLimit(plan.getRequestLimit() / 4);
	    quota.setMonthlyLimit(plan.getRequestLimit());
	    quota.setYearlyLimit(plan.getRequestLimit() * 12);
	    quota.setConcurrentRequests(
	            plan.getBurstLimit());
	    quota.setRequestsPerMinute(
	            plan.getBurstLimit());
	    quota.setRequestsPerSecond(
	            Math.max(1,
	                    plan.getBurstLimit() / 60));

	    quotaLimitRepository.save(quota);
	}
	private void createQuotaUsage(
	        DeveloperSubscription subscription) {

	    QuotaUsage usage = new QuotaUsage();
	    usage.setSubscription(subscription);
	    usage.setUsageDate(LocalDate.now());
	    usage.setDailyRequests(0L);
	    usage.setWeeklyRequests(0L);
	    usage.setMonthlyRequests(0L);
	    usage.setYearlyRequests(0L);
	    usage.setOverageRequests(0L);
	    usage.setTotalCost(BigDecimal.ZERO);

	    quotaUsageRepository.save(usage);
	}
	@Override
	public DeveloperSubscriptionResponse updateSubscription(
	        Long subscriptionId,
	        DeveloperSubscriptionRequest request) {

	    DeveloperSubscription subscription =
	            developerSubscriptionRepository.findById(subscriptionId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Subscription not found."));

	    SubscriptionPlan previousPlan =
	            subscription.getPlan();

	    SubscriptionPlan newPlan =
	            subscriptionPlanRepository.findById(request.getPlanId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Subscription Plan not found."));

	    subscription.setPlan(newPlan);

	    subscription.setBillingCycle(request.getBillingCycle());

	    subscription.setAutoRenew(request.getAutoRenew());

	    subscription.setEndDate(request.getEndDate());

	    if (request.getBillingCycle() == BillingCycle.MONTHLY) {

	        subscription.setNextBillingDate(
	                LocalDate.now().plusMonths(1));

	    } else {

	        subscription.setNextBillingDate(
	                LocalDate.now().plusYears(1));
	    }

	    DeveloperSubscription updated =
	            developerSubscriptionRepository.save(subscription);

	    createSubscriptionHistory(
	            updated,
	            previousPlan,
	            newPlan);

	    updateQuotaLimit(updated);

	    return subscriptionMapper.toSubscriptionResponse(updated);
	}
	private void createSubscriptionHistory(
	        DeveloperSubscription subscription,
	        SubscriptionPlan previousPlan,
	        SubscriptionPlan newPlan) {

	    SubscriptionHistory history =
	            new SubscriptionHistory();

	    history.setSubscription(subscription);

	    history.setPreviousPlan(previousPlan);

	    history.setNewPlan(newPlan);

	    if (previousPlan.getId().equals(newPlan.getId())) {

	        history.setActionType(ActionType.RENEWED);

	        history.setRemarks("Subscription renewed");

	    } else if (previousPlan.getMonthlyPrice()
	            .compareTo(newPlan.getMonthlyPrice()) < 0) {

	        history.setActionType(ActionType.UPGRADED);

	        history.setRemarks("Subscription upgraded");

	    } else {

	        history.setActionType(ActionType.DOWNGRADED);

	        history.setRemarks("Subscription downgraded");
	    }

	    subscriptionHistoryRepository.save(history);
	}
	private void updateQuotaLimit(
	        DeveloperSubscription subscription) {

	    QuotaLimit quota =
	            quotaLimitRepository.findBySubscriptionId(
	                    subscription.getId())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Quota Limit not found."));

	    SubscriptionPlan plan =
	            subscription.getPlan();

	    quota.setDailyLimit(plan.getRequestLimit() / 30);

	    quota.setWeeklyLimit(plan.getRequestLimit() / 4);

	    quota.setMonthlyLimit(plan.getRequestLimit());

	    quota.setYearlyLimit(plan.getRequestLimit() * 12);

	    quota.setConcurrentRequests(
	            plan.getBurstLimit());

	    quota.setRequestsPerMinute(
	            plan.getBurstLimit());

	    quota.setRequestsPerSecond(
	            Math.max(1,
	                    plan.getBurstLimit() / 60));

	    quotaLimitRepository.save(quota);
	}
	@Override
	public DeveloperSubscriptionResponse getSubscription(Long subscriptionId) {
		// TODO Auto-generated method stub
		DeveloperSubscription subscription =
	            developerSubscriptionRepository.findById(subscriptionId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Subscription not found."));

	    return subscriptionMapper
	    		.toSubscriptionResponse(subscription);
	}
	@Override
	public List<DeveloperSubscriptionResponse> getDeveloperSubscriptions(Long developerId) {
		// TODO Auto-generated method stub
	    if (!developerProfileRepository.existsById(developerId)) {

	        throw new ResourceNotFoundException(
	                "Developer not found.");
	    }

	    return developerSubscriptionRepository
	            .findByDeveloperId(developerId)
	            .stream()
	            .map(subscriptionMapper::toSubscriptionResponse)
	            .toList();
	}
	@Override
	public List<SubscriptionHistoryResponse> getHistory(Long subscriptionId) {
		// TODO Auto-generated method stub
		  if (!developerSubscriptionRepository.existsById(subscriptionId)) {

		        throw new ResourceNotFoundException(
		                "Subscription not found.");
		    }

		    return subscriptionHistoryRepository
		            .findBySubscriptionId(subscriptionId)
		            .stream()
		            .map(subscriptionHistoryMapper::toResponse)
		            .toList();
	}
	@Override
	public QuotaResponse getQuotaUsage(Long subscriptionId) {
		// TODO Auto-generated method stub
		  if (!developerSubscriptionRepository.existsById(subscriptionId)) {

		        throw new ResourceNotFoundException(
		                "Subscription not found.");
		    }

		    QuotaUsage usage =
		            quotaUsageRepository
		            .findFirstBySubscriptionIdOrderByUsageDateDesc(subscriptionId)
		            .orElseThrow(() ->
		                    new ResourceNotFoundException(
		                            "Quota usage not found."));

		    return quotaMapper.toResponse(usage);
	}
	
}
