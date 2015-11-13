package edu.cmu.hustlr.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class Portfolio implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(stocks);
        dest.writeSerializable(transactions);
        dest.writeDouble(cash);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Portfolio createFromParcel(Parcel in) {
            Portfolio portfolio = new Portfolio();
            portfolio.stocks = (ArrayList<Stock>)in.readSerializable();
            portfolio.transactions = (ArrayList<Transaction>)in.readSerializable();
            portfolio.cash = in.readDouble();
            return portfolio;
        }

        public Portfolio[] newArray(int size) {
            return new Portfolio[size];
        }
    };
}
