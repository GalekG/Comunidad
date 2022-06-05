public class PorcentajeReparto {
    private String zona;
    private double porcentaje;

    public PorcentajeReparto() {}

    public PorcentajeReparto(String zona, double porcentaje) {
        this.zona = zona;
        this.porcentaje = porcentaje;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
