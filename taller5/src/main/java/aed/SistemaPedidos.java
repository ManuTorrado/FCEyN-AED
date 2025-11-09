package aed;

public class SistemaPedidos {

    private ListaEnlazada<Pedido> cola;

    private ABB<Pedido> arbol;

    public SistemaPedidos() {
        this.cola = new ListaEnlazada<>();
        this.arbol = new ABB<>();
    }

    public void agregarPedido(Pedido pedido) {

        cola.agregarAtras(pedido);

        arbol.insertar(pedido);
    }

    public Pedido proximoPedido() {
        if (cola.longitud() == 0)
            return null;

        Pedido p = cola.obtener(0);

        arbol.eliminar(p);

        cola.eliminar(0);
        return p;
    }

    public Pedido pedidoMenorId() {

        return arbol.minimo();

    }

    public String obtenerPedidosEnOrdenDeLlegada() {

        return cola.toString();
    }

    public String obtenerPedidosOrdenadosPorId() {
        String res = "{";
        ABB<Pedido>.ABB_Iterador it = arbol.iterador();

        if (it.haySiguiente()) {
            res = res + it.siguiente();
        }

        while (it.haySiguiente()) {
            res = res + ", " + it.siguiente();
        }

        res = res + "}";
        return res;
    }
}