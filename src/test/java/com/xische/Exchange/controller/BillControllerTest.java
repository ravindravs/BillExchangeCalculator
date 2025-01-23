package com.xische.Exchange.controller;

import com.xische.Exchange.model.Bill;
import com.xische.Exchange.model.Item;
import com.xische.Exchange.service.BillCalculationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillControllerTest {

    @Mock
    private BillCalculationService billCalculationService; // Mock dependency

    @InjectMocks
    private BillController billController; // Inject mocked dependency into controller

    public BillControllerTest() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testCalculate() {
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

        // Mock service response
        when(billCalculationService.calculateFinalAmount(bill)).thenReturn(71624.47);

        // Call the controller method
        ResponseEntity<Double> response = billController.calculate(bill);

        // Verify the response
        assertEquals(71624.47, response.getBody());
        verify(billCalculationService, times(1)).calculateFinalAmount(bill);
    }
}
