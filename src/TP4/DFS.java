package TP4;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        }

        //Una vez recorridos todos los adyacentes de este vertice, lo pintamos de negro para finalizar
        this.estadoVertices.put(verticeId,'N');
        System.out.println("Finalizado el vertice: " + verticeId);

    }


}

