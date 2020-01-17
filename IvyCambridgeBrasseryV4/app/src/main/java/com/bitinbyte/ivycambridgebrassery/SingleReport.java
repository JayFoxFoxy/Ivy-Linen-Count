package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleReport extends AppCompatActivity {

    TextView screen;
    //Button srb1;

    public newCount NewCount = new newCount();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_report);

        this.screen = (TextView) findViewById(R.id.srt1);
        //this.srb1 = (Button) findViewById(R.id.srb1);

        int id = getIntent().getIntExtra("INDEX", 0);

        showContentEditText(id);

        //this.srb1.setOnClickListener(this);
    }

    public void showContentEditText(int id) {
        this.screen.setText(this.NewCount.showReport(id));
    }

    public void goBack(View v) {
        Intent intent = new Intent(SingleReport.this, MainActivity.class);
        startActivity(intent);
    }


}


