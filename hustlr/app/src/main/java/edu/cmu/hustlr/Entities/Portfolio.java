package edu.cmu.hustlr.Entities;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class Portfolio {
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private double cash = 0;
    // getter
    public ArrayList<Stock> getAllStocks() {
        return stocks;
    }
    public ArrayList<Transaction> getAllTransactions() {
        return transactions;
    }
    public double getCash() {
        return cash;
    }

    // setter
    public void addStock(Stock stock) {
        stocks.add(stock);
    }
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public void setCash(double cash) {
        this.cash = cash;
    }
}
