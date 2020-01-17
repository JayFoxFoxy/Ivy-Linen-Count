package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class showReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reports);

        showReports();
    }

    public void showReports(){

        newCount nc = new newCount();

        ScrollView scrollView = new ScrollView(this);
            scrollView.setId(0);
            scrollView.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.FILL_PARENT, ScrollView.LayoutParams.MATCH_PARENT));

        TableLayout tl = new TableLayout(this);
        scrollView.addView(tl);
        setContentView(scrollView);

        System.out.println(nc.getReports().size());

        for(int i = nc.getReports().size()-1; i >= 0; --i){
            TableRow tr = new TableRow(this);
                tr.setId(i);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                tr.setGravity(Gravity.CENTER);


            TextView newTextView = new TextView(this);
                newTextView.setId(i);
                newTextView.setText(nc.getReports().get(i).getDate());
                newTextView.setWidth(500);
                newTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            Button btSelect = new Button(this);
                btSelect.setId(i);
                btSelect.setText("Select");
                btSelect.setWidth(200);

            tr.addView(newTextView);
            tr.addView(btSelect);

            tl.addView(tr);

            final int index = i;

            btSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(showReports.this, SingleReport.class);
                        intent.putExtra("INDEX", index);
                        startActivity(intent);

                }
            });
        }

        Button goBack = new Button(this);
        goBack.setText("GO BACK");
        tl.addView(goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showReports.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
