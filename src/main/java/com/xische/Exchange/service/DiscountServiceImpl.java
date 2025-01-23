package com.xische.Exchange.service;

import com.xische.Exchange.model.Item;
import com.xische.Exchange.model.Bill;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Override
    public double calculateDiscount(Bill bill) {
        double totalAmount = bill.getItems().stream()
                .mapToDouble(Item::getPrice).sum();

        double percentageDiscount = 0.0;

        // Apply percentage-based discounts
        if ("employee".equalsIgnoreCase(bill.getUserType())) {
            percentageDiscount = 0.30;
        } else if ("affiliate".equalsIgnoreCase(bill.getUserType())) {
            percentageDiscount = 0.10;
        } else if ("customer".equalsIgnoreCase(bill.getUserType()) && bill.getTenure() > 2) {
            percentageDiscount = 0.05;
        }

        double percentageDiscountAmount = bill.getItems().stream()
                .filter(item -> !"groceries".equalsIgnoreCase(item.getCategory()))
                .mapToDouble(Item::getPrice)
                .sum() * percentageDiscount;

        // Apply $5 discount per $100 on the bill
        double fixedDiscount = Math.floor(totalAmount / 100) * 5;

        return percentageDiscountAmount + fixedDiscount;
    }
}
