public class Lista {
    private Nodo head;
    private Nodo tail;

    public Lista() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(String data) {
        for (int i = 0; i < data.length(); i++) {
            Nodo newNode = new Nodo(String.valueOf(data.charAt(i)));
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
    }

    public void display() {
        Nodo current = head;
        while (current != null) {
            System.out.print(current.data + " , ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Lista list = new Lista();

        list.insert("(x+(y-z))");

        list.display();
    }
}