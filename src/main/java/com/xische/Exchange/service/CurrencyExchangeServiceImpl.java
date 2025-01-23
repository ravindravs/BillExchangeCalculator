package com.xische.Exchange.service;

import com.xische.Exchange.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    @Value("${exchange.api.key}")
    private String apiKey;

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    @Override
    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        // Construct the API URL
        String url = String.format("%s/%s/latest/%s", baseUrl, apiKey, baseCurrency);

        RestTemplate restTemplate = new RestTemplate();
        try {
            // Log the API URL for debugging
            System.out.println("Requesting exchange rates from: " + url);

            // Make the API call
            ExchangeResponse response = restTemplate.getForObject(url, ExchangeResponse.class);

            // Check and return the exchange rate for the target currency
            if (response != null && response.getConversionRates() != null) {
                return response.getConversionRates().getOrDefault(targetCurrency, 1.0);
            } else {
                throw new RuntimeException("Invalid response or missing conversion rates");
            }
        } catch (Exception e) {
            // Log the error for debugging
            System.err.println("Error while fetching exchange rates: " + e.getMessage());
            throw new RuntimeException("Unable to fetch exchange rate from the API", e);
        }
    }
}
