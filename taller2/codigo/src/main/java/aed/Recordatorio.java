package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {

        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha); // Evito el aliasign
        this.horario = horario;
    }

    public Horario horario() {
        return this.horario;
    }

    public Fecha fecha() {

        return new Fecha(this.fecha);
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {

        return this.mensaje + " @ " + this.fecha + " " + this.horario;
    }

    @Override
    public boolean equals(Object otro) {

        boolean otroEsNull = (otro == null);
        boolean mismaClase = (otro.getClass() == this.getClass());
        if (mismaClase && !otroEsNull) {
            Recordatorio otroRecordatorio = (Recordatorio) otro;
            boolean mismoMensaje = otroRecordatorio.mensaje == this.mensaje;
            boolean mismaFecha = otroRecordatorio.fecha == this.fecha;
            boolean mismoHorario = otroRecordatorio.horario == this.horario;

            return mismoMensaje && mismaFecha && mismoHorario;
        }

        return false;
    }

}
