package com.example.applibts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ClassBD db;
        setContentView(R.layout.activity_main);
        EditText nomInput,prenomInput,classeInput;


    }


    public void Enregistrer(View view){
        Intent intentAfficher = new Intent(this, enregistrerPersonnel.class);
        startActivity(intentAfficher);
    }
    public void Rdv(View view){
        Intent intentAfficher = new Intent(this, PrendreRdv.class);
        startActivity(intentAfficher);
    }
    public void Planning(View view){
        Intent intentAfficher = new Intent(this, Planning.class);
        startActivity(intentAfficher);
    }
    public void Personnel(View view){
        Intent intentAfficher = new Intent(this, ChercheUnPro.class);
        startActivity(intentAfficher);
    }




}