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
//        System.out.println("---WHILE---");
//        Iterator<String> it = nombres.iterator();
//        while (it.hasNext()){
//            System.out.println("Imprimiendo elemento: " + it.next());
//        }

        //Iterator usando foreach
        System.out.println("---FOREACH---");
        for (String nombre : nombres){
            System.out.println("Imprimiendo elemento: " + nombre);
        }




//        MySimpleLinkedList<Integer> numeros1 = new MySimpleLinkedList<>();
//        numeros1.insertFront(25);
//        numeros1.insertFront(22);
//        numeros1.insertFront(20);
//        numeros1.insertFront(16);
//
//        MySimpleLinkedList<Integer> numeros2 = new MySimpleLinkedList<>();
//        numeros2.insertFront(26);
//        numeros2.insertFront(22);
//        numeros2.insertFront(8);
//        numeros2.insertFront(5);
//
//
//        System.out.println(numeros1);
//        System.out.println(numeros2);
//
//        MySimpleLinkedList<Integer> comunes = elementosComunes(numeros1,numeros2);
//        System.out.println(comunes);



        MySimpleLinkedList<Integer> numeros1 = new MySimpleLinkedList<>();
        numeros1.insertFront(30);
        numeros1.insertFront(22);
        numeros1.insertFront(20);
        numeros1.insertFront(15);
        numeros1.insertFront(10);

        MySimpleLinkedList<Integer> numeros2 = new MySimpleLinkedList<>();
        numeros2.insertFront(35);
        numeros2.insertFront(30);
        numeros2.insertFront(5);
        numeros2.insertFront(21);
        numeros2.insertFront(10);


        System.out.println(numeros1);
        System.out.println(numeros2);

        MySimpleLinkedList<Integer> comunes = elementosComunesB(numeros1,numeros2);
        System.out.println(comunes);
        
        
        
        System.out.println("EJERCICIO A");
        MySimpleLinkedList<Integer>  comunesA = elementosComunesA(numeros1,numeros2);
        
        System.out.println(comunesA);













        //------------------LISTA DOBLEMENTE VINCULADA

        MyDoubleLinkedList<String> apellidos = new MyDoubleLinkedList<>();

        System.out.println("_----------------------------------------------------_");
        apellidos.insertFront("Aceto");
        apellidos.insertLast("Ramirez");
        apellidos.insertFront("EL PRIMEROOO");
        System.out.println(apellidos);











    }

    //Ambas listas vienen ordenadas, el resultado debe estar ordenado
    public static MySimpleLinkedList<Integer> elementosComunesB(MySimpleLinkedList<Integer> lista1, MySimpleLinkedList<Integer> lista2){
    	MySimpleLinkedList<Integer> resultado = new MySimpleLinkedList<>();

    	LinkedListIterator<Integer> iterador1 = lista1.iterator();
    	LinkedListIterator<Integer> iterador2 = lista2.iterator();

    	while(iterador1.hasNext() && iterador2.hasNext()) {
    		if (iterador1.getInfo() > iterador2.getInfo()){
                iterador2.next();
            }
            else if(iterador1.getInfo() < iterador2.getInfo()){
                iterador1.next();
            }
            else{
                resultado.insertLast(iterador1.getInfo());
                iterador1.next();
                iterador2.next();
            }

    	}

    	return resultado;
    }

    //Ambas listas desordenadas, el resultado debe estar ordenado
    public static MySimpleLinkedList<Integer> elementosComunesA(MySimpleLinkedList<Integer> lista1, MySimpleLinkedList<Integer> lista2){
        MySimpleLinkedList<Integer> resultado = new MySimpleLinkedList<>();

        for (int elemento : lista1) {
        	if (lista2.indexOf(elemento) != -1) {
        		resultado.insertFront(elemento);
        	}
        }

        //TODO

        return resultado;
    }







}
