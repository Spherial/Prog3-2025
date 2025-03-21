package TP1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
//        MySimpleLinkedList<Integer> numeros = new MySimpleLinkedList<>();
//        numeros.insertFront(2);
//        numeros.insertFront(5);
//        numeros.insertFront(7);

//        System.out.println(numeros.size());
//        System.out.println(numeros);
//        System.out.println(numeros.get(2));
//        System.out.println(numeros.indexOf(7));

    MySimpleLinkedList<String> nombres = new MySimpleLinkedList<>();
    nombres.insertFront("pablo");
    nombres.insertFront("jimena");
    nombres.insertFront("nadaquever");

        System.out.println(nombres);
        System.out.println(nombres.size());
        System.out.println(nombres.indexOf("pablo"));
        System.out.println(nombres.get(1));


        //Iterator usando while
        System.out.println("---WHILE---");
        Iterator<String> it = nombres.iterator();
        while (it.hasNext()){
            System.out.println("Imprimiendo elemento: " + it.next());
        }

        //Iterator usando foreach
        System.out.println("---FOREACH---");
        for (String nombre : nombres){
            System.out.println("Imprimiendo elemento: " + nombre);
        }
    }
    
    public <T> MySimpleLinkedList<T> elementosComunes(MySimpleLinkedList<T> lista1, MySimpleLinkedList<T> lista2){
    	MySimpleLinkedList<T> resultado = new MySimpleLinkedList();
    	
    	LinkedListIterator<T> iterador1 = lista1.iterator();
    	LinkedListIterator<T> iterador2 = lista2.iterator();
    	
    	while(!iterador1.hasNext() && !iterador2.hasNext()) {
    		
    	}
    	
    	return null;
    }
}
