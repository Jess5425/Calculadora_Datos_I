package Class;

public class ExpressionTree {
    NodoArbol root;
    NodoArbol left;
    NodoArbol right;

    public StringArray operators;
    public StringArray opLogicos;
    //Lista expresion; // x+(y-z)
    Pila operandoStack;



    public ExpressionTree(NodoArbol a){
        this.root=a;
        this.left=null;
        this.right=null;
    }

    public ExpressionTree(){
        this.root=null;
        this.left=null;
        this.right=null;

        this.operators = new StringArray(6); // Initialize operators array
        this.opLogicos = new StringArray(4); // Initialize opLogicos array
        this.operandoStack = new Pila();

        // Populate the arrays
        operators.insertAll("+", "-", "*", "/", "%", "**");
        opLogicos.insertAll("&", "|", "^", "~");
    }

    private void creaArbol(Lista caracteres){ // cambiar return nodoarbol ,quiero que me cree un nodo dada una lista de caracteres
        System.out.println("creaArbol");
        NodoArbol current = caracteres.getFirst();
        while (current !=null){
            revisaCaracter(current);
            current=current.next;
        }

    }

    private void evalue(Lista caracteres){
        System.out.println("evalue");
        Lista nodosYOperadores = procesarParentesis(caracteres);
        creaArbol(nodosYOperadores);
    }

    private Boolean isOperator(StringArray conjunto, NodoArbol c){ //Método que revisa si es operator
        System.out.println("isOperator");
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

                NodoArbol nextNode = c.next;  // Store the next node in a variable
                if (nextNode != null) {  // Add a null check here
                    nextNode.prev = c.prev;
                }

                c = nextNode;  // Update c to the next node
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






    private void operatorHandler(NodoArbol op) {
        NodoArbol resultNode = new NodoArbol();
        resultNode.data = op.data;
        resultNode.left = op.right;  // The right operand is the left child of the result node
        resultNode.right = op.left;  // The left operand is the right child of the result node
        operandoStack.push(resultNode);
    }



    private Lista procesarParentesis(Lista expresion) {

        System.out.println("procesarParentesis");
        NodoArbol current = expresion.getFirst();
        NodoArbol start = null;
        NodoArbol end = null;

        // Itera a través de los nodos de la expresión
        while (current != null) {
            if (current.data.equals("(")) {  // Si el nodo actual es un paréntesis abierto:
                start = current;  // Marca este nodo como el inicio del paréntesis.
            } else if (current.data.equals(")")) {  // Si el nodo actual es un paréntesis cerrado:
                end = current;  // Marca este nodo como el fin del paréntesis.
                break;
            }
            current = current.next;
        }

        // Si se encontraron un par de paréntesis válido
        if (start != null && end != null) {
            // Extrae la subexpresión entre los paréntesis
            Lista subExpression = extractSubExpression(start, end);

            // Crea un árbol de expresión para la subexpresión
            creaArbol(subExpression);

            // Si el paréntesis estaba al inicio de la expresión
            if (start == expresion.getFirst()) {
                expresion.insertNode(subExpression.getFirst());  // Inserta la subexpresión en la lista original.
            } else {
                // Encuentra el nodo anterior al inicio de la subexpresión
                NodoArbol previous = getPrevious(expresion.getFirst(), start);
                previous.next = subExpression.getFirst();  // Conecta el nodo anterior al inicio de la subexpresión.
            }

            // Conecta el último nodo de la subexpresión al siguiente después del paréntesis cerrado
            subExpression.getLast().next = end.next;

            // Si hay un nodo después del paréntesis cerrado
            if (end.next != null) {
                end.next.prev = subExpression.getLast();  // Conecta el nodo siguiente al anterior de la subexpresión.
            }

            // Llamada recursiva para procesar más paréntesis.
            return procesarParentesis(expresion);
        }

        return expresion;  // Retorna la lista de expresión actualizada.
    }


    private Lista extractSubExpression(NodoArbol start, NodoArbol end) {
        System.out.println("extractSub");
        NodoArbol current = start;
        Lista temporal = new Lista();
        while (current.next != null) {
            temporal.insertNode(current);
            current = current.next;
        }

        if (start.prev != null) {
            start.prev.next = null; // quita las referencias a la subexpresión
        }
        if (end.next != null) {
            end.next.prev = null;
        }
        end.next = null;
        return temporal;
    }


    private NodoArbol getPrevious(NodoArbol current, NodoArbol target) {
        System.out.println("getPrev");
        while (current != null && current.next != target) {
            current = current.next;
        }
        return current;
    }

    public void printTree(NodoArbol node, int depth) {
        System.out.println("printTree");
        if (node != null) {
            printTree(node.right, depth + 1);
            System.out.println("   ".repeat(depth) + node.data);
            printTree(node.left, depth + 1);
        }
    }

    public void evalueLista(Lista list){
        evalue(list);
    }

}