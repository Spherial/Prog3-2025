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