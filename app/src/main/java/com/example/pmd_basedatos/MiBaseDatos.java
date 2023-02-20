package com.example.pmd_basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MiBaseDatos extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE pruebas(codigo INTEGER, nombre TEXT)";

    public MiBaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versionAnterior, int versionNueva ) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pruebas");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
