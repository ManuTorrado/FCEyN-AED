package aed;

public class Agenda {
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios recordatorios;

    public Agenda(Fecha fechaActual) {
        this.fechaActual = fechaActual;
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {

        this.recordatorios.agregarAtras(recordatorio);
        // this.recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String result = this.fechaActual + "\n" + "=====" + "\n";

        for (int i = 0; i < this.recordatorios.longitud(); i++) {
            if (this.fechaActual.equals(recordatorios.obtener(i).fecha())) {
                String formated = result + this.recordatorios.obtener(i).toString() + "\n";

                result = formated;
            }

        }

        return result;
    }

    public void incrementarDia() {
        this.fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return this.fechaActual;
    }

}
