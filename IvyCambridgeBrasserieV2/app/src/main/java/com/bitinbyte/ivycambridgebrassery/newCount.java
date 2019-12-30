package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class newCount extends AppCompatActivity {



    //Dinamical class to make the table view

    Container cont = new Container();

    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_count);

        showNewCount();
    }

    public void showNewCount(){

        //LinearLayout ll = new LinearLayout(this);
        //LinearLayout ll = (LinearLayout)findViewById(R.id.layoutNewCount);
        //setContentView(ll);
        //ll.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);
            scrollView.setId(0);
            scrollView.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.FILL_PARENT, ScrollView.LayoutParams.MATCH_PARENT));

        TableLayout tl = new TableLayout(this);
            //tl.setGravity(Gravity.CENTER);
        scrollView.addView(tl);
        setContentView(scrollView);

        for(int i = 0; i < cont.getContainer().size(); i++){

            TableRow tr = new TableRow(this);
                tr.setId(i);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                tr.setGravity(Gravity.CENTER);


            TextView newTextView = new TextView(this);
                newTextView.setId(i);
                newTextView.setText(cont.getContainer().get(i).getName() + " - " + cont.getContainer().get(i).getTotalNumber());
                newTextView.setWidth(300);
                newTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //TextView newTextView = (TextView)findViewById(R.id.displayNewCount);
            Button btPlus = new Button(this);
                btPlus.setId(i);
                btPlus.setText("+");
                btPlus.setWidth(150);
            //Button btPlus = (Button)findViewById(R.id.plusNewCount);
            Button btLess = new Button(this);
                btLess.setId(i);
                btLess.setText("-");
                btLess.setWidth(150);
            //Button btLess = (Button)findViewById(R.id.lessNewCount);
            final EditText newEditText = new EditText(this);
                newEditText.setId(i);
                newEditText.setWidth(250);
            //final EditText newEditText = (EditText)findViewById(R.id.insertTextNewCount);

            tr.addView(newTextView);
            tr.addView(newEditText);
            tr.addView(btPlus);
            tr.addView(btLess);

            tl.addView(tr);

            //scrollView.addView(tl);
            //setContentView(scrollView);

            //ll.addView(newTextView);
            //ll.addView(newEditText);
            //ll.addView(btPlus);
            //ll.addView(btLess);
            final int index = i;
            btPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articles articles = new Articles();

                    int num = Integer.parseInt(newEditText.getText().toString().trim());

                    articles.plusTotalNumber(cont.getContainer(),index,num);


                    showNewCount();
                }
            });

            btLess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articles articles = new Articles();

                    int num = Integer.parseInt(newEditText.getText().toString().trim());

                    articles.lessTotalNumber(cont.getContainer(),index,num);


                    showNewCount();
                }
            });
        }

        Button goBack = new Button(this);
        goBack.setText("GO BACK");
        //ll.addView(goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newCount.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button createReport = new Button(this);
        goBack.setText("CREATE REPORT");
        //ll.addView(createReport);

        createReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To create a class and a arraylist reports, copy the showArticles from Articles.class
            }
        });

    }

}
