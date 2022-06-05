public class LocalComercial extends Propiedad{
    private String nombreComercial;
    private String actividad;

    public LocalComercial(String tipo, String codigo, double extension, String idPropietario, String nombreComercial, String actividad) {
        super(tipo, codigo, extension, idPropietario);
        this.nombreComercial = nombreComercial;
        this.actividad = actividad;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    @Override
    public void mostrarDatos() {
        mostrarPropiedad();
        System.out.println("Nombre comercial: "+nombreComercial);
        System.out.println("Actividad comercial: "+actividad);
    }
}
