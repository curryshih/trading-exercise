package com.curry.trading;

import com.curry.trading.model.Trader;
import com.curry.trading.model.Transaction;
import com.curry.trading.service.TradingService;
import java.util.List;

/**
 * Created by curryshih on 2/19/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        TradingService tradingService = new TradingService();

        System.out.println("1. Find all traders from Singapore and sort them by name.");
        List<Trader> singaporeTraders = tradingService.getCityTraders("Singapore");
        singaporeTraders.forEach(System.out::println);

        System.out.println("\n2. Find the transaction with the highest value.");
        Transaction highestValueTransaction = tradingService.getHighestValueTransaction();
        System.out.println(highestValueTransaction);

        System.out.println("\n3. Find all transactions in the year 2016 and sort them by value (high to small).");
        List<Transaction> lastYearTransactions = tradingService.getYearTransactions(2016);
        lastYearTransactions.forEach(System.out::println);

        System.out.println("\n4. Find the average of transactions' values from the traders living in Beijing.");
        double average = tradingService.getAverageTransactionValue("Beijing");
        System.out.println(average);
    }
}
