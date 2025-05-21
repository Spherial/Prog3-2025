package TP5;

public class Celda {
    private int fila;
    private int columna;
    private int valor;
    private boolean izq;
    private boolean der;
    private boolean arriba;
    private boolean abajo;

    public Celda(int fila, int columna,int valor,boolean izq,boolean der,boolean arriba,boolean abajo){
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
        this.izq = izq;
        this.der = der;
        this.arriba = arriba;
        this.abajo = abajo;
    }

    public int getValor() {
        return valor;
    }

    public boolean getIzq() {
        return izq;
    }

    public boolean getDer() {
        return der;
    }

    public boolean getArriba() {
        return arriba;
    }

    public boolean getAbajo() {
        return abajo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
