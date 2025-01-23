package com.xische.Exchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private List<Item> items;
    private String userType;
    private int tenure;
    private String originalCurrency;
    private String targetCurrency;
}
