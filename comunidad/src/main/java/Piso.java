public class Piso extends Propiedad{
    private String tipoVivienda;//VH: Vivienda habitada, VNH: Vivienda no habitada
    private int dormitorios;

    public Piso(String tipo, String codigo, double extension, String idPropietario, String tipoVivienda, int dormitorios) {
        super(tipo, codigo, extension, idPropietario);
        this.tipoVivienda = tipoVivienda;
        this.dormitorios = dormitorios;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public int getDormitorios() {
        return dormitorios;
    }

    public void setDormitorios(int dormitorios) {
        this.dormitorios = dormitorios;
    }

    @Override
    public void mostrarDatos() {
        mostrarPropiedad();
        System.out.println("Tipo de vivienda: "+tipoVivienda);
        System.out.println("Cantidad de dormitorios: "+dormitorios);
    }
}
