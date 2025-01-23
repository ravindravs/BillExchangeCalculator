package com.xische.Exchange.service;


import com.xische.Exchange.model.Bill;

public interface DiscountService {
    double calculateDiscount(Bill bill);
}

