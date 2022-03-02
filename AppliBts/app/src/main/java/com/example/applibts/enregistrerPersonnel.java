package com.example.applibts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class enregistrerPersonnel extends AppCompatActivity {
    ClassBD db;
    TextView textListe;
    EditText nomInput,prenomInput,adresseInput,professionInput,ntelInput,mailInput;

    //Initialisation des variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_personnel);
        db = new ClassBD(this);

        nomInput = (EditText) findViewById(R.id.editTextNom);
        prenomInput = (EditText) findViewById(R.id.editTextPrenom);
        adresseInput = (EditText) findViewById(R.id.editTextAdresse);
        professionInput = (EditText) findViewById(R.id.editTextprofession);
        ntelInput = (EditText) findViewById(R.id.editTextTelephone);
        mailInput = (EditText) findViewById(R.id.editTextMail);

    }

    //Enregistrer un personnel dans la BD
    public void insertionClic(View view){
        db.insertData(nomInput.getText().toString(),prenomInput.getText().toString(),
                adresseInput.getText().toString(),professionInput.getText().toString(),
                      ntelInput.getText().toString(), mailInput.getText().toString());


    }
    // mise a jour de la liste
    public void majListe(){
        try{
            Cursor data = db.getAllData();
            String texte ="";
            while(data.moveToNext()){
                texte = texte + String.valueOf(data.getString(1)+" "+ data.getString(2)+" " + data.getString(3)+" "
                        +" " +data.getString(4)+" "+ data.getString(5)+" " + data.getString(6));
            }
            textListe.setText(texte);
        } catch (Exception e){
            textListe.setText(e.getMessage());
        }
    }

    // Redirection de pages
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