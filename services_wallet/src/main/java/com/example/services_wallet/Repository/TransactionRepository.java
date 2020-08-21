package com.example.services_wallet.Repository;

import com.example.services_wallet.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT t FROM Transaction t WHERE t.sendersId = ?1 OR t.receiversId = ?1")
    public List<Transaction> findBySidAndRid(int id);
}
