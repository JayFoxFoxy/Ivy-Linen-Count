package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class newCount extends AppCompatActivity {

    Container ct = new Container();

    private static ArrayList<Reports> reports = new ArrayList<>();

    //Dinamical class to make the table view

    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Container container = new Container();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_count);

        //container.saveFile(this);
        //container.loadData();

        showNewCount();
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

        for(int i = 0; i < ct.getContainer().size(); i++){

            TableRow tr = new TableRow(this);
                tr.setId(i);
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
                tr.setGravity(Gravity.CENTER);


            TextView newTextView = new TextView(this);
                newTextView.setId(i);
                newTextView.setText(ct.getContainer().get(i).getName() + " - " + ct.getContainer().get(i).getTotalNumber());
                newTextView.setWidth(300);
                newTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //TextView newTextView = (TextView)findViewById(R.id.displayNewCount);
            Button btPlus = new Button(this);
                btPlus.setId(i);
                btPlus.setText("+");
                btPlus.setWidth(50);
                btPlus.setLayoutParams (new TableRow.LayoutParams(230, TableRow.LayoutParams.MATCH_PARENT));
            //Button btPlus = (Button)findViewById(R.id.plusNewCount);
            Button btLess = new Button(this);
                btLess.setId(i);
                btLess.setText("-");
                btLess.setWidth(50);
                btLess.setLayoutParams (new TableRow.LayoutParams(230, TableRow.LayoutParams.MATCH_PARENT));

            Button btMult = new Button(this);
                btMult.setId(i);
                btMult.setText("* 20");
                btMult.setWidth(50);
                btMult.setLayoutParams (new TableRow.LayoutParams(230, TableRow.LayoutParams.MATCH_PARENT));

            final EditText newEditText = new EditText(this);
                newEditText.setId(i);
                newEditText.setWidth(200);
                newEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                newEditText.setTransformationMethod(null);
            //final EditText newEditText = (EditText)findViewById(R.id.insertTextNewCount);

            tr.addView(newTextView);
            tr.addView(newEditText);
            tr.addView(btPlus);
            tr.addView(btLess);
            tr.addView(btMult);

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
                    Container cn = new Container();

                    try {
                        int num = Integer.parseInt(newEditText.getText().toString().trim());
                        articles.plusTotalNumber(ct.getContainer(),index,num);
                        cn.saveFile(newCount.this);
                    }catch(NumberFormatException ex) {
                        //They didn't enter a number.  Pop up a toast or warn them in some other way
                        return;
                    }




                    showNewCount();
                }
            });

            btLess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articles articles = new Articles();
                    Container cn = new Container();

                    try{
                        int num = Integer.parseInt(newEditText.getText().toString().trim());
                        articles.lessTotalNumber(ct.getContainer(),index,num);
                        cn.saveFile(newCount.this);
                    }catch(NumberFormatException ex) {
                        //They didn't enter a number.  Pop up a toast or warn them in some other way
                        return;
                    }


                    showNewCount();
                }
            });
            btMult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articles articles = new Articles();
                    Container cn = new Container();

                    try{
                        int num = Integer.parseInt(newEditText.getText().toString().trim());
                        articles.MultValue(ct.getContainer(),index,num);
                        cn.saveFile(newCount.this);
                    }catch(NumberFormatException ex) {
                        //They didn't enter a number.  Pop up a toast or warn them in some other way
                        return;
                    }

                    showNewCount();
                }
            });
        }

        Button createReport = new Button(this);
            createReport.setText("CREATE REPORT");
            tl.addView(createReport);

        createReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(newCount.this);
                builder.setTitle("Message");
                builder.setMessage("Are you sure you want to create a report? \nThis will delete all the totals of the list!").setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Reports rp = new Reports();
                        Container cn = new Container();

                        int last = cn.getContainer().size();

                        String report = rp.generateReport(cn.getContainer());
                        Reports newReport = new Reports(report, rp.generateDate());
                        getReports().add(newReport);

                        Articles ar = new Articles();
                        ar.reset();
                        showNewCount();

                        rp.saveFile(newCount.this);


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }




        });

        Button reset = new Button(this);
            reset.setText("RESET");
            tl.addView(reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final AlertDialog.Builder builder = new AlertDialog.Builder(newCount.this);
                builder.setTitle("Message");
                builder.setMessage("Are you sure you want to reset all the values on the list? ").setCancelable(false);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Articles ar = new Articles();
                        Container cn = new Container();
                        ar.reset();
                        cn.saveFile(newCount.this);
                        showNewCount();
                        //cn.resetEditText();
                        //dialog.cancel();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        Button goBack = new Button(this);
            goBack.setText("GO BACK");
            tl.addView(goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newCount.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public String showReport(int id){ return getReports().get(id).getReport(); }

    public ArrayList<Reports> getReports() { return this.reports; }

}
