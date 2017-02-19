package com.curry.trading.service;

import com.curry.trading.model.Trader;
import com.curry.trading.model.Transaction;
import com.curry.trading.util.DataFetcher;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.toList;

/**
 * Created by curryshih on 2/19/17.
 */
public class TradingService {

    private List<Trader> traders;
    private List<Transaction> transactions;

    public TradingService() {
        DataFetcher dataFetcher = new DataFetcher();
        traders = dataFetcher.fetchTraders();
        transactions = dataFetcher.fetchTransactions();
    }

    public TradingService(List<Trader> traders, List<Transaction> transactions) {
        this.traders = traders;
        this.transactions = transactions;
    }

    public List<Trader> getCityTraders(String city) {
        List<Trader> singaporeTraders = traders.stream().filter(t -> t.getCity().equals(city))
                .sorted(comparing(Trader::getName))
                .collect(toList());
        return singaporeTraders;
    }

    public Transaction getHighestValueTransaction() {
        Transaction highestValueTransaction = transactions.stream()
                .sorted((t1, t2) -> new Double(t2.getValue()).compareTo(new Double(t1.getValue())))
                .collect(toList()).get(0);
        return highestValueTransaction;
    }

    public List<Transaction> getYearTransactions(int year) {
        List<Transaction> yearTransactions = transactions.stream()
                .filter(t -> new DateTime(Long.parseLong(t.getTimestamp()) * 1000).getYear() == year)
                .sorted((t1, t2) -> new Double(t2.getValue()).compareTo(new Double(t1.getValue())))
                .collect(toList());
        return yearTransactions;
    }

    public double getAverageTransactionValue(String city) {
        List<String> beijingTraders = new ArrayList<>();
        //get traderIds from the  city
        traders.stream().filter(t -> city.equals(t.getCity()))
                .collect(toList())
                .forEach(t -> beijingTraders.add(t.getId()));

        double average = transactions.stream()
                .filter(t -> beijingTraders.contains(t.getTraderId()))
                .collect(averagingDouble(Transaction::getValue));

        return average;
    }
}
