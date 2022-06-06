public class Propietario {
    private String id;
    private String nombre;
    private String provincia;
    private String municipio;
    private String correo;
    private ListaPropiedades listaPropiedades;

    public Propietario(String id, String nombre, String provincia, String municipio, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.municipio = municipio;
        this.correo = correo;
        listaPropiedades = new ListaPropiedades();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ListaPropiedades getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(ListaPropiedades listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public void mostrarDatos(){
        System.out.println("\nId Propietario: "+id);
        System.out.println("Nombre: "+nombre);
        System.out.println("Provincia: "+provincia);
        System.out.println("Municipio: "+municipio);
        System.out.println("Correo: "+correo);
        listaPropiedades.mostrarPropiedades();
    }
}
