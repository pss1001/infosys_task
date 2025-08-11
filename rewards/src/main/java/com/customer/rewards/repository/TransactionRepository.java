package com.customer.rewards.repository;

import org.springframework.stereotype.Repository;
import com.customer.rewards.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    //Mock Data for Testing
    private final List<Transaction> transactions = List.of(
            new Transaction("Transaction_001", "Customer_001", 120, LocalDate.of(2025, 5, 10)),
            new Transaction("Transaction_002", "Customer_001", 75, LocalDate.of(2025, 5, 15)),
            new Transaction("Transaction_003", "Customer_001", 200, LocalDate.of(2025, 6, 5)),
            new Transaction("Transaction_004", "Customer_003", 90, LocalDate.of(2025, 6, 8)),
            new Transaction("Transaction_005", "Customer_005", 120, LocalDate.of(2025, 7, 1)),
            new Transaction("Transaction_001", "Customer_002", 120, LocalDate.of(2025, 5, 10)),
            new Transaction("Transaction_002", "Customer_003", 75, LocalDate.of(2025, 5, 15)),
            new Transaction("Transaction_003", "Customer_002", 200, LocalDate.of(2025, 6, 5)),
            new Transaction("Transaction_004", "Customer_004", 90, LocalDate.of(2025, 6, 8)),
            new Transaction("Transaction_005", "Customer_004", 50, LocalDate.of(2025, 7, 1))

            );

    public List<Transaction> getTransactionsByCustomerAndDateRange(String customerId, LocalDate start, LocalDate end) {
        return transactions.stream()
                .filter(t -> t.getCustomerId().equals(customerId)
                        && (t.getDate().isEqual(start) || t.getDate().isAfter(start))
                        && (t.getDate().isEqual(end) || t.getDate().isBefore(end)))
                .collect(Collectors.toList());
    }
}