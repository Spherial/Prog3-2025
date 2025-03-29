package TP21;

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

}