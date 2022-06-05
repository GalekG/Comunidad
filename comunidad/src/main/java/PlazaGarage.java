public class PlazaGarage extends Propiedad{//plaza de garage
    private String estado;//A: Abierta, C:Cerrada
    private String trastero;//S: Si, N:No

    public PlazaGarage(String tipo, String codigo, double extension, String idPropietario, String estado, String trastero) {
        super(tipo, codigo, extension, idPropietario);
        this.estado = estado;
        this.trastero = trastero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTrastero() {
        return trastero;
    }

    public void setTrastero(String trastero) {
        this.trastero = trastero;
    }

    @Override
    public void mostrarDatos() {
        mostrarPropiedad();
        System.out.println("Estado: "+estado);
        System.out.println("Trastero: "+trastero);
    }
}
