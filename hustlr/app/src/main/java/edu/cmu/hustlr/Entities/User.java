package edu.cmu.hustlr.Entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.io.Serializable;

/**
 * Created by jacobnelson on 11/12/15.
 */
public class User implements Parcelable {
    private String name;
    private String password;
    // private ContactsContract.CommonDataKinds.Email address;
    private String address;
    private double cash; // initial cash
    private Portfolio port;
    // getter
    public String getName() {
        return name;
    }
    public double getCash() {
        return cash;
    }
    // setter
    public void setName(String name) {
        this.name = name;
    }
    public void setCash(double cash) {
        this.cash = cash;
    }
    public void setPortfolio(Portfolio port) {
        this.port = port;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(address);
        dest.writeDouble(cash);
        dest.writeParcelable(port, 0);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            User user = new User();
            user.name = in.readString();
            user.password = in.readString();
            user.address = in.readString();
            user.cash = in.readDouble();
            user.port = in.readParcelable(Portfolio.class.getClassLoader());
            return user;
        }

        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
