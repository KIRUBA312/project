package com.example.cdc_synchronization_engine.service.impl;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.example.cdc_synchronization_engine.constants.CacheConstants;
import com.example.cdc_synchronization_engine.service.RedisCacheService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisCacheServiceImpl
        implements RedisCacheService {

    private final CacheManager cacheManager;

    @Override
    public void clearCustomerCache() {

        if (cacheManager.getCache(CacheConstants.CUSTOMER_CACHE) != null) {

            cacheManager
                    .getCache(CacheConstants.CUSTOMER_CACHE)
                    .clear();
        }
    }

    @Override
    public void clearProductCache() {

        if (cacheManager.getCache(CacheConstants.PRODUCT_CACHE) != null) {

            cacheManager
                    .getCache(CacheConstants.PRODUCT_CACHE)
                    .clear();
        }
    }

    @Override
    public void clearInventoryCache() {

        if (cacheManager.getCache(CacheConstants.INVENTORY_CACHE) != null) {

            cacheManager
                    .getCache(CacheConstants.INVENTORY_CACHE)
                    .clear();
        }
    }

    @Override
    public void clearOrderCache() {

        if (cacheManager.getCache(CacheConstants.ORDER_CACHE) != null) {

            cacheManager
                    .getCache(CacheConstants.ORDER_CACHE)
                    .clear();
        }
    }

    @Override
    public void clearPaymentCache() {

        if (cacheManager.getCache(CacheConstants.PAYMENT_CACHE) != null) {

            cacheManager
                    .getCache(CacheConstants.PAYMENT_CACHE)
                    .clear();
        }
    }

    @Override
    public void clearAll() {

        clearCustomerCache();
        clearProductCache();
        clearInventoryCache();
        clearOrderCache();
        clearPaymentCache();
    }

}