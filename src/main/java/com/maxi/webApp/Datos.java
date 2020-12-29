package com.maxi.webApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datos {
    private Date fecha;
    private int pasajeros;
    private String linea;

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getFecha() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fecha);
    }

    public void setFecha(String fecha) {
        try {
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(fecha);
            this.fecha = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setFechaArg(String fecha) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
            this.fecha = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public Date getDateTime(){
        return fecha;
    }

    @Override
    public String toString() {
        return "Datos{" +
                "fecha='" + fecha + '\'' +
                ", pasajeros=" + pasajeros +
                ", linea='" + linea + '\'' +
                '}';
    }
}
