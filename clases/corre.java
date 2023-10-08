public class corre {
    public static void main(String[] args){
        Lista expresion = new Lista();
        Pila operandoStack = new Pila();
        StringArray operandos = new StringArray(6);
        StringArray opLogicos = new StringArray(4);

        expresion.insert("(x+(y-z))"); // aqui luego cambio por el input del usuario
        operandos.insertAll("+", "-", "*", "/", "%", "**");
        opLogicos.insertAll("and", "or", "not", "xor");
    }
}
