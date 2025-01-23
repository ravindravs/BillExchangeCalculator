package com.xische.Exchange.service;

import org.springframework.stereotype.Service;
import com.xische.Exchange.model.Item;
import com.xische.Exchange.model.Bill;

@Service
public class BillCalculationServiceImpl implements BillCalculationService {

    private final CurrencyExchangeService currencyExchangeService;
    private final DiscountService discountService;

    public BillCalculationServiceImpl(CurrencyExchangeService currencyExchangeService,
                                      DiscountService discountService) {
        this.currencyExchangeService = currencyExchangeService;
        this.discountService = discountService;
    }

    @Override
    public double calculateFinalAmount(Bill bill) {
        double totalAmount = bill.getItems().stream()
                .mapToDouble(item -> item.getPrice())
                .sum();

        double discount = discountService.calculateDiscount(bill);

        double amountAfterDiscount = totalAmount - discount;

        double exchangeRate = currencyExchangeService.getExchangeRate(
                bill.getOriginalCurrency(), bill.getTargetCurrency());

        return amountAfterDiscount * exchangeRate;
    }
}

