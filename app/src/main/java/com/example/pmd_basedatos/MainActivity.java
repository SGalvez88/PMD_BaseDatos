package com.example.pmd_basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String NOMBRE_DB = "pruebaDB.db";
    private static final int VERSION_ACTUAL = 1;

    private MiBaseDatos miBaseDatos;
    private SQLiteDatabase sqLiteDatabase;

    private Button botonInsertar;
    private Button botonBorrar;
    private Button botonModificar;
    private Button botonConsultar;
    private TextView texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonInsertar = (Button) findViewById(R.id.bInsertar);
        botonBorrar = (Button) findViewById(R.id.bBorrar);
        botonModificar = (Button) findViewById(R.id.bModificar);
        botonConsultar = (Button) findViewById(R.id.bConsultar);
        texto = (TextView) findViewById(R.id.textView);

        botonInsertar.setOnClickListener(this);
        botonBorrar.setOnClickListener(this);
        botonModificar.setOnClickListener(this);
        botonConsultar.setOnClickListener(this);

        miBaseDatos= new MiBaseDatos(getApplicationContext(), NOMBRE_DB,null ,VERSION_ACTUAL);
        sqLiteDatabase = miBaseDatos.getWritableDatabase();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bInsertar:

                sqLiteDatabase.execSQL("INSERT INTO pruebas (codigo,nombre) VALUES(1,'Corsair')");

                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("codigo",2);
                nuevoRegistro.put("nombre", "GForce");
                sqLiteDatabase.insert("pruebas",null,nuevoRegistro);

                Toast.makeText(getApplicationContext(),"Registro Insertado",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bBorrar:
                break;
            case R.id.bModificar:
                break;
            case R.id.bConsultar:
                break;
        }
    }
}