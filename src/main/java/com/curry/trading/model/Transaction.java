package com.curry.trading.model;

/**
 * Created by curryshih on 2/19/17.
 */
public class Transaction {

    private String timestamp;
    private String traderId;
    private double value;

    public Transaction(String timestamp, String traderId, double value) {
        this.timestamp = timestamp;
        this.traderId = traderId;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTraderId() {
        return traderId;
    }

    public void setTraderId(String traderId) {
        this.traderId = traderId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "timestamp='" + timestamp + '\'' +
                ", traderId='" + traderId + '\'' +
                ", value=" + value +
                '}';
    }
}
