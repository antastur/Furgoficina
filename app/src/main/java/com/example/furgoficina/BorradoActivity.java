package com.example.furgoficina;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.furgoficina3.R;

public class BorradoActivity extends AppCompatActivity implements View.OnClickListener{
    //Declaracion de variables
    EditText dni;
    Button borrar;
    Button volver;
    AdminBD admin;

    //Metodo onCreate se inicia al comenzar la activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrado);

        //Creacion de conexion a BD en esta activity
        admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion de los elementos de la activity a las variables
        dni =(EditText) findViewById(R.id.textPersonMatriculaBorrar);
        volver=(Button) findViewById(R.id.bvolverBorradoCamion2);
        borrar=(Button) findViewById(R.id.bvolverBorradoCamion);
        //Hacer listener al botonj
        borrar.setOnClickListener(this);


    }



        //Metodo boton volverConsulta
    public void volver(View view){
        Intent vuelta = new Intent(this, GestionConductor.class);
        startActivity(vuelta);
    }



    //Metodo que realiza los eventos desencadenados por los distintos listeners en este caso el boton borrar
    @Override
    public void onClick(View view) {
        //Si el EditText esta vacio se advierte con mensaje
        if (dni.getText().toString().isEmpty()) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Introduce el DNI del conductor a borrar", Toast.LENGTH_SHORT);
            toast1.show();
            //Si no se recoge el string introducido en el y se pasa por parametro al metodo borrarConductor para hacerlo
        }else{
            //Se instancia la BD y se prepara un arreglo en el que se transpasaran los datos
            SQLiteDatabase db  = admin.getWritableDatabase();
            String [] aBorrar = {dni.getText().toString()};


            //Se borra el que el dni coincida con el del arreglo si devuelve 1 se a producido el borrado y se da mensaje correspondiente
            int borrado=db.delete(Utilidades.NOMBRE_TABLA_CONDUCTOR, Utilidades.DNI+"=?",aBorrar);
            if (borrado==1){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Conductor borrado", Toast.LENGTH_SHORT);
                toast1.show();
                //Si no es asi se notifica
            }else{
                Toast toast1 = Toast.makeText(getApplicationContext(), "No existe conductor con ese DNI", Toast.LENGTH_SHORT);
                toast1.show();
            }
            dni.setText("");

        }
    }
}
