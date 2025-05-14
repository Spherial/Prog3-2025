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

    public void resetearEstadoVertices(){
        for(Map.Entry<Integer,Character> entry : this.estadoVertices.entrySet()){
            entry.setValue('B');
        }
    }

    public void DFS(){
        //Al modificar las entries, se modifican tambien los valores del hashmap original
        // (el set tiene las mismas referencias que el mapa)
        Set<Map.Entry<Integer, Character>> entries = this.estadoVertices.entrySet();

        //Para cada vertice del mapa de estados, los pintamos de blanco
        this.resetearEstadoVertices();
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
        this.resetearEstadoVertices();

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


    //Devuelve una lista de todos los vertices que pueden llegar a determinado destino
    public ArrayList<Integer> verticesQueLlegan(Integer destino) {
        ArrayList<Integer> resultado = new ArrayList<>();
        Iterator<Integer> adyacentes = this.grafo.obtenerVertices();

        while(adyacentes.hasNext()) {
            Integer vertice = adyacentes.next();

            //Limpiamos el mapa de recorrido en cada iteracion, para que los caminos explorados no afecten
            // a los caminos por explorar
            this.resetearEstadoVertices();

            //En cada vertice, si existe un camino, lo agregamos al resultado
            if(existeCamino(vertice, destino)) {
                resultado.add(vertice);
            }
        }
        return resultado;
    }

    private boolean existeCamino(Integer actual, Integer destino) {

        //Si llegamos al destino, devolvemos true
        if(actual.equals(destino)) {
            return true;
        }

        //Sino

        //Marcamos este vertice como visitado
        this.estadoVertices.put(actual, 'A');

        //Obtenemos los adyacentes de este vertice
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(actual);

        while(adyacentes.hasNext()) {
            Integer vecino = adyacentes.next();
            //Si el vecino esta sin visitar, propagamos la busqueda siguiendo por el
            if(!this.estadoVertices.get(vecino).equals('A')) {
                if(existeCamino(vecino, destino)) {
                    return true;
                }
            }
        }

        //Al volver de la recursion, si nunca llegamos al destino, devolvemos false
        return false;
    }


}

