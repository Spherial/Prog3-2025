package TP1;

public class MyDoubleLinkedList<T> implements Iterable<T>{

    private DNode<T> first;
    private DNode<T> last;
    private int size;

    public MyDoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void insertFront(T info) {
        DNode<T> nuevoNodo = new DNode<>(info,null,null);
        nuevoNodo.setNext(this.first);

        // Si habia un primer nodo, su "anterior" será el nuevo primer nodo
        if (this.first != null) {
            this.first.setPrevious(nuevoNodo);
        }


        this.first = nuevoNodo;

        //Si la lista esta vacia, el primer nodo tambien es el ultimo
        if(this.size == 0){
            this.last = nuevoNodo;
        }

        this.size+=1;
    }

    public void insertLast(T info) {
        DNode<T> nuevoNodo = new DNode<>(info, null, null);

        // Si la lista está vacía, el nuevo nodo es tanto el primero como el último
        if (this.first == null) {
            this.first = nuevoNodo;
            this.last = nuevoNodo;
        } else {
            // Si la lista no está vacia, actualizo las referencias de los nodos
            nuevoNodo.setPrevious(this.last);  // El nuevo nodo apunta al anterior (last)
            this.last.setNext(nuevoNodo);      // El last actual apunta al nuevo nodo
            this.last = nuevoNodo;             // Ahora el nuevo nodo es el último
        }

        this.size += 1;
    }


    public T extractFront() {
        DNode<T> nodoActual = this.first;
        DNode<T> nodoSiguiente = this.first.getNext();
        this.first = nodoSiguiente;


        //Si la lista queda vacia, el last tambien es null
        if (this.first == null){
            this.last = null;
        }
        else{
            this.first.setPrevious(null);
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

        DNode<T> nodoActual = this.first;
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
        DNode<T> nodoActual = this.first;

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
        DNode<T> nodoActual = this.first;
        while (nodoActual != null){
            resultado +=  "\n - " + nodoActual.toString();
            nodoActual = nodoActual.getNext();

        }

    return resultado;
    }


    //Para que se pueda iterar, devuelvo un iterador que arranque desde el primer nodo de la lista
    //TODO crear iterator para DNodes
    @Override
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<>(null);
    }
    
    
    
    
    
}