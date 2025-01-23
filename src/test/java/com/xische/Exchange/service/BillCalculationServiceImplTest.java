package com.xische.Exchange.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.xische.Exchange.model.*;
import org.mockito.MockitoAnnotations;

public class BillCalculationServiceImplTest {

    @Mock
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private BillCalculationServiceImpl billCalculationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateFinalAmount() {
        // Mock bill details
        Bill bill = new Bill();
        bill.setUserType("employee");
        bill.setOriginalCurrency("USD");
        bill.setTargetCurrency("INR");
        bill.setItems(Arrays.asList(
                new Item("Laptop", "electronics", 1000),
                new Item("Headphones", "electronics", 200),
                new Item("Apples", "groceries", 50)
        ));

        // Mock dependencies
        when(discountService.calculateDiscount(bill)).thenReturn(422.5); // $360 + $62.5
        when(currencyExchangeService.getExchangeRate("USD", "INR")).thenReturn(86.5113);

        // Test
        double finalAmount = billCalculationService.calculateFinalAmount(bill);

        // Verify: (Total = $827.5 after discount) * 86.5113
        assertEquals(71588.10075, finalAmount);
        verify(discountService, times(1)).calculateDiscount(bill);
        verify(currencyExchangeService, times(1)).getExchangeRate("USD", "INR");
    }
}
