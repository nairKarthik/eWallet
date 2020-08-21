package com.example.services_wallet.Model;

public class AddBalanceDetails {
    private int uId;
    private int amount;

    public AddBalanceDetails() {
    }

    public AddBalanceDetails(int uId, int amount) {
        this.uId = uId;
        this.amount = amount;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
