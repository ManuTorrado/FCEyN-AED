package aed;

public class Fecha {

    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {

    }

    public Integer dia() {
        return dia;
    }

    public Integer mes() {
        return mes;
    }

    @Override
    public String toString() {
        return this.dia + "/" + this.mes;
    }

    @Override
    public boolean equals(Object otra) {
        if (otra.getClass() == this.getClass()) {
            Fecha otraFecha = (Fecha) otra;
            boolean mismoDia = this.dia == otraFecha.dia;
            boolean mismoMes = this.mes == otraFecha.mes;
            return mismoDia && mismoMes;
        }
        return false;
    }

    public void incrementarDia() {
        int diaSumado = this.dia + 1;
        int diaFinal = diasEnMes(this.mes);
        if (diaSumado > diaFinal) {
            this.mes++;
            this.dia = 1;
            if (this.mes == 12) {
                this.mes = 1;
            }
            return;
        }
        this.dia++;
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
