import java.lang.reflect.Array;
import java.util.Objects;

public class ExpressionTree {
    NodoArbol root;
    NodoArbol left;
    NodoArbol right;

    public StringArray operandos;
    public StringArray opLogicos;
    Lista expresion; // x+(y-z)
    Pila operandoStack;


    public ExpressionTree(NodoArbol a){
        this.root=a;
        this.left=null;
        this.right=null;
    }

    private void creaArbol(Lista list){ //Método principal que toma una lista, evalúa cada nodo.
        Nodo current = list.getFirst();
        while (current!=null){
            revisaCaracter(current);
            current=current.next;
        }


    }

    private Boolean isOperator(StringArray conjunto, Nodo c){ //Método que revisa si es operator
        for (int i = 0; i < conjunto.getSize(); i++) {
            if (conjunto.get(i).equals(c.data)){
                return true;
            }
        }
        return false;
    }
    private void revisaCaracter(Nodo c){ //Método que si pertenece a operandos, llame a operandoHandler (crea subarbol)

        if (isOperator(operandos, c)){
            operandoHandler(c.data);
        }

        if (isOperator(opLogicos, c)){
            System.out.println("Aqui va el metodo para operandos lógicos");
        }else{
            operandoStack.push(c);
        }

    }

    private NodoArbol operandoHandler(String op){ // Método que crea un nuevo nodoArbol con dato operador, con referencias left y right a sus operandos
        NodoArbol temp = new NodoArbol();
        temp.data=op;
        this.root= temp;
//        this.right=operandoStack.pop();
//        this.left=operandoStack.pop();

        return temp; //quiero que temp vuelva a la lista como nodo
    }

    private Nodo procesarParentesis(Nodo current) { // divide la lista en el parentesis más pequeño posible
        Nodo start = current;
        Nodo end = null;
        int count = 0; //cuando encuentra ( count++, cuando encuentra ) count--, cuando count es 0 encontró la expresión más pequeña

        while (current != null) {
            if (current.data.equals("(")) {
                if (count == 0) {
                    start = current;
                }
                count++;
            } else if (current.data.equals(")")) {
                count--;
                if (count == 0) { //  asigna el nodo en el que está como el fin de la subexpresion y termina el loop
                    end = current;
                    break;
                }
            }
            current = current.next;
        }

        if (end != null) { // encuentra parentesis que cierra, hay una subexpresión en el parentesis
            Nodo subExpression = extractSubExpression(start, end);
            if (start == expresion.getFirst()) { // Si la subexpresion empieza al puro inicio de la lista, la vuelve a poner ahi
                expresion.insert(subExpression.data);
            } else {
                // Si no estaba al inicio, busca el nodo antes de start
                Nodo previous = getPrevious(expresion.getFirst(), start);
                previous.next = subExpression;
            }
            subExpression.next = end.next;
        }

        return expresion.getFirst(); // Return the updated reference
    }

    private Nodo extractSubExpression(Nodo start, Nodo end) {
        Nodo subExpressionHead = start.next;
        start.next = null;
        end.next = null;

        return subExpressionHead;
    }

    private Nodo getPrevious(Nodo current, Nodo target) {
        while (current != null && current.next != target) {
            current = current.next;
        }
        return current;
    }

}