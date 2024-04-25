package com.example.furgoficina;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



//Clase para creación y manejo de una BD SQLITE
public class AdminBD extends SQLiteOpenHelper {



    public AdminBD( Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Metodo onCreate, se ejecuta al instanciar la clase
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se crea una BD con tablas que incluyen los atributos de los objetos Camion y Conductor

        db.execSQL(Utilidades.CREAR_TABLA_CONDUCTORES);

        db.execSQL(Utilidades.CREAR_TABLA_CAMIONES);

    }



    //Borrado y creacion de una tabla para darle un numero de version mayor
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {


        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.NOMBRE_TABLA_CONDUCTOR);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.NOMBRE_TABLA_CAMION);

        onCreate(db);
    }





}
