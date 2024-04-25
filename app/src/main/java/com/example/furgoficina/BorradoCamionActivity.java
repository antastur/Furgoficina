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



public class BorradoCamionActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de variables
    EditText matricula;
    Button borrar;
    Button volver;
    AdminBD admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrado_camion);

        //Creacion de conexion a BD en esta activity
        admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion de los elementos de la activity a las variables
        matricula = findViewById(R.id.textPersonMatriculaBorrar);
        volver=findViewById(R.id.bvolverBorradoCamion3);
        borrar=findViewById(R.id.bvolverBorradoCamion);
        //Hacer listener al boton
        borrar.setOnClickListener(this);
    }




    //Metodo boton volverConsulta
    public void volver(View view){
        Intent vuelta = new Intent(this, GestionCamion.class);
        startActivity(vuelta);
    }



    @Override
    public void onClick(View view) {
        //Si el EditText esta vacio se advierte con mensaje
        if (matricula.getText().toString().isEmpty()) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Introduce la matricula del camion a borrar", Toast.LENGTH_SHORT);
            toast1.show();
            //Si no se recoge el string introducido en el
        } else {


            //Se instancia la BD y se prepara un arreglo en el que se transpasaran los datos
            SQLiteDatabase db = admin.getWritableDatabase();
            String[] parametros= {matricula.getText().toString()};
            //Si devuelve 1 se a producido el borrado y se da mensaje correspondiente
            //Se borra el que el dni coincida con el del EditText
            int borrado = db.delete(Utilidades.NOMBRE_TABLA_CAMION,""+Utilidades.MATRICULA+"=?",parametros );
            if (borrado == 1) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Camion borrado", Toast.LENGTH_SHORT);
                toast1.show();
                //Si no es asi se notifica
            } else {
                Toast toast1 = Toast.makeText(getApplicationContext(), "No existe camion con esa matricula", Toast.LENGTH_SHORT);
                toast1.show();
            }
            matricula.setText("");
        }
    }
}