package com.example.beto.controldegastos;

/**
 * Created by beto on 17/07/15.
 */
public class Lista {
    String concepto;
    double importe;
    String fecha;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lista(String concepto, String fecha, double importe,int id) {

        this.concepto = concepto;
        this.fecha = fecha;
        this.importe = importe;
        this.id = id;
    }
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }


}
