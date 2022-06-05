import java.util.ArrayList;
import java.util.List;

public abstract class Propiedad {
    private String tipo;//L: Local comercial, P: Piso, G: Plaza de garage
    private String codigo;
    private double extension;
    private String idPropietario;
    private Propietario propietario;
    private List<PorcentajeReparto> porcentajesReparto;

    public Propiedad(String tipo, String codigo, double extension, String idPropietario) {
        this.tipo = tipo;
        this.codigo = codigo;
        this.extension = extension;
        this.idPropietario = idPropietario;
        porcentajesReparto = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getExtension() {
        return extension;
    }

    public void setExtension(double extension) {
        this.extension = extension;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public List<PorcentajeReparto> getPorcentajesReparto() {
        return porcentajesReparto;
    }

    public void setPorcentajesReparto(List<PorcentajeReparto> porcentajesReparto) {
        this.porcentajesReparto = porcentajesReparto;
    }

    public void agregarPorcentajeReparto(PorcentajeReparto porcentajeReparto){
        porcentajesReparto.add(porcentajeReparto);
    }

    public void mostrarPropiedad(){
        System.out.print("\nTipo propiedad: "+tipo+" (");
        if(tipo.equals("P")){
            System.out.println("Piso)");
        }else if(tipo.equals("L")){
            System.out.println("Local Comercial)");
        }else{
            System.out.println("Plaza de garage)");
        }
        System.out.println("Codigo de la propiedad: "+codigo);
        System.out.println("Extension de la propiedad: "+extension+"m2");
        System.out.println("Propietario: "+idPropietario);
        System.out.println("Porcentajes de reparto: ");
        for(PorcentajeReparto porcentaje: porcentajesReparto){
            System.out.println("Zona: "+porcentaje.getZona()+" - Porcentaje: "+porcentaje.getPorcentaje()+"%");
        }
    }

    public abstract void mostrarDatos();
}