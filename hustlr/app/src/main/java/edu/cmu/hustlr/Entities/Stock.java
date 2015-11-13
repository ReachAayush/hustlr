package edu.cmu.hustlr.Entities;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class Stock {
    private String symbol;
    private boolean shorted = false;
    private int shares;
    private double purchasePrice;
    private double currPrice;
    public Stock() {
    }
    // getter
    public boolean isShorted() {
        return shorted;
    }
    public int getShares() {
        return shares;
    }
    public double mockCurPrice;
    public double getCurrPrice() {
        // TODO: use market API to get the real time price
        return mockCurPrice;
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
}
