package TP21;

public class Main {
    public static void main(String[] args) {
        Tree miArbol = new Tree();

        miArbol.add(5);
        miArbol.add(2);
        miArbol.add(10);
        miArbol.add(1);

        miArbol.printPreOrder();
        miArbol.printInOrder();
        miArbol.printPosOrder();

        System.out.println(miArbol.hasElem(5));

    }

}
