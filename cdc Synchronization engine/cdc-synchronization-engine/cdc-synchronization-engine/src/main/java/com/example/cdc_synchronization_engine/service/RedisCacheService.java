package com.example.cdc_synchronization_engine.service;

public interface RedisCacheService {

    void clearCustomerCache();

    void clearProductCache();

    void clearInventoryCache();

    void clearOrderCache();

    void clearPaymentCache();

    void clearAll();

}