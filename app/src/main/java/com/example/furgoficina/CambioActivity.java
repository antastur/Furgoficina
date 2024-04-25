package com.example.furgoficina;

import static com.example.furgoficina.Utilidades.ADR;
import static com.example.furgoficina.Utilidades.CARNETCONDUCIR;
import static com.example.furgoficina.Utilidades.DNI;
import static com.example.furgoficina.Utilidades.NOMBRE;
import static com.example.furgoficina.Utilidades.NOMBRE_TABLA_CONDUCTOR;
import static com.example.furgoficina.Utilidades.TARJETACAP;
import static com.example.furgoficina.Utilidades.TARJETATACOGRAFOCONDUCTOR;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.furgoficina3.R;




//Clase de la Activity en que se manejan los componentes para realizar modificaciones en la base de datos
public class CambioActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de variables de atributos del camion
    EditText dni;
    EditText nombre;
    EditText tarjetaCap;
    EditText tarjetaTacografo;
    EditText carnetConducir;
    EditText adr;
    AdminBD admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio);

        //Creacion de conexion a BD en esta activity
        admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion a variables del los EditText con datos de tipo date y asocialos a un evento click
        dni = findViewById(R.id.textdniMod);

        nombre = findViewById(R.id.dateNombCambio);

        tarjetaCap = findViewById(R.id.dateCapCambio2);
        tarjetaCap.setOnClickListener(this);
        tarjetaTacografo = findViewById(R.id.dateTarjTacogCambio);
        tarjetaTacografo.setOnClickListener(this);
        carnetConducir = findViewById(R.id.datePermConducirCambio);
        carnetConducir.setOnClickListener(this);
        adr=findViewById(R.id.dateAdrCambio);
        adr.setOnClickListener(this);
        //Limpiar los EditText
        limpiar();
    }


    @Override
    public void onClick(View view) {

        //Dependiendo en que EditText se hace clic se abre unDatePicker
        switch (view.getId()) {

            case R.id.dateNombCambio:

            case R.id.dateCapCambio2:
                showDatePickerDialog(tarjetaCap);

                break;
            case R.id.dateTarjTacogCambio:
                showDatePickerDialog(tarjetaTacografo);

                break;
            case R.id.datePermConducirCambio:
                showDatePickerDialog(carnetConducir);

                break;
            case R.id.dateAdrCambio:
                showDatePickerDialog(adr);

                break;
            case R.id.bvolverConsultar3:


                //Al hacer clic en el boton modificar se ejecuta el metodo cambiarConductor que cambia los campos rellenados del dni insertado
                cambiarConductor(dni.getText().toString());
        }
    }






    //Metodo para cambiar los datos de los campos de la tabla Conductor
    public void cambiarConductor(String entDni) {
        //Se inicia la BD
        SQLiteDatabase db = admin.getWritableDatabase();

        /*Para saber que valores introducir en los campos que no se rellenen (mantener los antiguos)
        primero se hace uba consulta de los valores actuales del Conductor cuyo dni se ha introducido.Estos valores seran los que se incluiran
        en los campos que no se rellenen(no interese cambiar */

        //Se instancian arreglos para la consulta , pimero los campos a mostrar,se importan los String de las constantes de Utilidades
        String[] consultas={ NOMBRE, TARJETACAP, TARJETATACOGRAFOCONDUCTOR, CARNETCONDUCIR, ADR};
        String[] clave={dni.getText().toString() };
        //Se hace la consulta
        Cursor cursor = db.query(NOMBRE_TABLA_CONDUCTOR, consultas, DNI + "=?",clave , null,null,null);
        //Se situa al inicio del cursor devuelto y se recogen los resultados en String
        cursor.moveToFirst();
        String iniDni=dni.getText().toString();
        String iniNom=cursor.getString(0);
        String iniCap=cursor.getString(1);
        String iniTac=cursor.getString(2);
        String iniCar=cursor.getString(3);
        String iniAdr=cursor.getString(4);

        //Se prepara un arreglo y contenedor para la orden update
        String[] parametros ={dni.getText().toString()};

        ContentValues cambios = new ContentValues();

        //Si el EditText esta vacio se dejan los valores antiguos ,si se han rellenado se rellena el ContentValues con ellos
        if (nombre.getText().toString().isEmpty()){
            cambios.put(NOMBRE,iniNom);
        }else{cambios.put(NOMBRE,nombre.getText().toString());}

        if (tarjetaCap.getText().toString().isEmpty()){
            cambios.put(TARJETACAP,iniCap);
        }else{
            cambios.put(TARJETACAP,tarjetaCap.getText().toString());
        }
        if (tarjetaTacografo.getText().toString().isEmpty()){
            cambios.put(TARJETATACOGRAFOCONDUCTOR,iniTac);
        }else{
            cambios.put(TARJETATACOGRAFOCONDUCTOR,tarjetaTacografo.getText().toString());
        }
        if (carnetConducir.getText().toString().isEmpty()){
            cambios.put(CARNETCONDUCIR,iniCar);
        }else{
            cambios.put(CARNETCONDUCIR,carnetConducir.getText().toString());
        }
        if (adr.getText().toString().isEmpty()){
            cambios.put(ADR,iniAdr);
        }else {
            cambios.put(ADR, adr.getText().toString());
        }
            //Se ejecuta la consulta y se preparan mensajes por si se realiza correctamente o no
        try {
            db.update(NOMBRE_TABLA_CONDUCTOR, cambios, DNI + "=?", parametros);
            Toast toast1 = Toast.makeText(getApplicationContext(), "Modificacion realizada", Toast.LENGTH_SHORT);
            toast1.show();
        }catch(Exception e){
            Toast toast2 = Toast.makeText(getApplicationContext(), "No se ha podido hacer la modifiacion", Toast.LENGTH_SHORT);
            toast2.show();
        }
            //Se cierra la BD
            db.close();
            //Se limpian los EditText
            dni.setText("");
            limpiar();
        }




    //Metodo boton volverConsulta
    public void volver(View view){
        Intent vuelta = new Intent(this, GestionConductor.class);
        startActivity(vuelta);
    }



    //Metodo para preparar un DatePicker y rellenar el EditText con lo elegido en el
    private void showDatePickerDialog(EditText editText) {

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque Enero es 0
                final String selectedDate = twoDigits(year) + "-" + (month+1) + "-" + twoDigits(day);
                editText.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }




    //Metodo para que el String que represente la fecha en los EditText conste de 2 digitos para los dias y meses
    private String twoDigits(int n){
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }




    private void limpiar(){
        nombre.setText("");
        tarjetaCap.setText("");
        tarjetaTacografo.setText("");
        carnetConducir.setText("");
        adr.setText("");
    }


}