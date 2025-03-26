package TP1;

import java.util.Iterator;

public class MySimpleLinkedList<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> first;
    private Node<T> last;
    private int size;

    public MySimpleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void insertFront(T info) {
        Node<T> nuevoNodo = new Node<>(info,null);
        nuevoNodo.setNext(this.first);
        this.first = nuevoNodo;

        //Si la lista esta vacia, el primer nodo tambien es el ultimo
        if(this.size == 0){
            this.last = nuevoNodo;
        }

        this.size+=1;
    }

    public void insertLast(T info) {
        Node<T> nuevoNodo = new Node<>(info, null);

        // Si es lista vacia, el nodo es tanto el primero como el ultmo
        if (this.first == null) {
            this.first = nuevoNodo;
            this.last = nuevoNodo;
        } else {
            // Sino, el ultimo nodo apunta al nuevo (y el nuevo pasa a ser el nuevo ultimo)
            this.last.setNext(nuevoNodo);
            this.last = nuevoNodo;
        }

        this.size+=1;
    }


    public T extractFront() {
        Node<T> nodoActual = this.first;
        Node<T> nodoSiguiente = this.first.getNext();
        this.first = nodoSiguiente;


        //Si la lista queda vacia, el last tambien es null
        if (this.first == null){
            this.last = null;
        }


        this.size-=1;
        return nodoActual.getInfo();
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public T get(int index) {

        if (index >= this.size || index < 0){
            throw new IllegalArgumentException();
        }

        Node<T> nodoActual = this.first;
        int contador = 0;

        while (contador < this.size){
            if (contador == index){
                return nodoActual.getInfo();
            }
            nodoActual = nodoActual.getNext();
            contador++;
        }

        return null;

    }

    public int indexOf(T info){
        int contador = 0;
        Node<T> nodoActual = this.first;

        while (contador < this.size){
            if (nodoActual.getInfo().equals(info)){
                return contador;
            }
            contador++;
            nodoActual = nodoActual.getNext();
        }

        return -1;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String resultado = "";
        Node<T> nodoActual = this.first;
        while (nodoActual != null){
            resultado +=  nodoActual.getInfo() + " - ";
            nodoActual = nodoActual.getNext();

        }

    return resultado;
    }


    //Para que se pueda iterar, devuelvo un iterador que arranque desde el primer nodo de la lista
    @Override
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<>(this.first);
    }
    
    
    
    
    
}