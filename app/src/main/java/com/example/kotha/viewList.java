package com.example.kotha;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kotha.R;
import com.example.kotha.database.database;
import com.example.kotha.database.room_contract;

public class viewList extends AppCompatActivity {
    private TextView ed1, ed2, ed3, ed4, ed5, ed6;
    private ImageView image;
    private long rowID;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlist);
        ed1 = (TextView) findViewById(R.id.first_text);
        ed2 = (TextView) findViewById(R.id.last_text);
        ed3 = (TextView) findViewById(R.id.budget_text);
        ed4 = (TextView) findViewById(R.id.location_text);
        ed5 = (TextView) findViewById(R.id.descrption_text);
        ed6 = (TextView) findViewById(R.id.noRoom_text);
        image=(ImageView) findViewById(R.id.image_view);

        Bundle extras = getIntent().getExtras();
        rowID = extras.getLong(room_contract.Movie.COLUMN_NAME_ID);

        database database = new database(this);
        db = database.getWritableDatabase();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // create new LoadMovieTask and execute it
        new LoadMovieTask().execute(rowID);
    }

    private class LoadMovieTask extends AsyncTask<Long, Void, Cursor> {
        @Override
        protected Cursor doInBackground(Long... longs) {
            return db.query(room_contract.Movie.TABLE_NAME, null, room_contract.Movie._ID ,
                    null, null, null, null);

        }
        @Override
        protected void onPostExecute(Cursor result) {
            super.onPostExecute(result);
            result.moveToFirst();
            int firstName=result.getColumnIndex(room_contract.Movie.FIRST_NAME);
            int LastName=result.getColumnIndex(room_contract.Movie.LAST_NAME);
            int location=result.getColumnIndex(room_contract.Movie.LOCATION);
            int description=result.getColumnIndex(room_contract.Movie.Description);
            int budget=result.getColumnIndex(room_contract.Movie.Budget);
            int norooms=result.getColumnIndex(room_contract.Movie.NO_ROOMS);
            int get_room=result.getColumnIndex(room_contract.Movie.IMAGE);


            ed1.setText(result.getString(firstName));
            ed2.setText(result.getString(LastName));
            ed3.setText(result.getString(location));
            ed4.setText(result.getString(description));
            ed5.setText(result.getString(budget));
            ed6.setText(result.getString(norooms));

            byte[] as =result.getBlob(get_room);
          BitmapFactory.Options options= new BitmapFactory.Options();

            Bitmap bm = BitmapFactory.decodeByteArray(as,0,as.length,options);
            image.setImageBitmap(bm);
        }
    }
}
