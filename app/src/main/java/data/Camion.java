package data;

import java.io.Serializable;
import java.util.Date;
// Clase que define los atributos de un Camion y los metodos para crearlos y obtenerlos
public  class Camion implements Serializable {

    //Declaracion de los atributos
    private String matricula;
    private int kilometros;
    private Date extintor;
    private Date revTacografo;
    private Date itv;
    private Date seguroMercancias;
    private Date seguroVehiculo;
    private Date ruedas;
    private Date pastillas;
    private Date permCirculacion;
    private Date reparacion;

    //Metodos constructores


    public Camion() {
    }

    public Camion(String matricula, int kilometros, Date extintor, Date revTacografo, Date itv, Date seguroMercancias, Date seguroVehiculo, Date ruedas, Date pastillas, Date permCirculacion, Date reparacion) {
        this.matricula = matricula;
        this.kilometros = kilometros;
        this.extintor = extintor;
        this.revTacografo = revTacografo;
        this.itv = itv;
        this.seguroMercancias = seguroMercancias;
        this.seguroVehiculo = seguroVehiculo;
        this.ruedas = ruedas;
        this.pastillas = pastillas;
        this.permCirculacion = permCirculacion;
        this.reparacion = reparacion;
    }

    // metodos getter y setter de cada uno de los atributos
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public Date getExtintor() {
        return extintor;
    }

    public void setExtintor(Date extintor) {
        this.extintor = extintor;
    }

    public Date getRevTacografo() {
        return revTacografo;
    }

    public void setRevTacografo(Date revTacografo) {
        this.revTacografo = revTacografo;
    }

    public Date getItv() {
        return itv;
    }

    public void setItv(Date itv) {
        this.itv = itv;
    }

    public Date getSeguroMercancias() {
        return seguroMercancias;
    }

    public void setSeguroMercancias(Date seguroMercancias) {
        this.seguroMercancias = seguroMercancias;
    }

    public Date getSeguroVehiculo() {
        return seguroVehiculo;
    }

    public void setSeguroVehiculo(Date seguroVehiculo) {
        this.seguroVehiculo = seguroVehiculo;
    }

    public Date getRuedas() {
        return ruedas;
    }

    public void setRuedas(Date ruedas) {
        this.ruedas = ruedas;
    }

    public Date getPastillas() {
        return pastillas;
    }

    public void setPastillas(Date pastillas) {
        this.pastillas = pastillas;
    }

    public Date getPermCirculacion() {
        return permCirculacion;
    }

    public void setPermCirculacion(Date permCirculacion) {
        this.permCirculacion = permCirculacion;
    }

    public Date getReparacion() {
        return reparacion;
    }

    public void setReparacion(Date reparacion) {
        this.reparacion = reparacion;
    }


}
