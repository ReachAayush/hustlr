package edu.cmu.hustlr.Util;

import android.provider.BaseColumns;

/**
 * The DatabaseContract specifies the schemas of the SQL tables used for
 * accessing user info.
 */
public abstract class DatabaseContract {

    /**
     * Columns used for making a profile
     */
    public static abstract class ProfileEntry implements BaseColumns {
        public static final String TABLE_NAME = "profiles";

        /**
         * The name for the profile.
         */
        public static final String COLUMN_NAME_PROFILE_NAME = "username";

        /*
         * The password for the profile.
         */
        public static final String COLUMN_NAME_PROFILE_PASSWORD = "password";

        /*
         * The id of the portfolio associated with the user.
         */
        public static final String COLUMN_NAME_PORTFOLIO_ID = "portfolio_id";

        /*
         * The id associated with the user
         */
        public static final String COLUMN_NAME_PROFILE_ID = "profile_id";
    }

    /**
     * Columns used for portfolio.
     */
    public static abstract class PortfolioEntry implements BaseColumns {
        public static final String TABLE_NAME = "portfolios";

        /**
         * The id of the portfolio
         */
        public static final String COLUMN_NAME_PORTFOLIO_ID = "portfolio_id";

        /*
         * The amount of cash on hand
         */
        public static final String COLUMN_NAME_PROFILE_CASH = "cash";
    }

    /**
     * Columns for OwnedStocks.
     */
    public static abstract class OwnedStockEntry implements BaseColumns {
        public static final String TABLE_NAME = "owned_stocks";

        /**
         * The id of the portfolio which owns this stock.
         */
        public static final String COLUMN_NAME_PORTFOLIO_ID = "portfolio_id";

        /**
         * The id of the owned_stock
         */
        public static final String COLUMN_NAME_OWNED_STOCK_ID = "owned_stock_id";

        /**
         * The symbol of the owned stock.
         */
        public static final String COLUMN_NAME_SYMBOL = "symbol";

        /**
         * The price at which the stock was bought or shorted.
         */
        public static final String COLUMN_NAME_START_PRICE = "start_price";

        /**
         * The status of the stock, whether it was bought or shorted.
         */
        public static final String COLUMN_NAME_BUY_OR_SHORT = "buy_or_short";
    }

    /**
     * Columns for Transactions.
     */
    public static abstract class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME = "transactions";

        /**
         * The id of the transaction.
         */
        public static final String COLUMN_NAME_TRANSACTION_ID = "transaction_id";

        /**
         * The id of the portfolio making the transaction.
         */
        public static final String COLUMN_NAME_PORTFOLIO_ID = "portfolio_id";

        /**
         * The owned stock id of the transaction.
         */
        public static final String COLUMN_NAME_OWNED_STOCK_ID = "owned_stock_id";

        /**
         * The date of the transaction.
         */
        public static final String COLUMN_NAME_TRANSACTION_DATE = "date";

        /**
         * The final price of the stock in the transaction.
         */
        public static final String COLUMN_NAME_TRANSACTION_FINAL_PRICE = "final_price";

        /**
         * The value of the portfolio at the end of the transaction.
         */
        public static final String COLUMN_NAME_FINAL_PORTFOLIO_VALUE = "final_portfolio_value";
    }
}