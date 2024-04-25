package com.example.furgoficina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.furgoficina3.R;

//Clase Que proporciona una activity en la que navegar para ir a otras activities que haran las CRUD para la tabla conductor
public class GestionConductor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_conductor);
    }


    //Metodo boton volver
    public void volver(View view) {
        Intent vuelta = new Intent(this, MainActivity.class);
        startActivity(vuelta);
    }

    //Metodo boton introducir
    public void bintroducir(View view) {
        Intent intro = new Intent(this, ConductorActivity.class);
        startActivity(intro);
    }

    //Metodo boton consultar
    public void bconsultar(View view) {
        Intent consulta = new Intent(this, ConsultaActivity.class);
        startActivity(consulta);
    }

    //Metodo boton modificar
    public void bmodificar(View view) {
        Intent cambio = new Intent(this, CambioActivity.class);
        startActivity(cambio);
    }

    //Metodo boton borrar
    public void bborrar(View view) {
        Intent borrado = new Intent(this, BorradoActivity.class);
        startActivity(borrado);
    }




}