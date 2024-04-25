package com.example.furgoficina;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.furgoficina3.R;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// Clase que maneja los componentes de la Activity encargada de insertar un nuevo camion en la tabla camiones
public class CamionActivity extends AppCompatActivity implements View.OnClickListener {
    //Declaracion de variables de atributos del camion
    EditText matricula;
    EditText extintor;
    EditText revTacografo;
    EditText itv;
    EditText segMercancias;
    EditText segVehiculo;
    EditText ruedas;
    EditText pastillas;
    EditText permCirculacion;
    EditText reparacion;
    EditText kilometros;
    AdminBD admin;


    //Sobreescritura de metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camion);

        //Creacion de conexion a BD en esta activity
        admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion a variables del los EditText con datos de tipo date y asocialos a un evento click
        matricula = (EditText)findViewById(R.id.textMatricula);
        kilometros= (EditText)findViewById(R.id.textkilometrosCamion);
        extintor =(EditText) findViewById(R.id.dateExtintorCamion);
        extintor.setOnClickListener(this);
        revTacografo = (EditText)findViewById(R.id.dateRevTacografoCamion);
        revTacografo.setOnClickListener(this);
        itv = (EditText)findViewById(R.id.dateItvCamion);
        itv.setOnClickListener(this);
        segMercancias =(EditText)findViewById(R.id.dateSegMercanciasCamion);
        segMercancias.setOnClickListener(this);
        segVehiculo = (EditText)findViewById(R.id.dateSegVehiculoCamion);
        segVehiculo.setOnClickListener(this);
        ruedas = (EditText)findViewById(R.id.dateRuedasCamion);
        ruedas.setOnClickListener(this);
        pastillas =(EditText) findViewById(R.id.datePastillasCamion);
        pastillas.setOnClickListener(this);
        permCirculacion = (EditText)findViewById(R.id.datePermCirculacionCamion);
        permCirculacion.setOnClickListener(this);
        reparacion =(EditText) findViewById(R.id.dateUltReparacionCamion);
        reparacion.setOnClickListener(this);


    }




    //Metodo boton volver
    public void volver(View view) {
        Intent vuelta = new Intent(this, GestionCamion.class);
        startActivity(vuelta);
    }



    @Override
    public void onClick(View view) {
        //Dependiendo en que EditText se hace clic se habre unDatePicker
        switch (view.getId()) {
            case R.id.dateExtintorCamion:
                showDatePickerDialog(extintor);
                break;
            case R.id.dateRevTacografoCamion:
                showDatePickerDialog(revTacografo);
                break;
            case R.id.dateItvCamion:
                showDatePickerDialog(itv);
                break;
            case R.id.dateSegMercanciasCamion:
                showDatePickerDialog(segMercancias);
                break;
            case R.id.dateSegVehiculoCamion:
                showDatePickerDialog(segVehiculo);
                break;
            case R.id.dateRuedasCamion:
                showDatePickerDialog(ruedas);
                break;
            case R.id.datePastillasCamion:
                showDatePickerDialog(pastillas);
                break;
            case R.id.datePermCirculacionCamion:
                showDatePickerDialog(permCirculacion);
                break;
            case R.id.dateUltReparacionCamion:
                showDatePickerDialog(reparacion);
                break;
            case R.id.textkilometrosCamion:
                showDatePickerDialog(kilometros);
                break;
            //Caso de hacer clic en siguiente
            case R.id.bSiguiente:

                //Se recogen en String Los datos introducidos por el usuario en los EditText cuidando el tipo y el formato
                String stmatricula = matricula.getText().toString();
                String kilom = kilometros.getText().toString();
                String stextintor = extintor.getText().toString();
                String strevTacografo = revTacografo.getText().toString();
                String stitv = itv.getText().toString();
                String stsegMercancias = segMercancias.getText().toString();
                String stsegVehiculo = segVehiculo.getText().toString();
                String struedas = ruedas.getText().toString();
                String stpastillas = pastillas.getText().toString();
                String stpermCirculacion = permCirculacion.getText().toString();
                String streparacion = reparacion.getText().toString();

                //Si alguno de los EditText esta vacio lanza mensaje advirtiendolo
                if ((stmatricula.isEmpty()) || (kilom.isEmpty()) || (stextintor.isEmpty()) || (strevTacografo.isEmpty()) || (stitv.isEmpty()) || (stsegMercancias.isEmpty()) || (stsegVehiculo.isEmpty()) ||
                        (struedas.isEmpty()) || (stpastillas.isEmpty()) || (stpermCirculacion.isEmpty()) || (streparacion.isEmpty())) {

                    Toast toast1 = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT);
                    toast1.show();

                    //Si no se crea un objeto Camion con los atributos introducidos por el usuario
                } else {

                    //Se ejecuta la funcion insertarCamion y dependiendo de lo que retorna se da un mensaje u otro
                    try {
                        if (insertarCamion(stmatricula, kilom, stextintor, strevTacografo,
                                stitv, stsegMercancias, stsegVehiculo, struedas, stpastillas,
                                stpermCirculacion, streparacion)) {
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Conductor introducido", Toast.LENGTH_SHORT);
                            toast1.show();
                        } else {
                            Toast toast2 = Toast.makeText(getApplicationContext(), "Conductor no introducido", Toast.LENGTH_SHORT);
                            toast2.show();
                        }
                    } catch (SQLiteConstraintException e) {
                        Toast toast3 = Toast.makeText(getApplicationContext(), "Conductor ya existe", Toast.LENGTH_SHORT);
                        toast3.show();
                    }
                    //Se vacian los EditText
                    matricula.setText("");
                    kilometros.setText("");
                    extintor.setText("");
                    revTacografo.setText("");
                    itv.setText("");
                    segMercancias.setText("");
                    segVehiculo.setText("");
                    ruedas.setText("");
                    pastillas.setText("");
                    permCirculacion.setText("");
                    reparacion.setText("");
                }
        }
    }





    //Metodo para ingresar un registro en la tabla camion
    private Boolean insertarCamion(String stmatricula,String kilom,String stextintor,String strevTacografo,
                                  String stitv,String stsegMercancias,String stsegVehiculo,String struedas,
                                  String stpastillas,String stpermCirculacion,String streparacion) throws SQLiteConstraintException {
        //Se instancia la BD y se prepara el objeto content en el que se transpasaran los datos
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues content = new ContentValues();

        //Se prepara el objeto contenedorteniendo en cuenta las conversiones entre tipos y formatos
        content.put(Utilidades.MATRICULA, stmatricula);
        content.put(Utilidades.KILOMETROS, Integer.parseInt(kilom));
        content.put(Utilidades.EXTINTOR, stextintor);
        content.put(Utilidades.REVISIONTAC, strevTacografo);
        content.put(Utilidades.ITV, stitv);
        content.put(Utilidades.SEGUROMERCANCIAS, stsegMercancias);
        content.put(Utilidades.SEGUROVEHICULO, stsegVehiculo);
        content.put(Utilidades.RUEDAS, struedas);
        content.put(Utilidades.FRENOS, stpastillas);
        content.put(Utilidades.PERMISOCIRCULACION, stpermCirculacion);
        content.put(Utilidades.REPARACION, streparacion);

        //Se inserta el registro en la BD y devuelve true si es asì

        Long result = db.insert(Utilidades.NOMBRE_TABLA_CAMION, null, content);

        //Se cierra la BD
        db.close();
        //Se devuelve true si se realiza insercion
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }




    private void showDatePickerDialog(EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque Enero es 0
                final String selectedDate = year + "-" + twoDigits(month+1) + "-" + twoDigits(day);
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
