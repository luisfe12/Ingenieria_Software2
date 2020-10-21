package com.example.basededatos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;//importamos clase para la bd

import androidx.annotation.Nullable;

//crea clase de la abse de datos
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //poner el constructor para el cuerpo base
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase Basededatos) {
        //creacion d etablas
        //creamos la tabla y sus atributos
        Basededatos.execSQL("create table Articulos(codigo int primary key, descripcion texto, precio real)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//herencia
}
