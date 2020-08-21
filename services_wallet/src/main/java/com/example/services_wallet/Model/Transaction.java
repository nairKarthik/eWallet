package com.example.services_wallet.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trId;
    private int trAmt;
    private int sendersId;
    private int receiversId;
    private Date trDateAndTime;
    private String status;

    public Transaction() {
    }

    public Transaction(int trId, int trAmt, int sendersId, int receiversId, Date trDateAndTime, String status) {
        this.trId = trId;
        this.trAmt = trAmt;
        this.sendersId = sendersId;
        this.receiversId = receiversId;
        this.trDateAndTime = trDateAndTime;
        this.status = status;
    }

    public int getTrId() {
        return trId;
    }

    public void setTrId(int trId) {
        this.trId = trId;
    }

    public int getTrAmt() {
        return trAmt;
    }

    public void setTrAmt(int trAmt) {
        this.trAmt = trAmt;
    }

    public int getSendersId() {
        return sendersId;
    }

    public void setSendersId(int sendersId) {
        this.sendersId = sendersId;
    }

    public int getReceiversId() {
        return receiversId;
    }

    public void setReceiversId(int receiversId) {
        this.receiversId = receiversId;
    }

    public Date getTrDateAndTime() {
        return trDateAndTime;
    }

    public void setTrDateAndTime(Date trDateAndTime) {
        this.trDateAndTime = trDateAndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}