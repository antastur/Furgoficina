package com.example.furgoficina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.furgoficina3.R;

//Clase que representa el menu de inicio desde el que se puede navegar hacia las opciones de la tabla conductor,camion o salir de la app
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AdminBD admin= new AdminBD(this,Utilidades.DB_Name,null,Utilidades.DB_VERSION);
        //Declaramos los botones
        ImageButton imgCamion = (ImageButton) findViewById(R.id.iBCamion);
        ImageButton imgConductor = (ImageButton) findViewById(R.id.iBConductor);
        Button botonSalir = findViewById(R.id.buttonCerrar);
        //SE hace al boton salir listener para que lanze el codigo del metodo onClick
        botonSalir.setOnClickListener(new View.OnClickListener(){

            //En el metodo onClick esta el codigo para salir de la app
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }

    //Metodo boton camion para navegar a opciones de Camion
    public void abrirbCamion(View view){
        Intent bCamion = new Intent(this, GestionCamion.class);
        startActivity(bCamion);
    }

    //Metodo boton conductor para navegar a opciones de conductor
    public void abrirbConductor(View view){
        Intent bConductor = new Intent(this, GestionConductor.class);
        startActivity(bConductor);
    }

}