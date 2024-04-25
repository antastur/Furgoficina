package com.example.furgoficina;


import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.furgoficina3.R;
import data.Conductor;


public class ConductorActivity extends AppCompatActivity implements View.OnClickListener {
    //Declaracion de variables
    EditText dni;
    EditText nombre;
    EditText tarjetaCap;
    EditText tarjetTacografo;
    EditText carnetConducir;
    EditText adr;
    Button siguiente;
    Conductor conductor;
    AdminBD admin;

   //Metodo onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);

        //Instanciación de clase AdminBd que gestiona el acceso a la base de datos
        admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion a variables del los EditText y botones y asociar los de tipo Date a un evento click para activar un DatePicker
        //el boton de siguiente se have escuchador de evento onclick
        dni = (EditText)findViewById(R.id.dniConductor);
        nombre =(EditText) findViewById(R.id.nombConductor);
        tarjetaCap = (EditText)findViewById(R.id.tarjCap);
        tarjetaCap.setOnClickListener(this);
        tarjetTacografo =(EditText) findViewById(R.id.tarjTacogConductor);
        tarjetTacografo.setOnClickListener(this);
        carnetConducir =(EditText) findViewById(R.id.dateCarnetConducir);
        carnetConducir.setOnClickListener(this);
        adr=(EditText)findViewById(R.id.dateAdrConductor);
        adr.setOnClickListener(this);
        siguiente=(Button) findViewById(R.id.bSiguienteCond);
        siguiente.setOnClickListener(this);

    }

    //Metodo boton volver para volver a la Activity anterior
    public void volver(View view) {
        Intent vuelta = new Intent(this, GestionConductor.class);
        startActivity(vuelta);
    }


    //Metodo onClick en el que se desarrollan las acciones que llevarán a cabo los distintos listeners
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //Si se hace clic sobre distintos EditText se muestra DatePicker de cada uno
            case R.id.tarjCap:
                showDatePickerDialog(tarjetaCap);
                break;
            case R.id.tarjTacogConductor:
                showDatePickerDialog(tarjetTacografo);
                break;
            case R.id.dateCarnetConducir:
                showDatePickerDialog(carnetConducir);
                break;
            case R.id.dateAdrConductor:
                showDatePickerDialog(adr);
                break;
            //Si se hace clic en boton siguiente...
            case R.id.bSiguienteCond:
                //Se instancia un DateFormat para trabajar con Date y String
               // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //Se recogen en variables tipo String los datos introducidos por el usuario en los distintos EditText
                String stdni = dni.getText().toString();
                String stnombre = nombre.getText().toString();
                String sttarjCap =tarjetaCap.getText().toString();
                String sttarjTac = tarjetTacografo.getText().toString();
                String stCarnCond = carnetConducir.getText().toString();
                String stadr = adr.getText().toString();
                //Si alguno de  los EditText estan vacios se advierte con un mensaje
                if ((stdni.isEmpty()) || (stnombre.isEmpty()) || (sttarjCap.isEmpty()) || (sttarjTac.isEmpty()) || (stCarnCond.isEmpty()) || (stadr.isEmpty())) {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT);
                    toast1.show();

                    //Si no falta ningun campo por rellenar
                } else {

                    //Se inserta en Bd un conductor con los string recogidos en los EditText pasados como parametro
                    //y si devuelve true se lanza mensaje de confirmacion
               try{ if(insertarConductor(stdni,stnombre,sttarjCap,sttarjTac,stCarnCond,stadr)){
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Conductor introducido", Toast.LENGTH_SHORT);
                        toast2.show();
                        }else{ Toast toast3 = Toast.makeText(getApplicationContext(), "Conductor ya existe", Toast.LENGTH_SHORT);
                                toast3.show();}
                   }catch (Exception e){
                        //Si lanza SQLException , la primary key se repite ,se lanza mensaje advirtiendolo
                        Toast toast4 = Toast.makeText(getApplicationContext(), "Conductor no introducido", Toast.LENGTH_SHORT);
                         toast4.show();
                        }

                     //Se vacian los EditText
                       dni.setText("");
                       nombre.setText("");
                       tarjetaCap.setText("");
                       tarjetTacografo.setText("");
                       carnetConducir.setText("");
                       adr.setText("");
                   }

                }
    }




        //Metodo para añadir un nuevo conductor a la tabla pasando por parametro los datos recogidos en los EditText
        private Boolean insertarConductor(String dni,String name,String TarjetaCap,String TarjetaTac,String CarnetConducir,String adr) throws SQLiteConstraintException{

        //Creacion de conexion a BD en esta activity  y se prepara el objeto content en el que se transpasaran los datos

        SQLiteDatabase db  = admin.getWritableDatabase();
        ContentValues content= new ContentValues();

        //Se prepara el objeto contenedor teniendo en cuenta las conversiones entre tipos y formatos
        content.put(Utilidades.DNI,dni);
        content.put(Utilidades.NOMBRE,name);
        content.put(Utilidades.TARJETACAP, TarjetaCap);
        content.put(Utilidades.TARJETATACOGRAFOCONDUCTOR, TarjetaTac);
        content.put(Utilidades.CARNETCONDUCIR, CarnetConducir);
        content.put(Utilidades.ADR, adr);

        //Se inserta el registro en la BD y devuelve true si es asì

           Long result=db.insert(Utilidades.NOMBRE_TABLA_CONDUCTOR,null,content);

            //Se cierra la BD
            db.close();

           if(result==-1){
               return false;
           }else{
               return true;
           }
    }




    //Metodo para mostrar un Datepicker
    private void showDatePickerDialog(EditText editText) {

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque Enero es 0
                final String selectedDate = year + "-" + twoDigits(month+1)+ "-"+twoDigits(day);
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