package com.bitinbyte.ivycambridgebrassery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Container extends AppCompatActivity implements View.OnClickListener {

    //Container ct = new Container();


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
    protected void onPause() {
        super.onPause();
        Container ct = new Container();
        ct.saveFile(this);
        Log.d("TAG", "#onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Container ct = new Container();
        ct.readFile(this);
        Log.d("TAG", "#onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Container ct = new Container();
        ct.saveFile(this);
        Log.d("TAG", "#onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bct1:
                Log.d(TAG, "New value");
                Articles a = new Articles(this.editText.getText().toString(), 0);
                this.container.add(a);
                saveFile(this);
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

    /*public void saveFile(Context context){
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences prefs = context.getSharedPreferences("container", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //editor.clear().commit();
        Gson gson = new Gson();
        List<Articles.ArticleModel> models = new ArrayList<>();
        *//*for(Articles article : getContainer())
        {
            models.add(article.toModel());
        }*//*
        for(int i = 0; i < this.container.size(); i++){
            models.add(this.container.get(i).toModel());
        }
        String json = gson.toJson(models);
        editor.putString("container", json);
        editor.apply();
    }

    public void readFile(Context context){

        File f = new File("/data/data/com.bitinbyte.ivycambridgebrassery/shared_prefs/container.xml");
        if (f.exists()) {
            Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
            SharedPreferences prefs = context.getSharedPreferences("container", context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = prefs.getString("container", "");
            Type type = new TypeToken<ArrayList<Articles.ArticleModel>>(){}.getType();
            List<Articles.ArticleModel> articleModels = new Gson().fromJson(json, type);
            getContainer().clear();
            for(int i = 0; i < articleModels.size(); i++){
                Articles a = new Articles(articleModels.get(i).getName(), articleModels.get(i).getTotalNumber());
                getContainer().add(a);
            }
        }else {
            Log.d("TAG", "Setup default preferences");
        }
    }*/

    public void saveFile(Context context) {

        try {

            FileOutputStream fos = context.openFileOutput("articles.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);


            oos.writeObject(getContainer());

            oos.close();
            fos.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, "File not fond", Toast.LENGTH_LONG).show();
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(context, "Not possible!", Toast.LENGTH_LONG).show();
        }

    }

    public void readFile(Context context){

        try {
            FileInputStream fis = context.openFileInput("articles.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            setContainer((ArrayList<Articles>) ois.readObject());

        }catch (IOException e){
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    public void showContentEditText(){
        this.screen.setText(this.articles.showArticles(this.container));
    }

    public void resetEditText(){
        this.editText.setText("");
    }

    public void setContainer(ArrayList<Articles> articles) { this.container = articles; }

    public ArrayList<Articles> getContainer() { return this.container; }
}
