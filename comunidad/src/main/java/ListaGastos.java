import java.util.ArrayList;
import java.util.List;

public class ListaGastos {
    private List<Gasto> gastos;

    public ListaGastos(){
        gastos = new ArrayList<>();
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    public void agregarGasto(Gasto gasto){
        gastos.add(gasto);
    }

    public void mostrarGastos(){
        for(Gasto gasto: gastos){
            gasto.mostrarDatos();
        }
    }
}
