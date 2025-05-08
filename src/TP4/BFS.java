package TP4;

import java.util.*;

public class BFS<T> {
    private HashMap<Integer,Character> estadoVertices;
    private GrafoDirigido<T> grafo;

    public BFS(GrafoDirigido<T> grafo){
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

    public void BFS() {
        //Creamos la fila
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Map.Entry<Integer, Character>> entries = this.estadoVertices.entrySet();

        //Marcamos todos los vertices como no visitados (Blanco)
        for (Map.Entry<Integer,Character> entry : entries) {
            entry.setValue('B');
        }

        //Si el vertice que recorremos esta sin visitar, lo visitamos
        //Pasamos la la queue que vamos a usar en todo el recorrido
        for (Map.Entry<Integer,Character> entry : entries) {
            if (entry.getValue().equals('B')) {
                BFS_Visit(entry.getKey(), queue);
            }
        }

    }



    public void BFS_Visit(Integer verticeId, LinkedList<Integer> queue) {
        //Marcamos el actual como visitado
        this.estadoVertices.put(verticeId, 'N');

        //Agregamos el actual a la queue para empezar el recorrido
        queue.add(verticeId);


        while (!queue.isEmpty()) {
            int actual = queue.poll(); //Como ya visitamos este, propagamos la busqueda a los adyacentes
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);
            while(adyacentes.hasNext()) {
                //Para cada adyacente blanco, repetimos el proceso.
                //Lo marcamos, lo metemos a la queue y lo usamos para conseguir sus adyacentes
                int next = adyacentes.next();

                if(this.estadoVertices.get(next).equals('B')) {
                    this.estadoVertices.put(next,'N');
                    queue.add(next);
                }
            }
        }
    }


}

