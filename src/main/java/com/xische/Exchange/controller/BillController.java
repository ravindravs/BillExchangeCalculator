package com.xische.Exchange.controller;

import com.xische.Exchange.model.Bill;
import com.xische.Exchange.service.BillCalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BillController {

    private final BillCalculationService billCalculationService;

    public BillController(BillCalculationService billCalculationService) {
        this.billCalculationService = billCalculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate(@RequestBody Bill bill) {
        double finalAmount = billCalculationService.calculateFinalAmount(bill);
        return ResponseEntity.ok(finalAmount);
    }
}

