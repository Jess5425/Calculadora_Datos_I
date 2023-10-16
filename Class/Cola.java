package Class;

public class Cola {
    private NodoArbol front;
    private NodoArbol rear;

    public Cola() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(NodoArbol nodo) {
        if (isEmpty()) {
            front = rear = nodo;
        } else {
            rear.next = nodo;
            rear = nodo;
        }
    }

    public NodoArbol dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacía, no se puede desencolar.");
        }

        NodoArbol nodo = front;
        front = front.next;

        if (front == null) {
            rear = null; // Si la cola está vacía, también actualizamos 'rear'
        }

        nodo.next = null; // Desvinculamos el nodo de la cola
        return nodo;
    }

    public NodoArbol peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cola vacía, no se puede observar.");
        }

        return front;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
