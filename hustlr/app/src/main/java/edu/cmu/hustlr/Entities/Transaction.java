package edu.cmu.hustlr.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class Transaction implements Parcelable, Serializable {
    private String symbol;
    private Date date;
    private boolean shorted;
    private double startPrice;
    private double endPrice;
    private int numShares;
    private double userCash; // after transaction

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeSerializable(date);
        dest.writeByte((byte) (shorted ? 1 : 0));
        dest.writeDouble(startPrice);
        dest.writeDouble(endPrice);
        dest.writeInt(numShares);
        dest.writeDouble(userCash);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Transaction createFromParcel(Parcel in) {
            Transaction transaction = new Transaction();
            transaction.symbol = in.readString();
            transaction.shorted = in.readByte() != 0;
            transaction.date = (Date)in.readSerializable();
            transaction.startPrice = in.readDouble();
            transaction.endPrice = in.readDouble();
            transaction.numShares = in.readInt();
            transaction.userCash = in.readDouble();
            return transaction;
        }

        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
