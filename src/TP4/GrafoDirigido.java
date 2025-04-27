package TP4;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap<Integer, ArrayList<Arco<T>>> arcosAdyacentes;
    private int cantArcos;

    public GrafoDirigido(){
        this.arcosAdyacentes = new HashMap<>();
        this.cantArcos = 0;
    }


    @Override
    public void agregarVertice(int verticeId) {
        //Chequear el get != null
        if (!arcosAdyacentes.containsKey(verticeId)){
            this.arcosAdyacentes.put(verticeId, new ArrayList<>());
        }

    }

    @Override
    public void borrarVertice(int verticeId) {
        // TODO Auto-generated method stub
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        Arco<T> nuevoArco = new Arco<>(verticeId1,verticeId2, etiqueta);
        //Al hashmap le pedimos el value (la lista de arcos) y a dicha lista le agregamos el arco nuevo
        this.arcosAdyacentes.get(verticeId1).add(nuevoArco);
        this.cantArcos++;
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return this.arcosAdyacentes.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        ArrayList<Arco<T>> arcos = this.arcosAdyacentes.get(verticeId1);
        for (Arco<T> arcoActual : arcos){
            if (arcoActual.getVerticeDestino() == verticeId2){
                return true;
            }
        }
        return false;
    }

    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int cantidadVertices() {
        return this.arcosAdyacentes.size();
    }

    @Override
    public int cantidadArcos() {
        return this.cantArcos;
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return this.arcosAdyacentes.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        LinkedList<Arco<T>> totalArcos = new LinkedList<>();
        for(ArrayList<Arco<T>> arcos : this.arcosAdyacentes.values()){
            totalArcos.addAll(arcos);
        }
        return totalArcos.iterator();
    }

    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        return this.arcosAdyacentes.get(verticeId).iterator();
    }

}