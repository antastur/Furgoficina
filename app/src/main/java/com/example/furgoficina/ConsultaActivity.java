package com.example.furgoficina;

import static com.example.furgoficina.Utilidades.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.furgoficina3.R;



public class ConsultaActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de variables
    EditText dni;
    TextView nombre;
    TextView tarjetaCap;
    TextView tarjetaTacografo;
    TextView carnetConducir;
    TextView adr;
    Button buscar;
    Button volver;
    AdminBD admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        //Creacion de conexion a BD en esta activity
        admin = new AdminBD(this, DB_Name, null, DB_VERSION);

        //Asignacion a variables del los EditText con datos de tipo date y asocialos a un evento click
        dni = (EditText) findViewById(R.id.textdniConsulta);

        //Mapeado de los TExtViews y creacion de listener
        nombre = (TextView) findViewById(R.id.textPersonNameConsulta);
        tarjetaCap = (TextView) findViewById(R.id.dateCapConsulta);
        tarjetaTacografo = (TextView) findViewById(R.id.dateTarjTacogConsulta);
        carnetConducir = (TextView) findViewById(R.id.datePermConducirCons);
        adr = (TextView) findViewById(R.id.dateAdrConsulta);
        buscar = (Button) findViewById(R.id.bvolverConsultar4);
        //Para que al hacer clic en el se ejecute el codigo del metodo onClick
        buscar.setOnClickListener(this);
        volver = (Button) findViewById(R.id.bvolverConsultar);


    }


    @Override
    public void onClick(View view) {

        //Si no se ha rellenado el EditText para el Dni se manda aviso
        if (dni.getText().toString().isEmpty()) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "Introduce la matrícula", Toast.LENGTH_SHORT);

            toast1.show();
            //Si se introduce un dni
        } else {
            //Se ejecuta el metodo verConductor pasandole por parametro el dni introducido en el EditText por usuario
            verConductor(dni.getText().toString());
        }
    }


    //Metodo boton volverConsulta
    public void volver(View view) {
        Intent vuelta = new Intent(this, GestionConductor.class);
        startActivity(vuelta);
    }


    //Metodo que realiza una consulta pasando por parametro un dni y devuelve un objeto booleano que nos indicara si ha encontrado resultados a la busqueda
    public void verConductor(String entDni) {

        //Se instancia la BD y se prepara el objeto content en el que se transpasaran los datos
        SQLiteDatabase db = admin.getReadableDatabase();
        //Se instancian arreglos para la consulta , pimero los campos a mostrar,se importan los String de las constantes de Utilidades
        String[] consultas={ NOMBRE, TARJETACAP, TARJETATACOGRAFOCONDUCTOR, CARNETCONDUCIR, ADR};
        //Despues del campo que servira como llave de la busqueda(dni)
        String[] clave={entDni };
        try {
            //Se hace la consulta
            Cursor cursor = db.query(NOMBRE_TABLA_CONDUCTOR, consultas, DNI + " = ?", clave, null,null,null);
            //Se situa al inicio del cursor devuelto y se recogen los resultados mostrandolos en sus correspondientes TextViews
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            tarjetaCap.setText(cursor.getString(1));
            tarjetaTacografo.setText(cursor.getString(2));
            carnetConducir.setText(cursor.getString(3));
            adr.setText(cursor.getString(4));
            //Se cierra el cursor
            cursor.close();

        //Si la consulta arroja excepcion se da mensaje de advertencia
        }catch(Exception e){
            Toast toast1 = Toast.makeText(getApplicationContext(), "No se ha encontrado ese conductor", Toast.LENGTH_SHORT);
            toast1.show();
        }


    }

}