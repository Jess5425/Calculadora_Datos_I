public class StringArray {
    private String[] data;
    private int size;

    public StringArray(int capacity) {
        this.data = new String[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return data[index];
    }

    public void set(int index, String value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        data[index] = value;
    }

    public void insert(int index, String value) {
        if (isFull()) {
            throw new IllegalStateException("Array is full");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    public void remove(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
    }

    public void add(String value) {
        if (isFull()) {
            throw new IllegalStateException("Array is full");
        }

        data[size] = value;
        size++;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        StringArray arr = new StringArray(5);

        arr.add("Rojo");
        arr.add("Rosado");
        arr.add("Naranja");

        arr.print();

        arr.insert(1, "Amarillo");
        arr.print();
//        arr.print();
//
//        arr.remove(2);
//        arr.print();
    }
}
