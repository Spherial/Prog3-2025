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

    public void resetearEstadoVertices(){
        for(Map.Entry<Integer,Character> entry : this.estadoVertices.entrySet()){
            entry.setValue('B');
        }
    }

    public void BFS() {
        //Creamos la fila
        LinkedList<Integer> queue = new LinkedList<>();
        Set<Map.Entry<Integer, Character>> entries = this.estadoVertices.entrySet();

        //Marcamos todos los vertices como no visitados (Blanco)
        this.resetearEstadoVertices();

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

    //Encuentra el camino mas corto entre 2 esquinas (vertices)
    public ArrayList<Integer> caminoMasCorto(Integer esquinaA, Integer esquinaB){
        LinkedList<Integer> queue = new LinkedList<>();

        //Mientras recorremos, para cada nodo anotamos cual es su padre (el predecesor)
        //Cuando encontremos nuestro destino, usamos este mapa para construir el camino hacia el
        HashMap<Integer,Integer> padres = new HashMap<>();
        ArrayList<Integer> resultado = new ArrayList<>();

        this.resetearEstadoVertices();

        this.estadoVertices.put(esquinaA,'N');
        queue.add(esquinaA);

        padres.put(esquinaA,null); //El origen no tiene padre

        while(!queue.isEmpty()){
            int actual = queue.poll();
            Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);


            while(adyacentes.hasNext()){
                Integer next = adyacentes.next();
                if (this.estadoVertices.get(next).equals('B')){
                    this.estadoVertices.put(next,'N');
                    //El padre del adyacente es el actual
                    padres.put(next,actual);

                    //Si el siguiente es el destino, este es el camino a devolver.
                    //Entonces cortamos, y abajo recreamos el camino para llegar hasta aca
                    if (next.equals(esquinaB)){
                        queue.clear();
                        break;
                    }
                    queue.add(next);
                }
            }
        }

        //Ahora que llegamos a la esquina B, usamos el hashmap para saber como hicimos para llegar
        //Como nuestro hashmap guarda los padres de los nodos, vamos a recorrer de atras para adelante

        //Antes, chequeamos casos especiales como que no exista camino, o que partamos ya en el destino

        if (!padres.containsKey(esquinaB) || esquinaA.equals(esquinaB)) {
            return resultado;   //Vacio
        }

        Integer actual = esquinaB;

        //Desde la esquina B, hacemos un recorrido inverso hasta llegar al nodo cuyo padre sea null
        // es decir, esquinaA
        while(actual != null){
            resultado.addFirst(actual); //Insertamos al principio el elemento (corre hacia la derecha lo demas)
            actual=padres.get(actual);  //Nos paramos en el padre y repetimos
        }


        return resultado;

    }

}

