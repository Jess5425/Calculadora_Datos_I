package Class;

public class StringArray {
    private String[] data;
    private int size;

    public StringArray(int capacity) {
        this.data = new String[capacity];
        this.size = 0;
    }

    public void insertAll(String... elements) {
        for (String element : elements) {
            if (isFull()) {
                throw new IllegalStateException("Array is full");
            }
            data[size] = element;
            size++;
        }
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


    private boolean isFull() {
        return size == data.length;
    }

    private boolean isEmpty() {
        return size == 0;
    }
    public int getSize(){
        return size;
    }
}
