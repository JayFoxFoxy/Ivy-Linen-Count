package com.bitinbyte.ivycambridgebrassery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Container extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Tag";

    public Articles articles = new Articles();

    private ArrayList<Articles> container = new ArrayList<>();

    Button newValue, deleteValue;
    EditText editText;
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        this.newValue = (Button) findViewById(R.id.bct1);
        this.deleteValue = (Button) findViewById(R.id.bct2);
        this.screen = (TextView) findViewById(R.id.tct1);
        this.editText = (EditText) findViewById(R.id.ect1);

        showContentEditText();

        this.newValue.setOnClickListener(this);
        this.deleteValue.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bct1:
                Log.d(TAG, "New value");
                Articles a = new Articles(this.editText.getText().toString());
                this.container.add(a);
                showContentEditText();
                resetEditText();
                break;
            case R.id.bct2:
                Log.d(TAG, "Delete value");
                boolean check = checkName(this.container, this.editText.getText().toString());
                if(check) {
                    this.container = articles.deleteValue(this.container, this.editText.getText().toString());
                    showContentEditText();
                    resetEditText();
                }else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Error");
                    builder.setMessage("The value is not reachable").setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                break;

        }
    }

    public boolean checkName(ArrayList<Articles> ls, String name){

        boolean check = false;

        for (int i = 0; i < ls.size(); i++){
            if (ls.get(i).getName().equals(name)){
                check = true;
            }
        }

        return check;

    }

    public void showContentEditText(){
        this.screen.setText(this.articles.showArticles(this.container));
    }

    public void resetEditText(){
        this.editText.setText("");
    }
}
