package com.example.applibts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

import java.io.StringReader;
import java.util.ArrayList;

// initilisation des variables
public class PrendreRdv extends AppCompatActivity {
    ClassBD db;
    Spinner listPro,listHeure,listeNom,heure;
    int idSelect;
    String[] heures={"8h","9h","10h","11h","12h","14h","15h","16h","17h","18h"};
    int rangSelect,id1;
    CalendarView calendar;
    String curDate;
    ArrayList<String> lesPros;
    ArrayList<String> listeIdPros;


    //Attribution des valeurs a nos items au lancement de la page + connexion a la BD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prendre_rdv);
        db = new ClassBD(this);
        heure = findViewById(R.id.spinner3);
        listPro = findViewById(R.id.spinner4);
        listHeure = findViewById(R.id.spinner3);
        calendar = findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                curDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1);
            }});



        // Création de nos tableaux + Ajout des valeurs dans nos tableaux
        try {
            Cursor data = db.getAllData();
             listeIdPros = new ArrayList<String>();
            lesPros = new ArrayList<String>();
            while (data.moveToNext()) {
                listeIdPros.add(data.getString(0));
                lesPros.add(data.getString(1) + " " + data.getString(2));
            }

            //Selectionner le professionnel pour le rendez vous
            ArrayAdapter<String> RDV = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesPros);
            RDV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            listPro.setAdapter(RDV);

            listPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    idSelect = position;

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {


        }
    //Selectionner l'heure du rendez-vous
        ArrayAdapter<String> lesHeures = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, heures);
        lesHeures.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heure.setAdapter(lesHeures);

        heure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rangSelect = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    //Enregistrement du rendez vous
    public void PriseRDV (View view){

        db.insertData2(curDate.toString() , heures[rangSelect], Integer.valueOf(listeIdPros.get(idSelect)));
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