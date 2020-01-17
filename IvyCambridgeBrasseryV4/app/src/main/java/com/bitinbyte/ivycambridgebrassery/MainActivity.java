package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String name = "ahah";
        String age = "1";
        String id = "0";

        SharedPreferences prefs = getSharedPreferences(
                "SP_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userName", name);
        editor.putString("userAge", age);
        editor.putString("userID", id);
        editor.apply();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newCount = (Button) findViewById(R.id.bt1);
        Button contain = (Button) findViewById(R.id.bt2);
        Button lastReports = (Button) findViewById(R.id.bt3);
        Button exit = (Button) findViewById(R.id.bt4);

        newCount.setOnClickListener(this);
        contain.setOnClickListener(this);
        lastReports.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt1:
                Log.d(TAG, "New count");
                Intent intent = new Intent(MainActivity.this,newCount.class);
                startActivity(intent);
                break;
            case R.id.bt2:
                Log.d(TAG, "Container");
                intent = new Intent(MainActivity.this,Container.class);
                startActivity(intent);
                break;
            case R.id.bt3:
                Log.d(TAG, "LatestReports");
                intent = new Intent(MainActivity.this, showReports.class);
                startActivity(intent);
                break;
        }
    }
}
