public class Principal {
    public static void main(String[] args) {
        Archivo comunidad = new Archivo("comunidad.txt");
        comunidad.crearArchivo();
        comunidad.leerArchivo();
        comunidad.abrirArchivo();
        /*comunidad.getListaPropiedades().mostrarPropiedades();
        comunidad.getListaPropietarios().mostrarPropietarios();
        gastos.getListaGastos().mostrarGastos();*/
        Archivo gastos = new Archivo("Gastos.txt");
        gastos.crearArchivo();
        gastos.leerArchivo();
        gastos.abrirArchivo();


    }
}
