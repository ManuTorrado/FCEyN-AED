package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private int cardinal;
    private int altura;
    private Nodo raiz;

    private class Nodo {
        private T valor;
        private Nodo izq;
        private Nodo der;
        private Nodo padre;

        public Nodo(T v) {
            this.valor = v;
            this.izq = null;
            this.der = null;
            this.padre = null;
        }

    }

    public ABB() {
        this.cardinal = 0;
        this.altura = 0;
        this.raiz = null;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo() {
        Nodo root = this.raiz;
        while (root.izq != null) {
            root = root.izq;
        }
        return root.valor;

    }

    public T maximo() {

        Nodo root = this.raiz;
        while (root.der != null) {
            root = root.der;
        }

        return root.valor;

    }

    public void insertar(T elem) {
        Nodo n = new Nodo(elem);

        // Caso arbol vacio
        if (this.raiz == null) {
            this.raiz = n;
            this.cardinal++;
            return;
        }

        Nodo root = this.raiz;
        int camino;
        while (root != null) {
            camino = elem.compareTo(root.valor);

            if (camino == 0) {
                return;
            }

            if (camino < 0) {
                if (root.izq == null) {
                    root.izq = n;
                    root.izq.padre = root;
                    this.cardinal++;
                    return;
                }
                root = root.izq; // Muevo hacia la izquierda
            }

            if (camino > 0) {
                if (root.der == null) {
                    root.der = n;
                    root.der.padre = root;
                    this.cardinal++;
                    return;
                }
                root = root.der; // Muevo hacia la derecha
            }

        }

    }

    private Nodo sucesor(Nodo nodo) {
        Nodo res;

        // Caso 1: Si tiene subárbol derecho, el sucesor es el menor en ese subárbol
        if (nodo.der != null) {
            res = nodo.der;
            // Busca el nodo más a la izquierda del subárbol derecho
            while (res.izq != null) {
                res = res.izq;
            }
        } else {
            // Caso 2: Si no tiene subárbol derecho, subir al padre hasta que el nodo sea
            // hijo izquierdo
            Nodo hijo = nodo;
            res = nodo.padre;
            while (res != null && res.der == hijo) {
                hijo = res;
                res = res.padre;
            }
        }

        return res;
    }

    public boolean pertenece(T elem) {

        // Caso arbol vacio
        if (this.raiz == null) {
            return false;
        }

        int camino;
        Nodo Root = this.raiz;
        while (Root != null) {
            camino = elem.compareTo(Root.valor);
            if (Root.valor.equals(elem))
                return true;
            if (camino < 0) {
                Root = Root.izq;
            } else {
                Root = Root.der;
            }
        }
        return false;
    }

    private boolean esRaiz(Nodo n) {
        return (this.raiz == n);
    }

    public void eliminar(T elem) {

        int camino;
        Nodo Root = this.raiz;
        while (Root != null) {
            camino = elem.compareTo(Root.valor);
            if (Root.valor.equals(elem)) {

                Nodo padre = Root.padre;

                // Caso sin descendencia
                if (Root.izq == null && Root.der == null) {
                    Root = null;
                }

                // Caso 2 hijos
                if (Root.izq != null && Root.der != null) {
                    Nodo suc = sucesor(Root);
                    if (esRaiz(Root)) {
                        this.raiz = suc;
                        this.raiz.izq = Root.izq;
                        this.raiz.der = Root.der;
                        this.cardinal--;
                        return;
                    }

                    // Redirecciono los hijos al padre

                    if (padre.izq == Root) {
                        padre.izq = suc;
                        suc.padre = padre;
                    }
                    if (padre.der == Root) {
                        padre.der = suc;
                        suc.padre = padre;
                    }
                }

                // Caso 1 hijo (derecha)
                if (Root.der != null && Root.izq == null) {

                    if (esRaiz(Root)) {
                        Nodo suc = sucesor(Root);
                        this.raiz = suc;
                        this.raiz.der = Root.der;
                        this.cardinal--;
                        return;
                    }

                    Root = Root.der;
                    if (padre.izq == Root) {
                        padre.izq = Root;
                    } else {
                        padre.der = Root;
                    }

                }

                // Caso 1 hijo (izquierda)
                if (Root.izq != null && Root.der == null) {

                    if (esRaiz(Root)) {
                        Nodo suc = sucesor(Root);
                        this.raiz = suc;
                        this.raiz = Root.izq;
                        this.cardinal--;
                        return;
                    }

                    Root = Root.izq;
                    if (padre.izq == Root) {
                        padre.izq = Root;
                    } else {
                        padre.der = Root;
                    }

                }

                this.cardinal--;
                return;
            }

            if (camino < 0) {
                Root = Root.izq;
            } else {
                Root = Root.der;
            }
        }

    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {

            throw new UnsupportedOperationException("No implementada aun");
        }

        @Override
        public T siguiente() {

            throw new UnsupportedOperationException("Unimplemented method 'siguiente'");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
