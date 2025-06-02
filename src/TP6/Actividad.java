package TP6;

public class Actividad {
    private int tiempoInicio;
    private int tiempoFin;

    public Actividad(int tiempoInicio, int tiempoFin){
        this.tiempoInicio = tiempoInicio;
        this.tiempoFin = tiempoFin;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public int getTiempoFin() {
        return tiempoFin;
    }

    @Override
    public String toString() {
        return "[" + tiempoInicio + " | " + tiempoFin + "]";
    }
}
