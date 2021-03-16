package com.eaapps.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class E extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e);

        findViewById(R.id.a).setOnClickListener(v->startActivity(new Intent(this,F.class)
         ));
    }

    @Override
    protected void onDestroy() {
        Log.d("BOYS", "onDestroy: ");
        super.onDestroy();
    }
}