package Class;

public class NodoArbol {
    String data;
    NodoArbol left;
    NodoArbol right;
    NodoArbol next;
    NodoArbol prev;

    public NodoArbol(){
        this.data=null;
        this.next=null;
        this.left=null;
        this.right=null;
        this.prev=null;
    }
}

