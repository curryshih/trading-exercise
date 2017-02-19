package com.curry.trading.util;

import com.curry.trading.model.Trader;
import com.curry.trading.model.Transaction;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;

/**
 * Created by curryshih on 2/19/17.
 */
public class DataFetcher {

    private static final String PATH_SERVER = "https://fvjkpkflnc.execute-api.us-east-1.amazonaws.com";
    private static final String API_KEY = "gaqcRZE4bd58gSAJH3XsLYBo1EvwIQo88IfYL1L5";
    private static final String PATH_TRADERS_DATA = "/prod/traders";
    private static final String PATH_TRANSACTIONS_DATA = "/prod/transactions";

    private Client client;

    public DataFetcher() {
        client = ClientBuilder.newClient();
    }

    public List<Trader> fetchTraders() {
        List<Trader> traders;
        String target = PATH_SERVER + PATH_TRADERS_DATA;
        try {
            String traderResult = client.target(target)
                    .request()
                    .header("x-api-key", API_KEY)
                    .get(String.class);
            traders = new GsonBuilder()
                    .create().fromJson(traderResult, new TypeToken<List<Trader>>(){}.getType());
        } catch (Exception e) {
            System.err.println("Can't fetch trader data from " + target);
            throw new RuntimeException(e);
        }

        return traders;
    }

    public List<Transaction> fetchTransactions() {
        List<Transaction> transactions;
        String target = PATH_SERVER + PATH_TRANSACTIONS_DATA;
        try {
            String transactionResult = client.target(target)
                    .request()
                    .header("x-api-key", API_KEY)
                    .get(String.class);
            GsonBuilder builder = new GsonBuilder();
            transactions = builder
                    .create().fromJson(transactionResult, new TypeToken<List<Transaction>>(){}.getType());
        } catch (Exception e) {
            System.err.println("Can't fetch transaction data from " + target);
            throw new RuntimeException(e);
        }

        return transactions;
    }
}
