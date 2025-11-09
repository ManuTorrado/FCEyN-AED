package aed;

public class ABB<T extends Comparable<T>> {
    private Nodo raiz;

    private class Nodo {
        private T value;
        private Nodo left;
        private Nodo right;
        private Nodo parent;

        public Nodo(T value, Nodo parent) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }
    }

    public class HandleABB {
        private Nodo referencia;

        public HandleABB(Nodo referencia) {
            this.referencia = referencia;
        }

        public T valor() {
            return referencia.value;
        }

        public void eliminar() {
            ABB.this.eliminar(referencia.value);
        }
    }

    public ABB() {
        this.raiz = null;
    }

    public T maximo() {
        Nodo actual = raiz;
        if (actual == null)
            return null;
        while (actual.right != null)
            actual = actual.right;
        return actual.value;
    }

    public T minimo() {
        Nodo actual = raiz;
        if (actual == null)
            return null;
        while (actual.left != null)
            actual = actual.left;
        return actual.value;
    }

    public int cardinal() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo n) {
        if (n == null)
            return 0;
        return 1 + contarNodos(n.left) + contarNodos(n.right);
    }

    public HandleABB insertar(T elem) {
        if (raiz == null) {
            raiz = new Nodo(elem, null);
            return new HandleABB(raiz);
        }

        Nodo actual = raiz;
        Nodo padre = null;

        while (actual != null) {
            padre = actual;
            if (elem.compareTo(actual.value) < 0)
                actual = actual.left;
            else
                actual = actual.right;
        }

        Nodo nuevo = new Nodo(elem, padre);
        if (elem.compareTo(padre.value) < 0)
            padre.left = nuevo;
        else
            padre.right = nuevo;

        return new HandleABB(nuevo);
    }

    public boolean pertenece(T elem) {
        Nodo actual = raiz;
        while (actual != null) {
            int cmp = elem.compareTo(actual.value);
            if (cmp == 0)
                return true;
            actual = (cmp < 0) ? actual.left : actual.right;
        }
        return false;
    }

    public void eliminar(T elem) {
        raiz = eliminarRec(raiz, elem);
        if (raiz != null)
            raiz.parent = null;
    }

    private Nodo eliminarRec(Nodo nodo, T elem) {
        if (nodo == null)
            return null;

        int cmp = elem.compareTo(nodo.value);

        if (cmp < 0) {
            nodo.left = eliminarRec(nodo.left, elem);
            if (nodo.left != null)
                nodo.left.parent = nodo;
        } else if (cmp > 0) {
            nodo.right = eliminarRec(nodo.right, elem);
            if (nodo.right != null)
                nodo.right.parent = nodo;
        } else {
            // sin hijos
            if (nodo.left == null && nodo.right == null)
                return null;

            // un hijo
            if (nodo.left == null) {
                nodo.right.parent = nodo.parent;
                return nodo.right;
            }
            if (nodo.right == null) {
                nodo.left.parent = nodo.parent;
                return nodo.left;
            }

            // dos hijos → sucesor
            Nodo sucesor = nodoMinimo(nodo.right);
            nodo.value = sucesor.value;
            nodo.right = eliminarRec(nodo.right, sucesor.value);
            if (nodo.right != null)
                nodo.right.parent = nodo;
        }
        return nodo;
    }

    private Nodo nodoMinimo(Nodo n) {
        while (n.left != null)
            n = n.left;
        return n;
    }

    private Nodo nodoMinimo() {
        if (raiz == null)
            return null;
        return nodoMinimo(raiz);
    }

    private Nodo sucesor(Nodo n) {
        if (n.right != null)
            return nodoMinimo(n.right);

        Nodo p = n.parent;
        while (p != null && n == p.right) {
            n = p;
            p = p.parent;
        }
        return p;
    }

    @Override
    public String toString() {
        String res = "{";
        ABB_Iterador it = iterador();
        if (it.haySiguiente())
            res = res + it.siguiente();
        while (it.haySiguiente())
            res = res + ", " + it.siguiente();
        return res + "}";
    }

    public class ABB_Iterador {
        private Nodo actual;

        public ABB_Iterador() {
            actual = nodoMinimo();
        }

        public boolean haySiguiente() {
            return actual != null;
        }

        public T siguiente() {
            if (actual == null)
                return null;
            T val = actual.value;
            actual = sucesor(actual);
            return val;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }
}
