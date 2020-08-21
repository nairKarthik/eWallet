package com.example.services_wallet.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int walId;
    private int userId;
    private int walBal;
    private boolean isActive;
    private String walType;

    public Wallet() {
    }

    public Wallet(int walId, int userId, int walBal, boolean isActive, String walType) {
        this.walId = walId;
        this.userId = userId;
        this.walBal = walBal;
        this.isActive = isActive;
        this.walType = walType;
    }

    public int getWalId() {
        return walId;
    }

    public void setWalId(int walId) {
        this.walId = walId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWalBal() {
        return walBal;
    }

    public void setWalBal(int walBal) {
        this.walBal = walBal;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getWalType() {
        return walType;
    }

    public void setWalType(String walType) {
        this.walType = walType;
    }
}
