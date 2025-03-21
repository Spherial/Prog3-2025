package TP1;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> cursor;

    public LinkedListIterator(Node<T> cursor) {
        this.cursor = cursor;
    }

    //Si el cursor apunta a algo que no sea null, es porque aun hay elementos
    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    //Devolvemos la info del nodo actual, y apuntamos el cursor al nodo siguiente
    @Override
    public T next() {
        T infoActual = this.cursor.getInfo();
        this.cursor = this.cursor.getNext();
        return infoActual;
    }
}
