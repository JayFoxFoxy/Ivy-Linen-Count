package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Tag";

    Button newCount = (Button) findViewById(R.id.bt1);
    Button lastReports = (Button) findViewById(R.id.bt2);
    Button exit = (Button) findViewById(R.id.bt3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt1:
                Log.d(TAG, "New count");
                break;
            case R.id.bt2:
                break;
            case R.id.bt3:
                break;
        }
    }
}
