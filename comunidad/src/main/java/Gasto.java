public class Gasto {
    private String idGasto;
    private String descripcion;
    private double importe;
    private String zonaReparto;

    public Gasto(String idGasto, String descripcion, double importe, String zonaReparto) {
        this.idGasto = idGasto;
        this.descripcion = descripcion;
        this.importe = importe;
        this.zonaReparto = zonaReparto;
    }

    public String getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(String idGasto) {
        this.idGasto = idGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getZonaReparto() {
        return zonaReparto;
    }

    public void setZonaReparto(String zonaReparto) {
        this.zonaReparto = zonaReparto;
    }

    public void mostrarDatos(){
        System.out.println("\nId Gasto: "+idGasto);
        System.out.println("Descripción: "+descripcion);
        System.out.println("Importe: "+importe);
        System.out.println("Zona de reparto: "+zonaReparto);
    }
}
