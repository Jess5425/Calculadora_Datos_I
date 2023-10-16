package Class;

public class corre {
    public static void main(String[] args){

        Lista expresion = new Lista();
        //Pila operandoStack = new Pila();

        StringArray operandos = new StringArray(6);

        StringArray opLogicos = new StringArray(4);

//        opLogicos.insertAll("&", "|", "^", "~");
//        operandos.insertAll("+", "-", "*", "/", "%", "**");
        expresion.insert("(x+(y-z))"); // aquí luego cambia por el input del usuario
        ExpressionTree tree = new ExpressionTree();
        //hasta aqui sirve el código
        tree.evalueLista(expresion);
        System.out.println("7");
        tree.printTree(tree.root, 0);
        System.out.println("8");
    }
}
