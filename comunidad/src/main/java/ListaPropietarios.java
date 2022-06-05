import java.util.ArrayList;
import java.util.List;

public class ListaPropietarios {
    private List<Propietario> propietarios;

    public ListaPropietarios(){
        propietarios = new ArrayList<>();
    }

    public List<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    public void agregarPropietario(Propietario propietario){
        propietarios.add(propietario);
    }

    public void mostrarPropietarios(){
        for (Propietario propietario: propietarios){
            propietario.mostrarDatos();
        }
    }
}
