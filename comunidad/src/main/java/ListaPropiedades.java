import java.util.ArrayList;
import java.util.List;

public class ListaPropiedades {
    private List<Propiedad> propiedades;

    public ListaPropiedades(){
        propiedades = new ArrayList<>();
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public void agregarPropiedad(Propiedad propiedad){
        propiedades.add(propiedad);
    }

    public void mostrarPropiedades(){
        for (Propiedad propiedad: propiedades){
            propiedad.mostrarDatos();
        }
    }
}
