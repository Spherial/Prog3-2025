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
        TreeNode nodoABorrar = get(elem);
        return delete(nodoABorrar);
    }

    private boolean delete(TreeNode actual){
        return false; //TODO
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