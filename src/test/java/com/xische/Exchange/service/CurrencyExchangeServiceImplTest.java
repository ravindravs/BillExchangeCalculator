package com.xische.Exchange.service;

import com.xische.Exchange.model.ExchangeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.*;

@SpringJUnitConfig
public class CurrencyExchangeServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyExchangeServiceImpl currencyExchangeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetExchangeRate() {
        // Mock the API response
        ExchangeResponse mockResponse = new ExchangeResponse();
        Map<String, Double> conversionRates = new HashMap<>();
        conversionRates.put("INR", 86.5113);
        mockResponse.setConversionRates(conversionRates);

        String baseCurrency = "USD";
        String targetCurrency = "INR";
        String url = "https://v6.exchangerate-api.com/v6/904491c8383697f00b18e86a/latest/USD";

        when(restTemplate.getForObject(url, ExchangeResponse.class)).thenReturn(mockResponse);

        // Test
        double exchangeRate = currencyExchangeService.getExchangeRate(baseCurrency, targetCurrency);

        // Verify
        assertEquals(86.5113, exchangeRate);
        verify(restTemplate, times(1)).getForObject(url, ExchangeResponse.class);
    }
}

