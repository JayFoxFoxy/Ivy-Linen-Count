package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class newCount extends AppCompatActivity {



    //TODO Try catch to catch only numbers on the new count
    //TODO Convert linearLayout to table layout with the xml file with id

    Container cont = new Container();

    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_count);

        showNewCount();
    }

    public void showNewCount(){

        LinearLayout ll = new LinearLayout(this);
        setContentView(ll);
        ll.setOrientation(LinearLayout.VERTICAL);

        for(int i = 0; i < cont.getContainer().size(); i++){


            TextView newTextView = new TextView(this);
            Button btPlus = new Button(this);
            Button btLess = new Button(this);
            final EditText newEditText = new EditText(this);
            btPlus.setText("+");
            btLess.setText("-");
            //newEditText.setText("Insert a number");
            newTextView.setText(cont.getContainer().get(i).getName() + " - " + cont.getContainer().get(i).getTotalNumber());
            ll.addView(newTextView);
            ll.addView(newEditText);
            ll.addView(btPlus);
            ll.addView(btLess);
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
        ll.addView(goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newCount.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button createReport = new Button(this);
        goBack.setText("CREATE REPORT");
        ll.addView(createReport);

        createReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To create a class and a arraylist reports, copy the showArticles from Articles.class
            }
        });

    }

}
