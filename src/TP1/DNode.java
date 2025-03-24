package TP1;

public class DNode<T>{

    private T info;
    private DNode<T> next;
    private DNode<T> previous;

    public DNode() {
        this.info = null;
        this.next = null;
        this.previous = null;
    }

    public DNode(T info,DNode<T> previous ,DNode<T> next) {
        this.setInfo(info);
        this.setNext(next);
        this.setPrevious(previous);
    }

    public DNode<T> getNext() {
        return next;
    }

    public DNode<T> getPrevious() { return previous; }

    public void setNext(DNode<T> next) {
        this.next = next;
    }

    public void setPrevious(DNode<T> previous) { this.previous = previous; }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "DNode{" +
                "info=" + info +
                ", next=" + (next != null ? next.getInfo() : "(null)") +
                ", previous=" + (previous != null ? previous.getInfo() : "(null)") +
                '}';
    }

}