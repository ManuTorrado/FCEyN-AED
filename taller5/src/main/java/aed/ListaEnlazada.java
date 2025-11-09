package aed;

public class ListaEnlazada<T> {

    private class Nodo {
        T valor;
        Nodo next;

        Nodo(T v) {
            valor = v;
            next = null;
        }
    }

    private Nodo primero;
    private int longitud;

    public ListaEnlazada() {
        primero = null;
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.next = primero;
        primero = nuevo;
        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
        longitud++;
    }

    private Nodo obtenerNodo(int i) {

        Nodo actual = primero;
        for (int k = 0; k < i; k++) {
            actual = actual.next;
        }
        return actual;
    }

    public T obtener(int i) {
        return obtenerNodo(i).valor;
    }

    public void eliminar(int i) {

        if (i == 0) {
            primero = primero.next;
        } else {
            Nodo anterior = obtenerNodo(i - 1);
            anterior.next = anterior.next.next;
        }

        longitud--;
    }

    public void modificarPosicion(int i, T elem) {
        obtenerNodo(i).valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {

        ListaIterador it = lista.iterador();
        while (it.haySiguiente()) {
            agregarAtras(it.siguiente());
        }
    }

    @Override
    public String toString() {
        String res = "[";
        Nodo actual = primero;

        if (actual != null) {
            res = res + actual.valor;
            actual = actual.next;
        }

        while (actual != null) {
            res = res + ", " + actual.valor;
            actual = actual.next;
        }

        res = res + "]";
        return res;
    }

    public class ListaIterador {
        private Nodo actual;

        public ListaIterador() {
            actual = primero;
        }

        public boolean haySiguiente() {
            return actual != null;
        }

        public T siguiente() {

            T valor = actual.valor;
            actual = actual.next;
            return valor;
        }

    }

    public ListaIterador iterador() {
        return new ListaIterador();
    }
}
