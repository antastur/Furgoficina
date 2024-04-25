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






public class ConsultaCamionActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaracion de variables de atributos del camion
    EditText matriculaEditText;
    TextView kilometros;
    TextView extintor;
    TextView revTacografo;
    TextView itv;
    TextView segMercancias;
    TextView segVehiculo;
    TextView ruedas;
    TextView pastillas;
    TextView permCirculacion;
    TextView reparacion;
    Button botonBuscar;
    AdminBD admin;




  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_camion);

      //Creacion de conexion a BD en esta activity
      admin=new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);

        //Asignacion a variables del los EditText con datos de tipo date y asocialos a un evento click
        matriculaEditText=(EditText) findViewById(R.id.textMatriculaCons2Camion);
        botonBuscar=(Button) findViewById(R.id.bCambiarCambioCamion2);
        botonBuscar.setOnClickListener(this);
        kilometros=(TextView) findViewById(R.id.textkilometrosCons2Camion);
        extintor = (TextView) findViewById(R.id.dateExtintorCons2Camion);
        revTacografo = (TextView)findViewById(R.id.dateRevTacografoCons2Camion);
        itv = (TextView)findViewById(R.id.dateItvCons2Camion);
        segMercancias = (TextView)findViewById(R.id.dateSegMercanciasCons2Camion);
        segVehiculo =(TextView) findViewById(R.id.dateSegVehiculoCons2Camion);
        ruedas = (TextView)findViewById(R.id.dateRuedasCons2Camion);
        pastillas = (TextView)findViewById(R.id.datePastillasCons2Camion);
        permCirculacion =(TextView) findViewById(R.id.datePermCirculacionCons2Camion);
        reparacion = (TextView)findViewById(R.id.dateUltReparacionCons2Camion);
  }



    //Metodo boton volverConsulta
    public void volver(View view){
      Intent vuelta = new Intent(this, GestionCamion.class);
        startActivity(vuelta);
    }



      @Override
    public void onClick(View view) {

               //Si no se ha introducido valor alguno en el EditText de la matricula se lanza un aviso
              if (matriculaEditText.getText().toString().isEmpty()) {
                  Toast toast1 = Toast.makeText(getApplicationContext(), "Introduce la matrícula", Toast.LENGTH_SHORT);
                  toast1.show();
                  //Si se introduce una matricula
              } else {

                  //Con el metodo verCamion se obtiene una consulta de todos los campos de un camion pasando como parametro su matricula
                  verCamion(matriculaEditText.getText().toString());
              }
    }





    //Metodo que devuelve un objeto camion con los mismos valores en sus atributos que el que tiene la matricula pasada por parametro
    private void  verCamion(String matricula){

        //Se instancia la BD y se prepara un arreglo en el que se transpasaran los datos
        SQLiteDatabase db = admin.getWritableDatabase();
        //Se instancian arreglos para la consulta , pimero los campos a mostrar,se importan los String de las constantes de Utilidades
        String[] consultas={ KILOMETROS, EXTINTOR, REVISIONTAC, ITV, SEGUROMERCANCIAS, SEGUROVEHICULO, RUEDAS,
                             FRENOS, PERMISOCIRCULACION, REPARACION};
        //Despues del campo que servira como llave de la busqueda(dni)
        String[] clave={matricula };
        try {
            //Se hace la consulta
            Cursor cursor = db.query(NOMBRE_TABLA_CAMION, consultas, MATRICULA + " = ?", clave, null,null,null);
            //Se situa al inicio del cursor devuelto y se recogen los resultados mostrandolos en sus correspondientes TextViews
            cursor.moveToFirst();
            kilometros.setText(cursor.getString(0));
            extintor.setText(cursor.getString(1));
            revTacografo.setText(cursor.getString(2));
            itv.setText(cursor.getString(3));
            segMercancias.setText(cursor.getString(4));
            segVehiculo.setText(cursor.getString(5));
            ruedas.setText(cursor.getString(6));
            pastillas.setText(cursor.getString(7));
            permCirculacion.setText(cursor.getString(8));
            reparacion.setText(cursor.getString(9));
            //Se cierra el cursor
            cursor.close();
            //Si la consulta arroja excepcion se da mensaje de advertencia
            } catch (Exception e) {
            Toast toast1 = Toast.makeText(getApplicationContext(), "No se ha encontrado ese camión", Toast.LENGTH_SHORT);
            toast1.show();

            }
        //Se cierra BD
        db.close();
        }
}






