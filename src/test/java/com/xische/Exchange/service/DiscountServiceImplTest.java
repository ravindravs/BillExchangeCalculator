package com.xische.Exchange.service;

import com.xische.Exchange.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceImplTest {

    @InjectMocks
    private DiscountServiceImpl discountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateDiscount() {
        // Mock bill details
        Bill bill = new Bill();
        bill.setUserType("employee");
        bill.setItems(Arrays.asList(
                new Item("Laptop", "electronics", 1000),
                new Item("Headphones", "electronics", 200),
                new Item("Apples", "groceries", 50)
        ));

        double discount = discountService.calculateDiscount(bill);

        // Verify: 30% discount on non-grocery items ($1200 * 0.30) + flat discount ($5 per $100 on $1250)
        assertEquals(360 + 60, discount);
    }
}

