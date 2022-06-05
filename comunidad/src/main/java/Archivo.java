import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Archivo {
    private String ruta;
    private String nombre;
    private File file;
    private Comunidad comunidad;
    private ListaZonas listaZonas;
    private ListaPropiedades listaPropiedades;
    private ListaPropietarios listaPropietarios;
    private ListaGastos listaGastos;

    public Archivo(String nombre) {
        ruta = "archivos/"+nombre;
        this.nombre = nombre;
        file = new File(ruta);
        listaZonas = new ListaZonas();
        listaPropiedades = new ListaPropiedades();
        listaPropietarios = new ListaPropietarios();
        listaGastos = new ListaGastos();
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public ListaZonas getListaZonas() {
        return listaZonas;
    }

    public void setListaZonas(ListaZonas listaZonas) {
        this.listaZonas = listaZonas;
    }

    public ListaPropiedades getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(ListaPropiedades listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public ListaPropietarios getListaPropietarios() {
        return listaPropietarios;
    }

    public void setListaPropietarios(ListaPropietarios listaPropietarios) {
        this.listaPropietarios = listaPropietarios;
    }

    public ListaGastos getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(ListaGastos listaGastos) {
        this.listaGastos = listaGastos;
    }

    public void crearArchivo(){
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerArchivo(){
        String linea;
        int apartado=0; //#Comunidad, #Zona, #Propiedad, #Propietario
        StringBuffer idComunidad, nombreComunidad, poblacionComunidad;
        StringBuffer idZona, nombreZona, tipoReparto;
        StringBuffer tipoPropiedad, codigoPropiedad, extension, idPropietario, listaPorcentajes, info1, info2;
        List<String> porcentajesReparto;
        StringBuffer nombrePropietario, poblacion, correo;

        StringBuffer idGasto, descripcion, importe;
        try{
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            while((linea = br.readLine())!=null){

                linea = linea.trim();
                idComunidad = new StringBuffer();
                nombreComunidad = new StringBuffer();
                poblacionComunidad = new StringBuffer();

                idZona = new StringBuffer();
                nombreZona = new StringBuffer();
                tipoReparto = new StringBuffer();

                tipoPropiedad = new StringBuffer();
                codigoPropiedad = new StringBuffer();
                extension = new StringBuffer();
                idPropietario = new StringBuffer();
                listaPorcentajes = new StringBuffer();
                porcentajesReparto = new ArrayList<>();

                PorcentajeReparto porcentajeReparto = new PorcentajeReparto();

                info1 = new StringBuffer();
                info2 = new StringBuffer();

                nombrePropietario = new StringBuffer();
                poblacion = new StringBuffer();
                correo = new StringBuffer();

                idGasto = new StringBuffer();
                descripcion = new StringBuffer();
                importe = new StringBuffer();

                if(nombre.equals("comunidad.txt")){
                    if(linea.length()!=0){
                        if(linea.charAt(0) == '.'){
                            //es comment
                            //System.out.println(linea.replace(".", ""));
                        }
                        else if(linea.compareTo("#Comunidad")==0){
                            apartado=1; //apartado 1 Comunidad
                        }
                        else if(linea.compareTo("#Zona")==0){
                            apartado=2; //apartado 2 Zona
                        }
                        else if(linea.compareTo("#Propiedad")==0){
                            apartado=3; //apartado 3 Propiedad
                        }
                        else if(linea.compareTo("#Propietario")==0){
                            apartado=4;//apartado 4 Propietario
                        }
                        else{
                            if(apartado==1){//Estamos leyendo Comunidades
                                asignarValores(linea, idComunidad, nombreComunidad, poblacionComunidad);
                                comunidad = new Comunidad(idComunidad.toString(), nombreComunidad.toString(), poblacionComunidad.toString());
                                //System.out.println("Comunidad:\nId: "+comunidad.getId()+"\nNombre: "+comunidad.getNombre()+"\nPoblación: "+comunidad.getPoblacion());
                            }
                            else if(apartado==2){//Estamos leyendo Zonas
                                asignarValores(linea, idZona, nombreZona, tipoReparto);
                                Zona zona = new Zona(idZona.toString(), nombreZona.toString(), tipoReparto.toString());
                                listaZonas.agregarZona(zona);
                                //System.out.println("Zona:\nId: "+zona.getId()+"\nNombre: "+zona.getNombre()+"\nTipo de reparto: "+zona.getTipoReparto());
                            }
                            else if(apartado==3){//Estamos leyendo Propiedades
                                asignarValores(linea, tipoPropiedad, codigoPropiedad, extension, idPropietario, listaPorcentajes, info1, info2);

                                Propiedad propiedad;
                                switch (tipoPropiedad.toString()) {
                                    case "L" ->//Local comercial
                                            propiedad = new LocalComercial(tipoPropiedad.toString(), codigoPropiedad.toString(), Double.parseDouble(extension.toString()), idPropietario.toString(), info1.toString(), info2.toString());
                                    case "P" ->//Piso
                                            propiedad = new Piso(tipoPropiedad.toString(), codigoPropiedad.toString(), Double.parseDouble(extension.toString()), idPropietario.toString(), info1.toString(), Integer.parseInt(info2.toString()));
                                    case "G" ->//Plaza de garage
                                            propiedad = new PlazaGarage(tipoPropiedad.toString(), codigoPropiedad.toString(), Double.parseDouble(extension.toString()), idPropietario.toString(), info1.toString(), info2.toString());
                                    default -> {//No corresponde
                                        System.out.println("Este tipo de propiedad no está contemplado...");
                                        continue;
                                    }
                                }

                                if(listaPorcentajes.toString().contains(",")){
                                    String[] tokens = listaPorcentajes.toString().split(",");
                                    porcentajesReparto.addAll(Arrays.asList(tokens));
                                }else{
                                    porcentajesReparto.add(listaPorcentajes.toString());
                                }
                                //System.out.println(listaPorcentajes);
                                for(String e: porcentajesReparto){

                                    if(e.contains("-")){
                                        StringTokenizer tokens = new StringTokenizer(e, "-");
                                        porcentajeReparto = new PorcentajeReparto(tokens.nextToken().trim(), Double.parseDouble(tokens.nextToken().trim()));
                                        propiedad.agregarPorcentajeReparto(porcentajeReparto);

                                    }
                                }

                                listaPropiedades.agregarPropiedad(propiedad);
                            }
                            else if(apartado==4){//Estamos leyendo Propietarios
                                asignarValores(linea, idPropietario, nombrePropietario, poblacion, correo);

                                String[] poblacionC = poblacion.toString().replace("(", ",").replace(")", "").split(",");

                                Propietario propietario = new Propietario(idPropietario.toString(), nombrePropietario.toString(), poblacionC[1], poblacionC[0], correo.toString());

                                listaPropietarios.agregarPropietario(propietario);

                                for(Propiedad propiedad: listaPropiedades.getPropiedades()){
                                    if(propiedad.getIdPropietario().equals(propietario.getId())){
                                        propiedad.setPropietario(propietario);
                                    }
                                }
                            }
                        }
                    }
                }

                else if(nombre.equals("Gastos.txt")){
                    if(linea.length()!=0){
                        if(linea.charAt(0) == '.'){
                            //es comment
                        }else if(linea.compareTo("#Gastos 2008")==0){
                            apartado = -1;
                        }else {
                            if(apartado==-1){//Estamos en gastos 2008
                                asignarValores(linea,idGasto, descripcion, importe, idZona);
                                Gasto gasto = new Gasto(idGasto.toString(), descripcion.toString(), Double.parseDouble(importe.toString()), idZona.toString());
                                listaGastos.agregarGasto(gasto);
                            }
                        }
                    }
                }
            }
        }
        catch(IOException e){
            e.getMessage();
        }
    }

    public void escribirArchivo(String texto, boolean sobreescribir){
        try {
            FileWriter fw = new FileWriter(ruta, !sobreescribir);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(texto);
            bw.close();
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    private void asignarValores(String linea, StringBuffer ...arreglo){
        StringTokenizer tokens;
        tokens = new StringTokenizer(linea, ";");

        for (StringBuffer e: arreglo){
            e.append(tokens.nextToken().trim());
        }
    }

    public void abrirArchivo(){
        try {
            if(!Desktop.isDesktopSupported()){
                System.out.println("No soportado");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists()){
                desktop.open(file);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
