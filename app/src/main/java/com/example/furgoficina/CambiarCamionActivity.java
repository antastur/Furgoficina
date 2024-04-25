package com.example.furgoficina;


import static com.example.furgoficina.Utilidades.EXTINTOR;
import static com.example.furgoficina.Utilidades.FRENOS;
import static com.example.furgoficina.Utilidades.ITV;
import static com.example.furgoficina.Utilidades.KILOMETROS;
import static com.example.furgoficina.Utilidades.MATRICULA;
import static com.example.furgoficina.Utilidades.NOMBRE_TABLA_CAMION;
import static com.example.furgoficina.Utilidades.PERMISOCIRCULACION;
import static com.example.furgoficina.Utilidades.REPARACION;
import static com.example.furgoficina.Utilidades.REVISIONTAC;
import static com.example.furgoficina.Utilidades.RUEDAS;
import static com.example.furgoficina.Utilidades.SEGUROMERCANCIAS;
import static com.example.furgoficina.Utilidades.SEGUROVEHICULO;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.furgoficina3.R;


public class CambiarCamionActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de variables de atributos del camion
    EditText matricula;
    EditText extintor;
    EditText kilometros;
    EditText revTacografo;
    EditText itv;
    EditText segMercancias;
    EditText segVehiculo;
    EditText ruedas;
    EditText pastillas;
    EditText permCirculacion;
    EditText reparacion;
    Button modificar;
    AdminBD admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_camion);

        //Creacion de conexion a BD en esta activity
        admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion a variables del los EditText con datos de tipo date y asocialos a un evento click

        matricula=findViewById(R.id.textMatriculaCambiarCamion);
        extintor = findViewById(R.id.dateExtintorCambCamion);
        extintor.setOnClickListener(this);
        kilometros=findViewById(R.id.textkilometrosCambCamion);
        revTacografo = findViewById(R.id.dateRevTacografoCambCamion);
        revTacografo.setOnClickListener(this);
        itv = findViewById(R.id.dateItvCambCamion);
        itv.setOnClickListener(this);
        segMercancias = findViewById(R.id.dateSegMercanciasCambCamion);
        segMercancias.setOnClickListener(this);
        segVehiculo = findViewById(R.id.dateSegVehiculoCambCamion);
        segVehiculo.setOnClickListener(this);
        ruedas = findViewById(R.id.dateRuedasCambCamion);
        ruedas.setOnClickListener(this);
        pastillas = findViewById(R.id.datePastillasCambCamion);
        pastillas.setOnClickListener(this);
        permCirculacion = findViewById(R.id.datePermCirculacionCambCamion);
        permCirculacion.setOnClickListener(this);
        reparacion = findViewById(R.id.dateUltReparacionCambCamion);
        reparacion.setOnClickListener(this);
        modificar = findViewById(R.id.bVolverCambioCamion);
        modificar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        //Dependiendo en que EditText se hace clic se habre unDatePicker
        switch (view.getId()) {
            case R.id.dateExtintorCambCamion:
                showDatePickerDialog(extintor);
                break;
            case R.id.dateRevTacografoCambCamion:
                showDatePickerDialog(revTacografo);
                break;
            case R.id.dateItvCambCamion:
                showDatePickerDialog(itv);
                break;
            case R.id.dateSegMercanciasCambCamion:
                showDatePickerDialog(segMercancias);
                break;
            case R.id.dateSegVehiculoCambCamion:
                showDatePickerDialog(segVehiculo);
                break;
            case R.id.dateRuedasCambCamion:
                showDatePickerDialog(ruedas);
                break;
            case R.id.datePastillasCambCamion:
                showDatePickerDialog(pastillas);
                break;
            case R.id.datePermCirculacionCambCamion:
                showDatePickerDialog(permCirculacion);
                break;
            case R.id.dateUltReparacionCambCamion:
                showDatePickerDialog(reparacion);
                break;
            case R.id.bVolverCambioCamion:

            //Al hacer clic en el boton modificar se ejecuta el metodo cambiarCamion que cambia los campos rellenados de la matricula insertada
            cambiarCamion(matricula.getText().toString());
        }
    }




    //Metodo para cambiar campos de la tabla camion
    public void cambiarCamion(String mat){

        //Se inicia la BD
        SQLiteDatabase db = admin.getWritableDatabase();

        /*Para saber que valores introducir en los campos que no se rellenen (mantener los antiguos)
        primero se hace uba consulta de los valores actuales del Camion cuya matricula se ha introducido.Estos valores seran los que se incluiran
        en los campos que no se rellenen(no interese cambiar) */

        //Se instancian arreglos para la consulta , pimero los campos a mostrar,se importan los String de las constantes de Utilidades
        String[] consultas={ KILOMETROS, EXTINTOR, REVISIONTAC, ITV, SEGUROMERCANCIAS,
                SEGUROVEHICULO, RUEDAS, FRENOS, PERMISOCIRCULACION, REPARACION};
        String[] clave={matricula.getText().toString() };

        //Se hace la consulta
        Cursor cursor = db.query(NOMBRE_TABLA_CAMION, consultas, MATRICULA + "=?",clave , null,null,null);
        //Se situa al inicio del cursor devuelto y se recogen los resultados en String
        cursor.moveToFirst();
        String iniMat=matricula.getText().toString();
        String iniKil=cursor.getString(0);
        String iniExt=cursor.getString(1);
        String iniRev=cursor.getString(2);
        String iniItv=cursor.getString(3);
        String iniMer=cursor.getString(4);
        String iniVeh=cursor.getString(5);
        String iniRue=cursor.getString(6);
        String iniFre=cursor.getString(7);
        String iniPer=cursor.getString(8);
        String iniRep=cursor.getString(9);

        //Se prepara un arreglo y contenedor para la orden update
        String[] parametros ={matricula.getText().toString()};

        ContentValues cambios = new ContentValues();

        //Si el EditText esta vacio se dejan los valores antiguos ,si se han rellenado se rellena el ContentValues con ellos
        if (kilometros.getText().toString().isEmpty()){
            cambios.put(KILOMETROS,iniKil);
        }else{cambios.put(KILOMETROS,kilometros.getText().toString());}

        if (extintor.getText().toString().isEmpty()){
            cambios.put(EXTINTOR,iniExt);
        }else{
            cambios.put(EXTINTOR,extintor.getText().toString());
        }
        if (revTacografo.getText().toString().isEmpty()){
            cambios.put(REVISIONTAC,iniRev);
        }else{
            cambios.put(REVISIONTAC,revTacografo.getText().toString());
        }
        if (itv.getText().toString().isEmpty()){
            cambios.put(ITV,iniItv);
        }else{
            cambios.put(ITV,itv.getText().toString());
        }
        if (segMercancias.getText().toString().isEmpty()){
            cambios.put(SEGUROMERCANCIAS,iniMer);
        }else {
            cambios.put(SEGUROMERCANCIAS, segMercancias.getText().toString());
        }
        if (segVehiculo.getText().toString().isEmpty()){
            cambios.put(SEGUROVEHICULO,iniVeh);
        }else{
            cambios.put(SEGUROVEHICULO,segVehiculo.getText().toString());
        }
        if (ruedas.getText().toString().isEmpty()){
            cambios.put(RUEDAS,iniRue);
        }else{
            cambios.put(RUEDAS,ruedas.getText().toString());
        }
        if (pastillas.getText().toString().isEmpty()){
            cambios.put(FRENOS,iniFre);
        }else{
            cambios.put(FRENOS,pastillas.getText().toString());
        }
        if (permCirculacion.getText().toString().isEmpty()){
            cambios.put(PERMISOCIRCULACION,iniPer);
        }else{
            cambios.put(PERMISOCIRCULACION,permCirculacion.getText().toString());
        }
        if (reparacion.getText().toString().isEmpty()){
            cambios.put(REPARACION,iniRep);
        }else{
            cambios.put(REPARACION,reparacion.getText().toString());
        }

        //Se ejecuta la consulta y se preparan mensajes por si se realiza correctamente o no
            try {
                db.update(NOMBRE_TABLA_CAMION, cambios, MATRICULA + "=?", parametros);
                Toast toast1 = Toast.makeText(getApplicationContext(), "Modificacion realizada", Toast.LENGTH_SHORT);
                toast1.show();
            }catch(Exception e){
                Toast toast2 = Toast.makeText(getApplicationContext(), "No se ha podido hacer la modifiacion", Toast.LENGTH_SHORT);
                toast2.show();
            }
        //Se cierra la BD
        db.close();
        //Se limpian los EditText
        matricula.setText("");
        limpiar();

    }





    //Metodo boton volverConsulta
    public void volver(View view){
        Intent vuelta = new Intent(this, GestionCamion.class);
        startActivity(vuelta);
    }




    //Metodo para vaciar los EditText
    private void limpiar(){
        extintor.setText("");
        kilometros.setText("");
        revTacografo.setText("");
        itv.setText("");
        segMercancias.setText("");
        segVehiculo.setText("");
        segMercancias.setText("");
        ruedas.setText("");
        pastillas.setText("");
        permCirculacion.setText("");
        reparacion.setText("");
    }




    //Metodo para preparar un DatePicker y rellenar el EditText con lo elegido en el
    private void showDatePickerDialog(EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque Enero es 0
                final String selectedDate = twoDigits(year)+ "-" + (month+1) + "-" + twoDigits(day);
                editText.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }




    //Metodo para que el String que represente la fecha en los EditText conste de 2 digitos para los dias y meses
    private String twoDigits(int n){
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }


}