package TP21;

public class Main {
    public static void main(String[] args) {
        Tree miArbol = new Tree();

//        miArbol.add(5);
//        miArbol.add(2);
//        miArbol.add(10);
//        miArbol.add(1);
        miArbol.add(6);
        miArbol.add(2);
        miArbol.add(10);
        miArbol.add(1);
        miArbol.add(4);
        miArbol.add(8);
        miArbol.add(11);
        miArbol.add(7);
        miArbol.add(9);
        miArbol.add(5);

        miArbol.printPreOrder();
        miArbol.printInOrder();
        miArbol.printPosOrder();

        System.out.println(miArbol.hasElem(5));

        System.out.println(miArbol.getFrontera());

        System.out.println(miArbol.getElemAtLevel(0));

        System.out.println(miArbol.getHeight());

        System.out.println(miArbol.getLongestBranch());



        miArbol.delete(4);
        miArbol.printPreOrder();

    }

}
