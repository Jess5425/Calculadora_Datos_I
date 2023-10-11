public class Lista {
    private NodoArbol head;
    private NodoArbol tail;
    private int longitud;

    public Lista() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(String data) {
        for (int i = 0; i < data.length(); i++) {
            NodoArbol newNode = new NodoArbol();
            newNode.data=String.valueOf(data.charAt(i));
            if (tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.left = tail; // Set the previous tail as the left child of the new node
            }
            tail = newNode;
        }
    }

    public void insertNode(NodoArbol dataNode) {
        NodoArbol newNode = new NodoArbol();
        newNode= dataNode;

        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.left = tail; // Set the previous tail as the left child of the new node
        }

        tail = newNode;
    }


    public void display() {
        NodoArbol current = head;
        while (current != null) {
            System.out.print(current.data + " , ");
            current = current.next;
        }
        System.out.println("null");
    }

    public int getSize(){
        NodoArbol current = head;
        while (current != null) {
            longitud++;
            current = current.next;
        }
        return longitud;
    }

    public NodoArbol getFirst(){
        return head;
    }
    public NodoArbol getLast(){
        return tail;
    }
}