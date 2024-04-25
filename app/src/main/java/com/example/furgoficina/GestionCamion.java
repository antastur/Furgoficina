package com.example.furgoficina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.furgoficina3.R;


//Clase Que proporciona una activity en la que navegar para ir a otras activities que haran las CRUD para la tabla camion
public class GestionCamion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_camion);
    }




    //Metodo boton volver
    public void volver(View view) {
        Intent vuelta = new Intent(this, MainActivity.class);
        startActivity(vuelta);
    }

    //Metodo boton introducir
    public void bintroducir(View view) {
        Intent intro = new Intent(this, CamionActivity.class);
        startActivity(intro);
    }

    //Metodo boton consultar
    public void bconsultar(View view) {
        Intent consulta = new Intent(this, ConsultaCamionActivity.class);
        startActivity(consulta);
    }

    //Metodo boton modificar
    public void bmodificarCamion(View view) {
        Intent modificar= new Intent(this, CambiarCamionActivity.class);
        startActivity(modificar);
    }

    //Metodo boton borrar
    public void bborrar(View view) {
        Intent borrando = new Intent(this, BorradoCamionActivity.class);
        startActivity(borrando);
    }


}