package com.xische.Exchange.service;

public interface CurrencyExchangeService {
    double getExchangeRate(String baseCurrency, String targetCurrency);
}

