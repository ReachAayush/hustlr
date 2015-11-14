package edu.cmu.hustlr.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class Stock implements Parcelable, Serializable {
    private String symbol;
    private boolean shorted = false;
    private int shares = 0;
    private double purchasePrice = 0.0;
    private double currentPrice;
    public Stock() {
    }
    // getter
    public String getSymbol() {
        return symbol;
    }
    public boolean isShorted() {
        return shorted;
    }
    public int getShares() {
        return shares;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public double mockCurrentPrice;
    public double getCurrentPrice() {
        // TODO: use market API to get the real time price
        return mockCurrentPrice;
    }

    // setter
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setShorted(boolean shorted) {
        this.shorted = shorted;
    }
    public void setShares(int shares) {
        this.shares = shares;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeByte((byte) (shorted ? 1 : 0));
        dest.writeInt(shares);
        dest.writeDouble(purchasePrice);
        dest.writeDouble(currentPrice);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Stock createFromParcel(Parcel in) {
            Stock stock = new Stock();
            stock.symbol = in.readString();
            stock.shorted = in.readByte() != 0;
            stock.shares = in.readInt();
            stock.purchasePrice = in.readDouble();
            stock.currentPrice = in.readDouble();
            return stock;
        }

        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };
}
