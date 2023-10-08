public class Pila {
    private Nodo top;

    public Pila() {
        this.top = null;
    }

    public void push(Nodo data) {
        (data).next = top;
        top = (data);
    }

    public String pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacía, no insista >:(");
        }

        String data = top.data;
        top = top.next;
        return data;
    }

    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Pila vacía, no insista >:(");
        }

        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}