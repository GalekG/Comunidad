import java.util.ArrayList;
import java.util.List;

public class ListaZonas {
    private List<Zona> zonas;

    public ListaZonas() {
        zonas = new ArrayList<>();
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    public void agregarZona(Zona zona){
        zonas.add(zona);
    }
}
