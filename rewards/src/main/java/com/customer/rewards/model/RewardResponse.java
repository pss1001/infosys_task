package com.customer.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Month;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RewardResponse {
    private String customerId;
    private Map<Month, Integer> monthlyPoints;
    private int totalPoints;
    private List<Transaction> transactions;

}
