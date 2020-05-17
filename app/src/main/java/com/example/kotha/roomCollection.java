package com.example.kotha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kotha.database.database;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class roomCollection extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    private EditText firstName, lastName, budget, location, description, noRoom;
    private database dbHelper;
    private ImageView myImg;// manages database
    ListView l1;
    Button b1;
    String pathToFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //  call super's onCreateml
        setContentView(R.layout.activity_room_collection);
        firstName = (EditText) findViewById(R.id.first_text);
        lastName = (EditText) findViewById(R.id.last_text);
        budget = (EditText) findViewById(R.id.budget_text);
        location = (EditText) findViewById(R.id.location_text);
        description = (EditText) findViewById(R.id.descrption_text);
        noRoom = (EditText) findViewById(R.id.noRoom_text);
        //  arrayList = new ArrayList<>();
        myImg = (ImageView) findViewById(R.id.image);
        dbHelper = new database(this);
        b1 = (Button) findViewById(R.id.viewList);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList();
            }
        });

        myImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ActivityCompat.requestPermissions(roomCollection.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
                //   Byte imagebyte= new Byte();
            }

        });

    }


    public void insert(View v) {
        byte[] NewEntrying = imageViewTobyte(myImg);
        boolean result = dbHelper.insertData(firstName.getText().toString(), lastName.getText().toString(),
                description.getText().toString(), location.getText().toString(), Integer.parseInt(budget.getText().toString()), Integer.parseInt(noRoom.getText().toString()), NewEntrying);
        if (result) {
            Toast.makeText(getApplicationContext(), "Data inserted Successfully", Toast.LENGTH_LONG).show();
            firstName.getText().clear();
            lastName.getText().clear();
            budget.getText().clear();
            location.getText().clear();
            description.getText().clear();
            noRoom.getText().clear();

        } else {
            Toast.makeText(getApplicationContext(), "Data could not be inserted Successfully", Toast.LENGTH_LONG).show();
        }


    }

    private byte[] imageViewTobyte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);

            } else {
                Toast.makeText(getApplicationContext(), "YOu dont have the permission to access file", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getData();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            
            myImg.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void viewList() {
        Intent intent = new Intent(this, listview.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);
        return true;
    }

}