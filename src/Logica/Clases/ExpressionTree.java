public class ExpressionTree {
    NodoArbol root;
    NodoArbol left;
    NodoArbol right;

    public StringArray operators;
    public StringArray opLogicos;
    Lista expresion; // x+(y-z)
    Pila operandoStack;


    public ExpressionTree(NodoArbol a){
        this.root=a;
        this.left=null;
        this.right=null;
    }

    private void creaArbol(Lista caracteres){ // cambiar return nodoarbol ,quiero que me cree un nodo dada una lista de caracteres
        NodoArbol current = caracteres.getFirst();
        while (current !=null){
            revisaCaracter(current);
            current=current.next;
        }

    }

    private void evalue(Lista caracteres){ //Método principal que toma una lista, evalúa cada nodo.
        NodoArbol current = caracteres.getFirst();
        while (current !=null){
            revisaCaracter(current);
            current=current.next;
        }


    }

    private Boolean isOperator(StringArray conjunto, NodoArbol c){ //Método que revisa si es operator
        for (int i = 0; i < conjunto.getSize(); i++) {
            if (conjunto.get(i).equals(c.data)){
                return true;
            }
        }
        return false;
    }

    private void revisaCaracter(NodoArbol c) {
        NodoArbol start = c;
        boolean hayOperando = false;

        while (c != null) {
            if (!isOperator(operators, c)) {
                operandoStack.push(c);

                if (c == start) {
                    start = c.next;
                } else {
                    NodoArbol previous = getPrevious(start, c);
                    previous.next = c.next;
                }

                c = c.next;
                c.prev = null;
                hayOperando = true;
            } else {
                c = c.next;
            }
        }

        if (hayOperando) {
            revisaCaracter(start);
        } else if (start != null) {
            operatorHandler(start);
        }
    }




    private void operatorHandler(NodoArbol op){ // Método que crea un nuevo nodoArbol con dato operador, con referencias left y right a sus operandos
        op.left=operandoStack.pop();
        op.right=operandoStack.pop();

    }

    private NodoArbol procesarParentesis(NodoArbol current) { // divide la lista en el parentesis más pequeño posible
        NodoArbol start = current;
        NodoArbol end = null;
        //int count = 0; //cuando encuentra ( count++, cuando encuentra ) count--, cuando count es 0 encontró la expresión más pequeña

        while (current != null) {
            if (current.data.equals("(")) {
                start = current;//guarda referencia a donde empieza la subexpresion
                //count++;
            } else if (current.data.equals(")")) {
                end = current;
            }
            current = current.next;
        }

        if (end != null) { // encuentra parentesis que cierra, hay una subexpresión en el parentesis
            Lista subExpression = extractSubExpression(start, end);
            creaArbol(subExpression); // aqui
//            if (start == expresion.getFirst()) { // Si la subexpresion empieza al puro inicio de la lista, la vuelve a poner ahi
//                expresion.insert(subExpression.data); //al haber un elemento con puntero nulo, si se inserta va a este puntero?
//            } else {
//                // Si no estaba al inicio, busca el nodo antes del inicio de la subexpresion
//                NodoArbol previous = getPrevious(expresion.getFirst(), start);
//                previous.next = subExpression;
//            }
//            subExpression.next = end.next;  Arreglar esto, que el nodo arbol vuelva donde iba subexpresion
        }

        return expresion.getFirst(); // Return the updated reference
    }

    private Lista extractSubExpression(NodoArbol start, NodoArbol end) {// retorna el término después del parentesis, inicio de subexpresion
        NodoArbol current=start;
        Lista temporal = new Lista();
        while (current.next!=null){
            temporal.insertNode(current);
            current=current.next;
        }

        (start.prev).next=null; // quita las referencias a la subexpresión
        (end.next).prev=null;
        end.next=null;
        return temporal;
    }

    private NodoArbol getPrevious(NodoArbol current, NodoArbol target) {
        while (current != null && current.next != target) {
            current = current.next;
        }
        return current;
    }

}