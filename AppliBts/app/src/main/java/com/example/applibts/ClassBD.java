package com.example.applibts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
        //creation de mes tables + les colonnes
public class ClassBD extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gsb.db";
    public static final String TABLE_NAME = "enregistrer_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NOM";
    public static final String COL_3 = "PRENOM";
    public static final String COL_4 = "ADRESSE";
    public static final String COL_5 = "PROFESSION";
    public static final String COL_6 = "TELEPHONE";
    public static final String COL_7 = "MAIL";

    public static final String TABLE_NAME2 = "enregistrer_rdv";
    public static final String  COLL_1 = "idRdv";
    public static final String  COLL_2 = "dateRdv";
    public static final String  COLL_3 = "heureRdv";
    public static final String  COLL_4 = "idPro";

    public ClassBD(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    // Initilisation de la table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NOM TEXT, PRENOM TEXT " +
                ", ADRESSE TEXT , PROFESSION TEXT , TELEPHONE TEXT, MAIL TEXT)");

        db.execSQL("CREATE table " + TABLE_NAME2 + "(idRdv integer PRIMARY KEY AUTOINCREMENT, " +
                "" + "dateRdv TEXT, heureRdv TEXT, idPro INTEGER)");;
    }
    // modification d'une table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Permet d'inserer les valeurs(nom,prenom,adresse,profession,telephone,mail) dans la table

    public void insertData(String nom,String prenom,String adresse,String profession,String telephone,String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, nom);
        contentValues.put(COL_3, prenom);
        contentValues.put(COL_4, adresse);
        contentValues.put(COL_5, profession);
        contentValues.put(COL_6, telephone);
        contentValues.put(COL_7, mail);

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    // Permet d'inserer les valeurs(dateRdv,heureRdv,idPro) dans la table
    public void insertData2(String dateRdv, String heureRdv, Integer idPro){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();

        cv2.put(COLL_2, dateRdv);
        cv2.put(COLL_3, heureRdv);
        cv2.put(COLL_4, idPro);
        db.insert(TABLE_NAME2, null, cv2);
        db.close();
    }

    //Afficher les valeurs de la table
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+ TABLE_NAME,null);

        return result;
    }

        //Afficher un personnel en fonction de son adresse
    public Cursor getProByAdress(String editTextIndice){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result=db.rawQuery("select * from "+TABLE_NAME+" WHERE adresse LIKE '%"+editTextIndice+"%'", null);
        return result;
    }
        //Afficher les rendez-vous
    public Cursor getRdv(String curDate){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME2,null);
        return result;
    }


}
