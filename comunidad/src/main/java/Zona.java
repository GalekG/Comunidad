public class Zona {
    private String id;
    private String nombre;
    private String tipoReparto;//Proporcional: P, Igualitario: I

    public Zona(String id, String nombre, String tipoReparto) {
        this.id = id;
        this.nombre = nombre;
        this.tipoReparto = tipoReparto;

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

    public String getTipoReparto() {
        return tipoReparto;
    }

    public void setTipoReparto(String tipoReparto) {
        this.tipoReparto = tipoReparto;
    }
}
