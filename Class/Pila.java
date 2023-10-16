package Class;

public class Pila {
    private NodoArbol top;

    public Pila() {
        this.top = null;
    }

    public void push(NodoArbol nodo) {
        nodo.next = top;
        top = nodo;
    }

    public NodoArbol pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacía, no insista >:(");
        }

        NodoArbol nodo = top;
        top = top.next;
        nodo.next = null; // Detach the node from the stack
        return nodo;
    }

    public NodoArbol peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacía, no insista >:(");
        }

        return top;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

