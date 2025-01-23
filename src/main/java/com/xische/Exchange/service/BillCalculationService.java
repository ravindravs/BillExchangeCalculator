package com.xische.Exchange.service;

import com.xische.Exchange.model.Bill;

public interface BillCalculationService {
    double calculateFinalAmount(Bill bill);
}
