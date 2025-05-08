package TP4;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

    private HashMap<Integer, ArrayList<Arco<T>>> arcosAdyacentes;
    private int cantArcos;
    private HashMap<Integer,Character> estadoVertices;

    public GrafoDirigido(){
        this.arcosAdyacentes = new HashMap<>();
        this.estadoVertices = new HashMap<>();
        this.cantArcos = 0;
    }


    @Override
    public void agregarVertice(int verticeId) {
        //Chequear el get != null
        if (!arcosAdyacentes.containsKey(verticeId)){
            this.arcosAdyacentes.put(verticeId, new ArrayList<>());
            this.estadoVertices.put(verticeId,'B');
        }

    }

    @Override
    //Borrar un vertice, incluyendo los arcos que salen y entran al mismo
    public void borrarVertice(int verticeId) {
        //Primero, chequeamos que exista el vertice que queremos borrar
        if (this.contieneVertice(verticeId)) {

            //Luego, iteramos todas las listas de arcos de nuestro hashmap
           for (ArrayList<Arco<T>> arcos : this.arcosAdyacentes.values()){

               //Para cada lista de arcos, iteramos los arcos y vemos si van hacia el elemento que vamos a borrar
               Iterator<Arco<T>> it = arcos.iterator();
               while (it.hasNext()){
                   Arco<T> arcoActual = it.next();

                   //Si es el caso, borramos dicho arco para que no apunte a un vertice inexistente
                   if (arcoActual.getVerticeDestino() == verticeId){
                       it.remove();
                       this.cantArcos--;
                   }
               }

           }

            //Antes de borrar el vertice, restamos su cantidad de arcos salientes)
            ArrayList<Arco<T>> arcosSalientes = arcosAdyacentes.get(verticeId);
            //Chequeamos que dichos arcos salientes si existan, porque caso contrario la lista es null
            if (arcosSalientes != null) {
                cantArcos -= arcosSalientes.size();
            }

            // Ahora, borramos el vértice del hashmap (garbage collector elimina los arcos salientes)
            this.arcosAdyacentes.remove(verticeId);
            this.estadoVertices.remove(verticeId);
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        Arco<T> nuevoArco = new Arco<>(verticeId1,verticeId2, etiqueta);
        //Al hashmap le pedimos el value (la lista de arcos) y a dicha lista le agregamos el arco nuevo
        this.arcosAdyacentes.get(verticeId1).add(nuevoArco);
        this.cantArcos++;
    }

    @Override
    //Borrar todos los arcos que vayan desde el vertice 1 al vertice 2
    public void borrarArco(int verticeId1, int verticeId2) {
        //Primero, chequeamos que el vertice1 si tenga arcos para borrar
        if (this.arcosAdyacentes.get(verticeId1) != null){

            //Obtenemos todos los arcos salientes de vertice1
            ArrayList<Arco<T>> arcos = this.arcosAdyacentes.get(verticeId1);
            Iterator<Arco<T>> it = arcos.iterator();
            while (it.hasNext()){
                Arco<T> arcoActual = it.next();

                //Si el arco actual va al vertice 2 (sabiendo que estamos parados en el 1), lo borramos
                if (arcoActual.getVerticeDestino() == verticeId2){
                    it.remove();
                    cantArcos--;
                }
            }
        }
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
        List<Arco<T>> adyacentes = this.arcosAdyacentes.get(verticeId1);
        if (adyacentes == null) {
            return null;
        }
        for (Arco<T> arco : adyacentes) {
            if (arco.getVerticeDestino() == verticeId2) {
                return arco;
            }
        }
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
        ArrayList<Integer> adyacentes = new ArrayList<>();
        for (Arco<T> arco : this.arcosAdyacentes.get(verticeId)){
            adyacentes.add(arco.getVerticeDestino());
        }

        return adyacentes.iterator();
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