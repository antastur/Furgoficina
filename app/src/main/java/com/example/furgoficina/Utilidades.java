package com.example.furgoficina;


import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

public class Utilidades{

    //Declaración de constantes que definen la BD
    public static final String DB_Name="dbfurgoficina";
    public static final int DB_VERSION=1;

    //Creacion de constantes que haran referencia a las caracteristicas de la tabla: nombre,campos
    public static final String NOMBRE_TABLA_CAMION = "camiones";
    public static final String MATRICULA = "matricula";
    public static final String KILOMETROS = "kilometros" ;
    public static final String EXTINTOR= "extintor";
    public static final String REVISIONTAC = "revTacografo";
    public static final String ITV = "itv";
    public static final String SEGUROMERCANCIAS = "seguroMercancias";
    public static final String SEGUROVEHICULO = "seguroVehiculo";
    public static final String RUEDAS = "ruedas";
    public static final String FRENOS = "pastillas";
    public static final String PERMISOCIRCULACION= "permCirculacion";
    public static final String REPARACION ="reparacion";

    public static final String CREAR_TABLA_CAMIONES = "CREATE TABLE "+ NOMBRE_TABLA_CAMION+" ("

            +MATRICULA+" TEXT PRIMARY KEY,"
            +KILOMETROS+" INTEGER,"
            +EXTINTOR+" TEXT,"
            +REVISIONTAC+" TEXT,"
            +ITV+" TEXT,"
            +SEGUROMERCANCIAS+" TEXT,"
            +SEGUROVEHICULO+" TEXT,"
            +RUEDAS+" TEXT,"
            +FRENOS+" TEXT,"
            +PERMISOCIRCULACION+" TEXT,"
            +REPARACION+" TEXT)";

    //Creacion de constantes que haran referencia a las caracteristicas de la tabla: nombre,campos
    public static final String NOMBRE_TABLA_CONDUCTOR = "conductores";
    public static final String DNI = "dni";
    public static final String NOMBRE = "nombre";
    public static final String TARJETACAP = "tarjetaCap";
    public static final String TARJETATACOGRAFOCONDUCTOR = "tarjetaTcografo";
    public static final String CARNETCONDUCIR = "carnetConducir";
    public static final String ADR = "adr";


    public static final String CREAR_TABLA_CONDUCTORES = "CREATE TABLE "+ NOMBRE_TABLA_CONDUCTOR+" ("

            +DNI+" TEXT PRIMARY KEY,"
            +NOMBRE+" TEXT,"
            +TARJETACAP+" TEXT,"
            +TARJETATACOGRAFOCONDUCTOR+" TEXT,"
            + CARNETCONDUCIR+" TEXT,"
            + ADR+" TEXT)";






















}
