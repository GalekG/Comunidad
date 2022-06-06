public class PlazaGarage extends Propiedad{//plaza de garage
    private String estado;//A: Abierta, C:Cerrada
    private String trastero;//S: Si, N:No

    public PlazaGarage(String tipo, String codigo, double extension, String idPropietario, String estado, String trastero) {
        super(tipo, codigo, extension, idPropietario);

        switch (estado){
            case "A" -> this.estado = "Abierta";
            case "C" -> this.estado = "Cerrada";
            default -> this.estado = estado;
        }

        switch (trastero){
            case "S" -> this.trastero = "Con Trastero";
            case "N" -> this.trastero = "Sin Trastero";
            default -> this.trastero = trastero;
        }

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
