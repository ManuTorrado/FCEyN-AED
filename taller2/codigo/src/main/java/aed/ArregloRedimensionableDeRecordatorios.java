package aed;

class ArregloRedimensionableDeRecordatorios {

    private Recordatorio[] elementos;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.elementos = new Recordatorio[0];
    }

    public int longitud() {
        return elementos.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] listaExtendida = new Recordatorio[this.elementos.length + 1];

        for (int j = 0; j < this.elementos.length; j++) {
            listaExtendida[j] = this.elementos[j];
        }

        listaExtendida[this.elementos.length] = i;
        this.elementos = listaExtendida;
    }

    public Recordatorio obtener(int i) {

        return this.elementos[i];
    }

    public void quitarAtras() {
        Recordatorio[] listaReducida = new Recordatorio[this.elementos.length - 1];
        for (int j = 0; j < listaReducida.length; j++) {
            listaReducida[j] = this.elementos[j];
        }
        this.elementos = listaReducida;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.elementos[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        int longitud = vector.longitud();
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios();
        for (int j = 0; j < longitud; j++) {
            copia.agregarAtras(vector.obtener(j));
        }
        this.elementos = copia.elementos;
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios(this);

        return copia;
    }
}
