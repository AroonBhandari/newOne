package com.example.kotha.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ImageView;

import com.example.kotha.room;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";  // SQL data type

    private static final String COMMA_SEP = ",";      // SQL comma
    private static final String INT_TYPE = " INT";
    // SQL command to create database
   private static final String BLOB_TYPE=" BLOB";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + room_contract.Movie.TABLE_NAME;

    Context context;


    private static final String SQL_CREATE_ENTRIES =
   //         "CREATE TABLE "+ room_contract.Movie.TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT,lastname TEXT,location TEXT,description TEXT,budget INT,noofroom INT, newimage blob)";
            "CREATE TABLE " + room_contract.Movie.TABLE_NAME + " (" +
                    room_contract.Movie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    room_contract.Movie.FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                    room_contract.Movie.LAST_NAME + TEXT_TYPE + COMMA_SEP +
                    room_contract.Movie.LOCATION + TEXT_TYPE + COMMA_SEP +
                    room_contract.Movie.Description + TEXT_TYPE + COMMA_SEP +
                    room_contract.Movie.Budget + INT_TYPE + COMMA_SEP +
                    room_contract.Movie.NO_ROOMS + INT_TYPE +COMMA_SEP+
                    room_contract.Movie.IMAGE+ BLOB_TYPE +

                    ")";

    public database(Context context) {
        super(context, room_contract.DATABASE_NAME, null, room_contract.DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade( db,  oldVersion, newVersion);
    }

    public boolean insertData(String firstName, String lastName, String Location, String Description,
                              int budget, int noRooms, byte[] by) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(room_contract.Movie.FIRST_NAME, firstName);
        contentValues.put(room_contract.Movie.LAST_NAME, lastName);
        contentValues.put(room_contract.Movie.Description, Description);
        contentValues.put(room_contract.Movie.LOCATION, Location);
        contentValues.put(room_contract.Movie.Budget, budget);
        contentValues.put(room_contract.Movie.NO_ROOMS, noRooms);
        contentValues.put(room_contract.Movie.IMAGE,by);
        long result = db.insert(room_contract.Movie.TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<room> getAlldata() {

        ArrayList<room> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + room_contract.Movie.TABLE_NAME, null);
    while(cursor.moveToNext()){
      int id =cursor.getInt(0);
      String firstName= cursor.getString(1);
      String lastName= cursor.getString(2);
      String location= cursor.getString(3);
      String description = cursor.getString(4);
     int budget =cursor.getInt(5);
      int noRooms =cursor.getInt(6);
        byte[] image=cursor.getBlob(7) ;
     room room = new room(id,firstName,lastName,location,description,budget,noRooms,image);
      arrayList.add(room);
    }
    return arrayList;
    }
}
