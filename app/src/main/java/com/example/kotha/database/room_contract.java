package com.example.kotha.database;

import android.provider.BaseColumns;

public class room_contract {
    public static final String DATABASE_NAME = "ar.db";

    // this number should change if the database schema (design) changes
    public static final int DATABASE_VERSION = 3;

    public static abstract class Movie implements BaseColumns {

        public static final String TABLE_NAME = "kotha3";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String FIRST_NAME ="first_name";
        public static final String LAST_NAME ="last_name";
        public static final String LOCATION ="location";
        public static final String Budget = "min_budget";
        public static final String Description ="description";
        public static final String NO_ROOMS="number_of_rooms";
        public static final String IMAGE ="image_ok";

    }
}
