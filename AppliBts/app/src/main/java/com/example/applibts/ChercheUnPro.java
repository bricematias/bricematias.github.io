package com.example.applibts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
// Initilisation des variables
public class ChercheUnPro extends AppCompatActivity {
    ClassBD db;
    Spinner listAdresse;
    int idSelect;
    EditText editTextIndice;
    ArrayList<String> listddresse;

    //Connexion bd + instantion des variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cherche_un_pro);
        db = new ClassBD(this);
        editTextIndice = findViewById(R.id.editTextIndice);
        listAdresse = findViewById(R.id.spinner2);
        listddresse = new ArrayList<String>();

    }

    //Cherche personnel
    public void majListe(View view){
        try {
            listddresse.clear();
            Cursor data = db.getProByAdress(editTextIndice.getText().toString());
            while (data.moveToNext()){
                listddresse.add(data.getString(1) + " " + data.getString(2));
            }

            ArrayAdapter<String> aaproChercher = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, listddresse);
            aaproChercher.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listAdresse.setAdapter(aaproChercher);
            listAdresse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    idSelect=position;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        catch(Exception e){
        }
    }

                        // Les redirections de pages
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