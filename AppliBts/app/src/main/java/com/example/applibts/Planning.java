package com.example.applibts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
// initilisation des variables
public class Planning extends AppCompatActivity {

    CalendarView calendarView2;
    ArrayList<String> Plannings;
    ClassBD db;
    Spinner Pro;
    String curDate;
    Cursor data;
    int id2;

//Connexion bd + init des variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        db = new ClassBD(this);

        calendarView2 = findViewById(R.id.calendarView2);
        Pro = findViewById(R.id.spinner);
        Plannings = new ArrayList<>();

        calendarView2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                curDate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1);
            }
        });


    }

    public void AffichPlanning(View view) {
    //Ajout des rendez vous avec les informations
        try{
            data =db.getRdv(curDate);
            ArrayList<String>listePlanning= new ArrayList<String>();
            String liste="";
            while (data.moveToNext()){
                listePlanning.add (" RDV n° " + data.getInt(0)+ " le " +
                        data.getString(1) + " à " + data.getString(2) + "h " +
                        " avec pro n° " + data.getString(3));
            }
            //Insertion des valeurs dans le spinner associés
            ArrayAdapter<String> RDV = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, listePlanning);
            RDV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Pro.setAdapter(RDV);
            Pro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    id2=position;
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

        }}
        //Redirection de pages
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