package com.example.kotha;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class secondactivity extends Fragment {
    Button signin;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.signin,container,false);
        signin = view.findViewById(R.id.button4);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent a = new Intent();

            }
        });
        return view;
    }
}
