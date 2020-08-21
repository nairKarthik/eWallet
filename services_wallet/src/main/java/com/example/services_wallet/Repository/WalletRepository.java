package com.example.services_wallet.Repository;

import com.example.services_wallet.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    @Query(value = "SELECT w FROM Wallet w WHERE w.userId = ?1")
    public Wallet findWalletByUserId(int userId);
}
