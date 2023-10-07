public class corre {
    public static void main(String[] args){
        Pila stack = new Pila();

        stack.push("Apple");
        stack.push("Banana");
        stack.push("Cherry");

        while (!stack.isEmpty()) {
            System.out.println("Top element: " + stack.peek());
            System.out.println("Popped: " + stack.pop());
        }
    }
}
