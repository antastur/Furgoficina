package data;

import java.io.Serializable;
import java.util.Date;
// Clase que define los atributos de un Conductor y los metodos para crearlos y obtenerlos
public class Conductor implements Serializable {

    //Declaracion de los atributos
    private String dni;
    private String nombre;
    private Date tarjetaCap;
    private Date tarjetaTcografo;
    private Date carnetConducir;
    private Date adr;

    //Metodos constructores


    public Conductor() {
    }

    public Conductor(String dni, String nombre, Date tarjetaCap, Date tarjetaTcografo, Date carnetConducir, Date adr) {
        this.dni = dni;
        this.nombre = nombre;
        this.tarjetaCap = tarjetaCap;
        this.tarjetaTcografo = tarjetaTcografo;
        this.carnetConducir = carnetConducir;
        this.adr = adr;
    }

    // metodos getter y setter de cada uno de los atributos
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Date getTarjetaCap() {
        return tarjetaCap;
    }

    public void setTarjetaCap(Date tarjetaCap) {
        this.tarjetaCap = tarjetaCap;
    }


    public Date getTarjetaTcografo() {
        return tarjetaTcografo;
    }

    public void setTarjetaTcografo(Date tarjetaTcografo) {
        this.tarjetaTcografo = tarjetaTcografo;
    }


    public Date getCarnetConducir() {
        return carnetConducir;
    }

    public void setCarnetConducir(Date carnetConducir) {
        this.carnetConducir = carnetConducir;
    }

    public Date getAdr() { return adr; }

    public void setAdr(Date adr) { this.adr = adr;}
}
