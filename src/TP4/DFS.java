package TP4;

import java.util.*;

public class DFS<T> {
    private HashMap<Integer,Character> estadoVertices;
    private GrafoDirigido<T> grafo;

    public DFS(GrafoDirigido<T> grafo){
        this.estadoVertices = new HashMap<>();
        this.grafo = grafo;
        cargarVertices(grafo);
    }

    public void cargarVertices(GrafoDirigido<T> grafo){
        Iterator<Integer> keys = grafo.obtenerVertices();
        while(keys.hasNext()){
            this.estadoVertices.put(keys.next(), 'B');
        }
    }

    public void DFS(){
        //Al modificar las entries, se modifican tambien los valores del hashmap original
        // (el set tiene las mismas referencias que el mapa)
        Set<Map.Entry<Integer, Character>> entries = this.estadoVertices.entrySet();

        //Para cada vertice del mapa de estados, los pintamos de blanco
        for(Map.Entry<Integer,Character> entry : entries){
            entry.setValue('B');
        }
        System.out.println("Seteados todos los vertices a blanco");


        for(Map.Entry<Integer,Character> entry : entries){
            //Si el vertice esta sin visitar (blanco)
            if (entry.getValue().equals('B')){
                DFS_Visit(entry.getKey());
            }
        }


    }

    public void DFS_Visit(Integer verticeId){
        //Marcamos el vertice como visitado (amarillo)
        this.estadoVertices.put(verticeId,'A');
        System.out.println("Pasando por vertice: " + verticeId);

        Iterator<Integer> it = this.grafo.obtenerAdyacentes(verticeId);
        while(it.hasNext()){
            Integer next = it.next();

            //Si este vertice esta sin explorar (blanco) lo recorremos
            if (this.estadoVertices.get(next).equals('B')){
                DFS_Visit(next);
            }
            else{
                if (this.estadoVertices.get(next).equals('A')){
                    System.out.println("CICLO DETECTADO");
                }
            }
        }

        //Una vez recorridos todos los adyacentes de este vertice, lo pintamos de negro para finalizar
        this.estadoVertices.put(verticeId,'N');
        System.out.println("Finalizado el vertice: " + verticeId);

    }

    //Encuentra el camino mas largo entre 2 vertices usando backtracking
    // (Busca todos los posibles caminos y se queda con el mas largo)
    public ArrayList<Integer> caminoMasLargo(int vertice1, int vertice2) {
        ArrayList<Integer> caminoActual = new ArrayList<>();
        ArrayList<Integer> masLargo = new ArrayList<>();

        //Setea por default todos los vertices como no visitados
        for(Map.Entry<Integer,Character> entry : this.estadoVertices.entrySet()){
            entry.setValue('B');
        }

        caminoMasLargo(vertice1, vertice2, caminoActual, masLargo);
        return masLargo;
    }



    private void caminoMasLargo(int actual, int destino, ArrayList<Integer> caminoActual, ArrayList<Integer> masLargo) {

        //Marcamos el vertice actual como visitado
        this.estadoVertices.put(actual,'A');
        //Agregamos el vertice al camino actual
        caminoActual.add(actual);

        //Si llegamos al destino, es una posible solucion, asi que comparamos con la mejor
        //solucion encontrada hasta ahora y la reemplaza de ser necesario
        if (actual == destino) {
            if (caminoActual.size() > masLargo.size()) {
                masLargo.clear();
                masLargo.addAll(new ArrayList<>(caminoActual));
            }
        }
        else {

            //Si todavia no llegue al destino, busco todos los adyacentes de mi vertice actual
            //y empiezo a buscar desde ahi
            Iterator<Integer> it = this.grafo.obtenerAdyacentes(actual);
            while (it.hasNext()) {
                int vecino = it.next();
                if (this.estadoVertices.get(vecino).equals('B')) {
                    caminoMasLargo(vecino, destino, caminoActual, masLargo);
                }
            }
        }

        // Cuando sale de la recursion (o sea que exploro todos los adyacentes)
        // reseteamos el estado del vertice actual, para asi poder explorar otros caminos
        // que no incluyan este vertice
        caminoActual.removeLast();

        //Como este vertice podria ser accesible desde otro camino, lo pongo de nuevo como no visitado
        this.estadoVertices.put(actual, 'B');

    }



}

