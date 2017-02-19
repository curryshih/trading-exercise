package com.curry.trading.service;

import com.curry.trading.model.Trader;
import com.curry.trading.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by curryshih on 2/19/17.
 */
public class TradingServiceTest {
    private TradingService tradingService;

    @Before
    public void before() {
        List<Trader> traders = new ArrayList<>();
        traders.add(new Trader("1", "Curry", "Taipei"));
        traders.add(new Trader("2", "Kobe", "Taipei"));
        traders.add(new Trader("3", "James", "New York"));
        traders.add(new Trader("4", "Curry", "Kaohsiung"));
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("1487507282", "1", 10));
        transactions.add(new Transaction("1487507280", "1", 40));
        transactions.add(new Transaction("1487507283", "2", 40));
        transactions.add(new Transaction("1487507283", "3", 50));
        transactions.add(new Transaction("1455884882", "4", 40));

        tradingService = new TradingService(traders, transactions);
    }

    @Test
    public void getCityTraders() {
        List<Trader> traders = tradingService.getCityTraders("Taipei");
        Assert.assertEquals(2, traders.size());
    }

    @Test
    public void getHighestValueTransaction() {
        Transaction transaction = tradingService.getHighestValueTransaction();
        Assert.assertEquals(50, transaction.getValue(), 0.1);
    }

    @Test
    public void getYearTransactions() {
        List<Transaction> yearTransactions = tradingService.getYearTransactions(2017);
        Assert.assertEquals(4, yearTransactions.size());
    }

    @Test
    public void getAverageTransactionValue() {
        double average = tradingService.getAverageTransactionValue("Taipei");
        Assert.assertEquals(30, average, 0.1);
    }
}
