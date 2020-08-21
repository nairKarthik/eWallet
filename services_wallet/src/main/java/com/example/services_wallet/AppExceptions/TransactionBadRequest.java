package com.example.services_wallet.AppExceptions;

public class TransactionBadRequest extends RuntimeException{
    public TransactionBadRequest(){
        super("TransactionBadRequest");
    }
}
