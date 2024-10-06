package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    private class Nodo {
        private T element;
        private Nodo next;
        private Nodo prev;

        public Nodo(T element, Nodo next, Nodo prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public Nodo next() {
            return this.next;
        }

        public Nodo prev() {
            return this.prev;
        }

        public void SetNext(Nodo next) {
            this.next = next;
        }

        public void ModifyNode(T element) {
            this.element = element;
        }

        public void SetPrev(Nodo prev) {
            this.prev = prev;
        } // cambia la direccion del puntero

    }

    public ListaEnlazada() {

    }

    public int longitud() {
        return this.longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo;
        if (this.primero == null) {
            nuevo = new Nodo(elem, null, null);
            this.longitud++;
            this.primero = nuevo;
            return;
        }
        nuevo = new Nodo(elem, this.primero, null);
        this.primero.SetPrev(nuevo);
        this.longitud++;
        this.primero = nuevo;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo;

        if (this.primero == null) {
            nuevo = new Nodo(elem, null, null);
            this.primero = nuevo;
            this.longitud++;
            return;
        }

        if (this.ultimo == null) {

            int a = 0;
            Nodo anteultimo = this.primero;
            while (a < this.longitud - 1) {
                anteultimo = anteultimo.next();
                a++;
            }
            nuevo = new Nodo(elem, null, anteultimo);
            this.longitud++;
            this.ultimo = nuevo;
            anteultimo.SetNext(this.ultimo);
            return;
        }

        nuevo = new Nodo(elem, null, this.ultimo);
        this.ultimo.SetNext(nuevo);
        this.longitud++;
        this.ultimo = nuevo;
    }

    public T obtener(int i) {

        if (i > this.longitud)
            return null;

        int a = 0;
        Nodo current = this.primero;
        while (a <= i) {
            if (a == i) {
                return current.element;
            }
            current = current.next();
            a++;
        }
        return null;
    }

    public void eliminar(int i) {

        int a = 0;
        Nodo current = this.primero;
        while (a < i) {
            current = current.next();
            a++;
        }

        Nodo previo = current.prev();
        Nodo siguiente = current.next();

        if (previo == null && siguiente == null) {
            this.longitud = 0;
            return;
        }

        if (siguiente == null) {
            previo.SetNext(siguiente);
            this.ultimo = previo;
            this.longitud--;
            return;
        }

        if (previo == null) {
            siguiente.SetPrev(previo);
            this.primero = siguiente;
            this.longitud--;
            return;
        }

        this.longitud--;
        previo.SetNext(siguiente);
        siguiente.SetPrev(previo);

    }

    public void modificarPosicion(int indice, T elem) {

        int a = 0;
        Nodo current = this.primero;
        while (a < this.longitud) {
            if (a == indice) {
                current.ModifyNode(elem);
            }
            current = current.next();
            a++;
        }
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this.longitud = 0;

        for (int i = 0; i < lista.longitud(); i++) {
            this.agregarAtras(lista.obtener(i));
        }

    }

    @Override
    public String toString() {
        String lista = "[";
        for (int i = 0; i < this.longitud; i++) {

            if (i == 0) {
                lista = lista + this.obtener(i) + ',';
                continue;
            }

            lista = lista + " " + this.obtener(i);
            if (i != this.longitud - 1) {
                lista += ',';
            }
        }
        lista = lista + "]";
        return lista;
    }

    private class ListaIterador implements Iterador<T> {

        private int pos = 0;

        public boolean haySiguiente() {
            return this.pos < longitud();
        }

        public boolean hayAnterior() {
            return !(this.pos == 0);
        }

        public T siguiente() {

            if (haySiguiente()) {
                int posicion = this.pos;
                this.pos++;
                return obtener(posicion);
            }
            return null;
        }

        public T anterior() {
            if (hayAnterior()) {
                this.pos--;
                return obtener(pos);
            }
            return null;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
