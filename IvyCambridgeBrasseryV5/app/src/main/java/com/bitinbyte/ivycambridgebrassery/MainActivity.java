package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Tag";

    Container ct = new Container();
    Reports rp = new Reports();
    newCount nc = new newCount();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Will create a new shared_prefs file with the name container
        /*SharedPreferences preferencesContainer = getSharedPreferences("container", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesContainer.edit();
        editor.apply();*/


        ct.readFile(this);
        rp.readFile(this);



       /* String name = "ahah";
        String age = "1";
        String id = "0";

        SharedPreferences prefs = getSharedPreferences(
                "SP_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userName", name);
        editor.putString("userAge", age);
        editor.putString("userID", id);
        editor.apply();*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newCount = (Button) findViewById(R.id.bt1);
        Button contain = (Button) findViewById(R.id.bt2);
        Button lastReports = (Button) findViewById(R.id.bt3);
        Button exit = (Button) findViewById(R.id.bt4);

        newCount.setOnClickListener(this);
        contain.setOnClickListener(this);
        lastReports.setOnClickListener(this);
        exit.setOnClickListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ct.saveFile(this);
        Log.d("TAG", "#onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        ct.readFile(this);
        Log.d("TAG", "#onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ct.saveFile(this);
        Log.d("TAG", "#onDestroy");
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
            case R.id.bt4:
                finish();
                System.exit(0);
                break;
        }
    }

    public void finish(){
        ct.saveFile(this);
        rp.saveFile(MainActivity.this);
    }
}
