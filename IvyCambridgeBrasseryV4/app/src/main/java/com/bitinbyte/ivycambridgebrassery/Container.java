package com.bitinbyte.ivycambridgebrassery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static android.content.Context.*;


public class Container extends AppCompatActivity implements View.OnClickListener {


    public static final String PREFS_NAME = "containerSharedPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static File mFolder;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private static final String TAG = "Tag";

    public Articles articles = new Articles();

    private static ArrayList<Articles> container = new ArrayList<>();

    ScrollView scrollView;
    Button newValue, deleteValue, goBack;
    EditText editText;
    TextView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        this.scrollView = (ScrollView)findViewById(R.id.scrollViewContainer);
        this.newValue = (Button) findViewById(R.id.bct1);
        this.deleteValue = (Button) findViewById(R.id.bct2);
        this.goBack = (Button) findViewById(R.id.bct3);
        this.screen = (TextView) findViewById(R.id.tct1);
        this.editText = (EditText) findViewById(R.id.ect1);

        showContentEditText();

        this.newValue.setOnClickListener(this);
        this.deleteValue.setOnClickListener(this);
        this.goBack.setOnClickListener(this);

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
            case R.id.bct3:
                Intent intent = new Intent(Container.this,MainActivity.class);
                startActivity(intent);
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

   /* public void saveFile() {

        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "myFile.txt";


        // save the object to file
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            File f = new File(getCacheDir(), "saves");
            f.createNewFile();
            if (!f.exists())
                f.mkdir();
                f.createNewFile();
            f = new File(baseDir + File.separator + fileName);
            fos = new FileOutputStream(f);
            out = new ObjectOutputStream(fos);
            out.writeObject(this.container);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    public void saveFile(){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //SharedPreferences prefs = this.getPreferences(Container.this.getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        List<Articles.ArticleModel> articles = getContainer().strem.map(Articles::toModel).collect(Collectors.toList());
        String json = gson.toJson(articles);
        editor.putString("container", json);
        editor.apply();
        editor.commit();
    }

/*
    static int readFile(int gamesPlayed) {

        String aux = "0";



        try{
            File obj = new File("saves.txt");
            Scanner input = new Scanner(obj);
            while(input.hasNextLine()){
                aux = input.nextLine();
                System.out.println(aux);
            }


            input.close();
            System.out.println("File read Successfully.");
        }
        catch(IOException e){
            System.out.println("Error creating File");
        }

        gamesPlayed = Integer.parseInt(aux);

        return gamesPlayed;

    }*/



    public void showContentEditText(){
        this.screen.setText(this.articles.showArticles(this.container));
    }

    public void resetEditText(){
        this.editText.setText("");
    }

    public void setContainer(ArrayList<Articles> ls) { this.container = ls; }

    public ArrayList<Articles> getContainer() { return this.container; }
}
