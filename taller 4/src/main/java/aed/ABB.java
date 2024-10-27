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

    private Nodo nodoMinimo() {
        Nodo root = this.raiz;
        while (root.izq != null) {
            root = root.izq;
        }
        return root;

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
        // caso tiene subarbol derecho
        Nodo res;
        if (nodo.der != null) {
            res = nodo.der;
            while (res.izq != null) {
                res = res.izq;
            }
        } else {
            // caso contrario: no tiene subarbol derecho
            // el siguiente es el primer padre de un subarbol izquierdo

            res = nodo.padre;
            while (res != null && res.der != null && res.der.valor.equals(nodo.valor)) {
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

    public String toString() {

        if (this.raiz == null) {
            return "{}";
        }
        ABB_Iterador it = new ABB_Iterador();

        if (!it.haySiguiente()) {
            return "{" + this.raiz.valor + "}";
        }

        String res = "{";

        Boolean primeraIteracion = true;
        while (it.haySiguiente()) {

            if (primeraIteracion) {
                res += it.siguiente();

                primeraIteracion = false;
                continue;
            }

            res += "," + it.siguiente();

        }

        res += "," + maximo() + "}";

        return res;
    }

    public void eliminar(T elem) {
        // Eliminacion recursiva, comienzo desde la raiz hasta llegar al elemento (si es
        // que se encuentra, de lo contrario, queda que raiz = raiz)
        this.raiz = eliminacionRecursiva(raiz, elem);

    }

    private Nodo eliminacionRecursiva(Nodo NodoActual, T elem) {

        if (NodoActual == null) {
            return null;
        }

        // Recorro el arbol
        if (elem.compareTo(NodoActual.valor) > 0) {
            NodoActual.der = eliminacionRecursiva(NodoActual.der, elem);
        } else if (elem.compareTo(NodoActual.valor) < 0) {
            NodoActual.izq = eliminacionRecursiva(NodoActual.izq, elem);
        } else { // Se encontro un elemento

            // Caso sin hijos
            if (NodoActual.izq == null && NodoActual.der == null) {

                this.cardinal--;
                return null;
            } else if (NodoActual.izq == null) {
                // Caso un hijo

                this.cardinal--;
                return NodoActual.der;
            }
            if (NodoActual.der == null) {
                this.cardinal--;
                return NodoActual.izq;
            } else {

                // Caso dos hijos

                Nodo Sucesor = sucesor(NodoActual);
                NodoActual.valor = Sucesor.valor;
                NodoActual.der = eliminacionRecursiva(NodoActual.der, Sucesor.valor);
            }

        }
        return NodoActual;
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {
            return !(this._actual.valor == maximo());

        }

        public ABB_Iterador() {
            if (maximo() == null) {

            }
            this._actual = nodoMinimo();
        }

        @Override
        public T siguiente() {

            if (haySiguiente()) {
                T res = this._actual.valor;
                this._actual = sucesor(this._actual);
                return res;
            }

            if (this._actual.valor == maximo()) {
                T res = this._actual.valor;
                this._actual = null;
                return res;
            }

            return null;
        }

    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
