package com.example.pmd_basedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
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
                sqLiteDatabase.execSQL("DELETE FROM pruebas WHERE codigo=1");
                sqLiteDatabase.delete("pruebas","codigo="+2,null);
                Toast.makeText(getApplicationContext(),"Regitro Borrado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bModificar:

                sqLiteDatabase.execSQL("UPDATE pruebas SET nombre='Intel' WHERE codigo=1");

                ContentValues valores = new ContentValues();
                valores.put("nombre","AMD");
                sqLiteDatabase.update("pruebas",valores,"codigo="+2,null);
                Toast.makeText(getApplicationContext(),"Registro modificado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bConsultar:

                Cursor miCursor = sqLiteDatabase.rawQuery("SELECT codigo,nombre FROM pruebas",null);
                texto.setText("");
                if(miCursor.moveToFirst()){
                    do{
                        String codigo = miCursor.getString(0);
                        String nombre = miCursor.getString(1);
                        texto.append(" "+codigo+" - "+nombre+"\n");
                    }while (miCursor.moveToNext());
                }
                miCursor.close();
                break;
        }
    }
}