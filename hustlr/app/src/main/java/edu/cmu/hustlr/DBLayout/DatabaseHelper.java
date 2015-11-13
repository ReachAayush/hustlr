package edu.cmu.hustlr.DBLayout;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aayushAgarwal on 11/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "hustlr";
    private static final int DB_VERSION_NUMBER = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create profile table
        sqLiteDatabase.execSQL(createProfileTable());

        //create portfolio table
        sqLiteDatabase.execSQL(createPortfolioTable());

        //create ownedstock table
        sqLiteDatabase.execSQL(createOwnedStocksTable());

        //create transactions table
        sqLiteDatabase.execSQL(createTransactionTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.ProfileEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.PortfolioEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.OwnedStockEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TransactionEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private String createProfileTable(){
        String createProfile;
        createProfile = "CREATE TABLE " + DatabaseContract.ProfileEntry.TABLE_NAME + "( ";
        createProfile += DatabaseContract.ProfileEntry.COLUMN_NAME_PROFILE_ID + " integer primary key, ";
        createProfile += DatabaseContract.ProfileEntry.COLUMN_NAME_PROFILE_NAME + " text, ";
        createProfile += DatabaseContract.ProfileEntry.COLUMN_NAME_PROFILE_PASSWORD + " text, ";
        createProfile += DatabaseContract.ProfileEntry.COLUMN_NAME_PORTFOLIO_ID + " integer);";
        System.out.println("CREATE PROFILE TABLE: " + createProfile);
        return createProfile;
    }

    private String createPortfolioTable(){
        String createPort;
        createPort = "CREATE TABLE " + DatabaseContract.PortfolioEntry.TABLE_NAME + "( ";
        createPort += DatabaseContract.PortfolioEntry.COLUMN_NAME_PORTFOLIO_ID + " integer primary key, ";
        createPort += DatabaseContract.PortfolioEntry.COLUMN_NAME_PROFILE_CASH + " real);";
        System.out.println("CREATE PROFILE TABLE: " + createPort);
        return createPort;
    }

    private String createOwnedStocksTable(){
        String create;
        create = "CREATE TABLE " + DatabaseContract.OwnedStockEntry.TABLE_NAME + "( ";
        create += DatabaseContract.OwnedStockEntry.COLUMN_NAME_OWNED_STOCK_ID + " integer primary key, ";
        create += DatabaseContract.OwnedStockEntry.COLUMN_NAME_PORTFOLIO_ID + " integer, ";
        create += DatabaseContract.OwnedStockEntry.COLUMN_NAME_BUY_OR_SHORT + " text, ";
        create += DatabaseContract.OwnedStockEntry.COLUMN_NAME_SYMBOL + " text, ";
        create += DatabaseContract.OwnedStockEntry.COLUMN_NAME_START_PRICE + " real);";
        System.out.println("CREATE OWNEDSTOCK TABLE: " + create);
        return create;
    }

    private String createTransactionTable(){
        String create;
        create = "CREATE TABLE " + DatabaseContract.TransactionEntry.TABLE_NAME + "( ";
        create += DatabaseContract.TransactionEntry.COLUMN_NAME_TRANSACTION_ID + " integer primary key, ";
        create += DatabaseContract.TransactionEntry.COLUMN_NAME_OWNED_STOCK_ID + " integer, ";
        create += DatabaseContract.TransactionEntry.COLUMN_NAME_PORTFOLIO_ID + " integer, ";
        create += DatabaseContract.TransactionEntry.COLUMN_NAME_TRANSACTION_DATE + " text, ";
        create += DatabaseContract.TransactionEntry.COLUMN_NAME_TRANSACTION_FINAL_PRICE + " real, ";
        create += DatabaseContract.TransactionEntry.COLUMN_NAME_FINAL_PORTFOLIO_VALUE + " real);";
        System.out.println("CREATE TRANSACTION TABLE: " + create);
        return create;
    }

    public void addProfile(String username, String password, double startingCash){
        SQLiteDatabase db = getReadableDatabase();

        //first create a portfolio for the user
        ContentValues v1 = new ContentValues();
        v1.put(DatabaseContract.PortfolioEntry.COLUMN_NAME_PROFILE_CASH, startingCash);
        db.insert(DatabaseContract.PortfolioEntry.TABLE_NAME, null, v1);

        //get the id of the newly created profile
        String query = "SELECT ROWID from " + DatabaseContract.PortfolioEntry.TABLE_NAME + " order by  ROWID DESC limit 1";
        Cursor c = db.rawQuery(query, null);
        int lastId = -1;
        if(c != null && c.moveToFirst()){
            lastId = (int) c.getLong(0);
        }

        //create profile entry with the portfolio id
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ProfileEntry.COLUMN_NAME_PROFILE_NAME, username);
        values.put(DatabaseContract.ProfileEntry.COLUMN_NAME_PROFILE_PASSWORD, password);
        values.put(DatabaseContract.ProfileEntry.COLUMN_NAME_PORTFOLIO_ID, lastId);
        db.insert(DatabaseContract.ProfileEntry.TABLE_NAME, null, values);
    }

    public void updatePortfolioCash(int portId, double newCashAmount){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(DatabaseContract.PortfolioEntry.COLUMN_NAME_PROFILE_CASH, newCashAmount);
        String portIDCheck = DatabaseContract.PortfolioEntry.COLUMN_NAME_PORTFOLIO_ID + "=" + Integer.toString(portId);
        db.update(DatabaseContract.PortfolioEntry.TABLE_NAME, newValues, portIDCheck, null);
    }
}
