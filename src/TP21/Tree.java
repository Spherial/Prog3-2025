package TP21;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private TreeNode root;

    public Tree() {
        this.root = null;
    }


    //El invocador solo pasa un numero, ya el metodo privado se encargara de crear los nodos correspondientes
    public void add(Integer value) {
        if (this.root == null)
            this.root = new TreeNode(value);    //Si no hay raiz, el nuevo elemento lo es
        else
            this.add(this.root,value);     //sino, llama al metodo privado para ubicarlo donde corresponda
    }


    //Encuentra la posicion indicada, crea un nodo con esa info y lo agrega al arbol
    //Importante que sea privado, pues el invocador solo ingresa un numero, no conoce la
    //existencia de los TreeNodes
    private void add(TreeNode actual, Integer value) {
        //Si el nodo actual es mas grande, entonces el nuevo ira a la izquierda
        if (actual.getValue() > value) {
            //Si la izquierda esta disponible, lo insertamos ahi
            if (actual.getLeft() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setLeft(temp);
            } else {
                //Sino, le preguntamos a dicho nodo izquierdo
                add(actual.getLeft(),value);
            }
        }
        //Si el nodo actual es mas chico, el nuevo ira a la derecha
        else if (actual.getValue() < value) {
            //Si la derecha esta disponible, se inserta ahi
            if (actual.getRight() == null) {
                TreeNode temp = new TreeNode(value);
                actual.setRight(temp);
            } else {
                //Sino, le preguntamos al nodo derecho
                add(actual.getRight(),value);
            }
        }
    }

    //Devuelve la info de la raiz, si la misma existe
    public Integer getRoot(){
        return this.root!=null ? this.root.getValue() : null;
    }

    //Comprueba si el arbol esta vacio (no tiene raiz)
    public boolean isEmpty(){
        return this.root == null;
    }


    //Obtiene el mayor valor del arbol (el que esta mas a la derecha)
    public Integer getMaxElem(){
        if (this.isEmpty()){
            return null;
        }

        //Partiendo de la raiz, si el nodo tiene un derecho, ese derecho se vuelve el de mayor valor
        TreeNode actual = this.root;
        while(actual.getRight() != null){
            actual = actual.getRight();
        }

        //Una vez llegado al nodo derecho maximo, devuelvo la info
        return actual.getValue();
    }


    //Inicializa la busqueda del integer en la raiz
    public boolean hasElem(Integer elem){
        return hasElem(this.root,elem);
    }


    //Comprueba si X valor esta en este arbol
    private boolean hasElem(TreeNode actual, Integer elem){
        if (actual != null){
            //Si el nodo actual es mas grande, busco por su izquierda
            if (actual.getValue() > elem){
                return hasElem(actual.getLeft(),elem);
            }
            //Si el nodo actual es mas chico, busco por su derecha
            else if (actual.getValue() < elem){
                return hasElem(actual.getRight(),elem);
            }
            //Si el valor es igual, lo encontre
            else{
                return true;
            }
        }


        return false;
    }


    //Obtiene un nodo especifico del arbol
    private TreeNode get(Integer elem){
        return get(this.root,elem);
    }

    //Recorre el arbol hasta encontrar el nodo deseado y lo retorna
    //Iterativo para evitar desbordamiento de la pila
    private TreeNode get(TreeNode actual, Integer elem) {
        while (actual != null) {
            if (actual.getValue() > elem) {
                actual = actual.getLeft();
            } else if (actual.getValue() < elem) {
                actual = actual.getRight();
            } else {
                return actual;
            }
        }
        return null;
    }


    //Elimina un nodo del arbol. Si es necesario reacomoda nodos para garantizar la estructura del ABB
    public boolean delete(Integer elem){
        if (this.root == null){
            return false;
        }

        //Si estoy queriendo borrar la raiz
        if (this.root.getValue().equals(elem)){
            this.root = null; //TODO CAMBIAR
            return true;
        }
        else{
            return borrarNodo(this.root,elem);
        }

    }

    //Encuentra el nodo a borrar, consigue un reemplazo y actualiza las referencias
    private boolean borrarNodo(TreeNode actual, Integer elem) {
        //Si el arbol esta vacio, devuelvo null
        if (actual == null) {
            return false;
        }


        //Si el elemento a borrar es menor, entonces debe estar en el subarbol izquierdo
        if (elem < actual.getValue()) {
            //Compruebo si el hijo izquierdo es el que queria eliminar
            if (actual.getLeft() != null && actual.getLeft().getValue().equals(elem)) {
                actual.setLeft(obtenerReemplazo(actual.getLeft())); //Consigo un reemplazo y lo cambio
                return true;
            } else {
                return borrarNodo(actual.getLeft(), elem); //Sino, sigo buscando por la izquierda
            }
        }
        //Si el elemento a borrar es mayor, entonces debe estar en el subarbol derecho
        else if (elem > actual.getValue()) {
            //Compruebo si el hijo derecho es el que quiero borrar
            if (actual.getRight() != null && actual.getRight().getValue().equals(elem)) {
                actual.setRight(obtenerReemplazo(actual.getRight()));   //Consigo un reemplazo y lo cambio
                return true;
            } else {
                return borrarNodo(actual.getRight(), elem); //Sino, sigo buscando por la derecha
            }
        }

        //No encontre el nodo que queria borrar
        return false;
    }


    //Devuelve cual nodo deberia tomar el lugar de este, al eliminarlo
    //Luego el arbol se encargara de actualizar la referencia
    private TreeNode obtenerReemplazo(TreeNode nodo){

        //Si el nodo a borrar es una hoja, directamente lo reemplazamos por un null
        if (nodo.getLeft() == null && nodo.getRight() == null){
            return null;
        }

        //Si el nodo a borrar tiene un solo hijo, dicho hijo tomara su lugar

        if (nodo.getLeft() == null) {   //Si solo tiene hijo derecho, el derecho sera el reemplazo
            return nodo.getRight();
        }
        else if (nodo.getRight() == null) { //Si solo tiene hijo izquierdo, el izquierdo sera el reemplazo
            return nodo.getLeft();
        }



        //Si el nodo a borrar tiene dos hijos, necesitamos buscar su sucesor inmediato
        //es decir, el minimo elemento de su subarbol derecho

        TreeNode sucesor = encontrarMinimo(nodo.getRight());    //Encuentro el sucesor
        nodo.setValue(sucesor.getValue());
        nodo.setRight(borrarNodo(nodo.getRight(), sucesor.getValue()) ? nodo.getRight() : null);
        return nodo; //El reemplazo sera un nodo que apunte al sucesor directo

    }


    //Busca el minimo nodo a partir del actual
    //Como lo necesito para encontrar el sucesor de un nodo, es privado y devuelvo el nodo entero
    // en lugar de su valor
    private TreeNode encontrarMinimo(TreeNode actual) {
        while (actual.getLeft() != null) {
            actual = actual.getLeft();
        }
        return actual;
    }





    //Obtiene la rama mas larga del arbol

    public List<Integer> getLongestBranch(){
        List<Integer> resultado = new ArrayList<>();
        getLongestBranch(this.root, new ArrayList<>(),resultado);   //La lista actual comienza vacia
        return resultado;
    }

    //Este metodo busca la totalidad del arbol y actualiza la lista "resultado"
    private void getLongestBranch(TreeNode actual, List<Integer> ramaActual, List<Integer> ramaMayor){
        //Si estoy parado en un null, dejo de buscar por este lado
        if (actual == null){
            return;
        }

        //Si estoy parado en un nodo valido, lo agrego para ir construyendo la lista
        ramaActual.add(actual.getValue());

        //Una vez que llego a una hoja, comparo el tamaño de las 2 ramas que tengo (la actual y la mayor)
        if(actual.getLeft() == null & actual.getRight()==null){
            if (ramaActual.size() > ramaMayor.size()){
                //Vacio la rama mayor y la reemplazo con la actual
                ramaMayor.clear();
                ramaMayor.addAll(ramaActual);
            }
        }
        //Si no estoy en una hoja (ni en un null) solo sigo recorriendo para ambos lados
        else{
            getLongestBranch(actual.getLeft(),ramaActual,ramaMayor);
            getLongestBranch(actual.getRight(),ramaActual,ramaMayor);
        }

        //Cada vez que la funcion "vuelve para atras", le quito el ultimo nodo para revertir el estado
        //de la ramaActual a como era antes de entrar a este nodo (para evitar que se puedan mezclar los caminos)
        ramaActual.remove(ramaActual.size() - 1);


    }





    //Devuelve la frontera del arbol (todas las hojas)
    public List<Integer> getFrontera(){
        ArrayList<Integer> resultado = new ArrayList<>();
        rellenarListaFrontera(this.root,resultado);
        return resultado;
    }

    //Inserta todos los nodos sin hijos (izq o der) en la lista y se propaga recursivamente
    private void rellenarListaFrontera(TreeNode actual, List<Integer> lista){
        //Si estoy en un null, no hago nada
        if (actual == null){
            return;
        }

        //Si estoy parado en una hoja, la agrego a la lista
        if ((actual.getLeft() == null) && (actual.getRight() == null)){
            lista.add(actual.getValue());
        }

        //Propaga la busqueda en los nodos hijos
        rellenarListaFrontera(actual.getLeft(),lista);
        rellenarListaFrontera(actual.getRight(),lista);
    }


    //Obtiene todos los valores de nodos de X nivel de profundidad
    public List<Integer> getElemAtLevel(int level){
        ArrayList<Integer> resultado = new ArrayList<>();
        getElemAtLevel(this.root,resultado,0,level);
        return resultado;
    }


    //Comprueba si el nivel del nodo es el buscado, lo agrega a la lista y se propaga a sus hijos
    private void getElemAtLevel(TreeNode actual, List<Integer> lista, int level,int target){
        if (actual != null){
            if (level == target){
                lista.add(actual.getValue());
            }
        getElemAtLevel(actual.getLeft(),lista,level+1,target);
        getElemAtLevel(actual.getRight(),lista,level+1,target);


        }
    }

    public int getHeight() {
        return getHeight(this.root);
    }

    private int getHeight(TreeNode actual) {
        if (actual == null) {
            return -1;
        }
        int izqH = getHeight(actual.getLeft());
        int derH = getHeight(actual.getRight());

        return Math.max(izqH, derH) + 1;
    }



    //Ejercicio 2

    //Suma los valores de todos los nodos internos (aquellos que no son hojas)

    public int sumarNodosInternos(){
        //Si el arbol esta vacio, no hay nada que sumar, es 0
        if (this.isEmpty()){
            return 0;
        }
        return sumarNodosInternos(this.root);
    }


    private int sumarNodosInternos(TreeNode actual){
        //Si estamos recorriendo un null, no tiene valor, no sumamos nada
        if (actual == null){
            return 0;
        }
        //Almacenamos la suma actual (de este subarbol), la cual ira creciendo al
        //sumar los demas subarboles
        int suma = 0;

        //Si estamos en un nodo interno (o sea, que tenga al menos un hijo), sumamos su valor
        if (actual.getLeft() != null || actual.getRight() != null){
            suma+= actual.getValue();
        }

        //Propagamos la suma a los subarboles izquierdos y derechos
        suma+= sumarNodosInternos(actual.getLeft());
        suma+= sumarNodosInternos(actual.getRight());

        return suma;

    }



    //Ejercicio 3

    //Crea una lista con todas las hojas cuyo valor sea mayor que K

    public List<Integer> valoresMayoresA(Integer k){
        ArrayList<Integer> resultado = new ArrayList<>();
        valoresMayoresA(this.root,k, resultado);
        return resultado;
    }

    private void valoresMayoresA(TreeNode actual, Integer k, List<Integer> resultado){
        if (actual != null){

            //Comprueba que el nodo actual sea una hoja
            if (actual.getLeft() == null && actual.getRight() == null){
                //Si esta hoja tiene un valor mayor a k, lo añadimos a la lista
                if (actual.getValue() > k){
                    resultado.add(actual.getValue());
                }
            }

            //Buscamos en los subarboles izquierdos y derechos
            valoresMayoresA(actual.getLeft(),k,resultado);
            valoresMayoresA(actual.getRight(),k,resultado);


        }
    }






    //RECORRIDOS

    public void  printPreOrder(){
        System.out.println("-----PRE ORDER-----");
        if (this.root != null){
            printPreOrder(this.root);
        }
        else{
            System.out.println("Arbol vacio");
        }
    }

    private void printPreOrder(TreeNode actual){
        if (actual == null){
            System.out.println("-");
            return;
        }
        System.out.println(actual.getValue());
        printPreOrder(actual.getLeft());
        printPreOrder(actual.getRight());
    }

    public void  printInOrder(){
        System.out.println("-----IN ORDER-----");
        if (this.root != null){
            printInOrder(this.root);
        }
        else{
            System.out.println("Arbol vacio");
        }
    }

    private void printInOrder(TreeNode actual){
        if (actual == null){
            System.out.println("-");
            return;
        }
        printInOrder(actual.getLeft());
        System.out.println(actual.getValue());
        printInOrder(actual.getRight());
    }

    public void  printPosOrder(){
        System.out.println("-----POS ORDER-----");
        if (this.root != null){
            printPosOrder(this.root);
        }
        else{
            System.out.println("Arbol vacio");
        }
    }

    private void printPosOrder(TreeNode actual){
        if (actual == null){
            System.out.println("-");
            return;
        }
        printPosOrder(actual.getLeft());
        printPosOrder(actual.getRight());
        System.out.println(actual.getValue());
    }

}