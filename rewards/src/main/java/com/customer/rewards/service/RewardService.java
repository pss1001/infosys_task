package com.customer.rewards.service;

import com.customer.rewards.execption.ResourceNotFoundException;
import com.customer.rewards.model.RewardResponse;
import com.customer.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import com.customer.rewards.model.Transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardService {

    private final TransactionRepository transactionRepo;

    public RewardService(TransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    public RewardResponse calculateRewards(String customerId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionRepo.getTransactionsByCustomerAndDateRange(customerId, startDate, endDate);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for customer " + customerId);
        }

        Map<Month, Integer> monthlyPoints = new LinkedHashMap<>();
        int totalPoints = 0;

        for (Transaction t : transactions) {
            int points = calculatePointsForTransaction(t.getAmount());
            monthlyPoints.merge(t.getDate().getMonth(), points, Integer::sum);
            totalPoints += points;
        }

        return new RewardResponse(customerId, monthlyPoints, totalPoints, transactions);
    }

    private int calculatePointsForTransaction(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points += 50; // 1 point for $50–$100
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }
        return points;
    }
}