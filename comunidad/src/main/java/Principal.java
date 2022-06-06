public class Principal {
    public static void main(String[] args) {
        Archivo comunidad = new Archivo("comunidad.txt");
        Archivo gastos = new Archivo("gastos.txt");
        Archivo resumen = new Archivo("resumen.txt");
        Archivo propiedades = new Archivo("propiedades.txt");
        Archivo propietarios = new Archivo("propietarios.txt");

        comunidad.crearArchivo();
        comunidad.leerArchivo();
        comunidad.abrirArchivo();

        gastos.crearArchivo();
        gastos.leerArchivo();
        gastos.abrirArchivo();

        resumen.crearArchivo();
        escribirResumen(comunidad, gastos, resumen);
        resumen.abrirArchivo();

        propiedades.crearArchivo();
        escribirPropiedades(comunidad, propiedades);
        propiedades.abrirArchivo();

        propietarios.crearArchivo();
        esrcibirPropietarios(comunidad, propietarios);
        propietarios.abrirArchivo();
    }

    public static void escribirResumen(Archivo comunidad, Archivo gastos, Archivo resumen){
        int zonas = comunidad.getListaZonas().getZonas().size();
        int propiedades = comunidad.getListaPropiedades().getPropiedades().size();
        int propietarios = comunidad.getListaPropietarios().getPropietarios().size();
        int numGastos = gastos.getListaGastos().getGastos().size();

        String texto = "Comunidad: "+comunidad.getComunidad().getId()+" - "+comunidad.getComunidad().getNombre();
        texto += "\nNumero de Zonas: "+zonas;
        texto += "\nNumero de Propiedades: "+propiedades;
        texto += "\nNumero de Propietarios: "+propietarios;
        texto += "\nNumero de Gastos: "+numGastos;

        resumen.escribirArchivo(texto, true);
    }

    public static void escribirPropiedades(Archivo comunidad, Archivo propiedades){
        StringBuilder texto = new StringBuilder(String.format("%s %4s %4s %20s %14s %43s", "Cod.", "m2", "C.", "Nombre Propietario", "Cuotas", "Informacion Adicional") + "\n----------------------------------------------------------------------------------------------");

        for(Propiedad propiedad: comunidad.getListaPropiedades().getPropiedades()){
            texto.append(String.format("\n%s %5.0f %4s %20s", propiedad.getCodigo(), propiedad.getExtension(), propiedad.getPropietario().getId(), propiedad.getPropietario().getNombre()));

            int cuotasT = propiedad.getPorcentajesReparto().size(), cont=0;
            StringBuilder cuotas= new StringBuilder();
            for(PorcentajeReparto cuota: propiedad.getPorcentajesReparto()){
                cuotas.append(String.format("%.0f%s%s", cuota.getPorcentaje(), "%", cuota.getZona()));
                if(cont<cuotasT-1){
                    cuotas.append(", ");
                }
                cont++;
            }

            texto.append(String.format("%15s", cuotas));

            if(propiedad instanceof Piso){
                texto.append(String.format("%44s", (((Piso) propiedad).getTipoVivienda()+" - "+((Piso) propiedad).getDormitorios()+" Dormitorio(s)")));
            }else if(propiedad instanceof LocalComercial){
                texto.append(String.format("%44s", (((LocalComercial) propiedad).getNombreComercial()+" - "+((LocalComercial) propiedad).getActividad())));
            }else if(propiedad instanceof PlazaGarage){
                texto.append(String.format("%44s", (((PlazaGarage) propiedad).getEstado()+" - "+((PlazaGarage) propiedad).getTrastero())));
            }
        }

        texto.append("\n----------------------------------------------------------------------------------------------\n");
        texto.append(String.format("%3s", (comunidad.getListaPropiedades().getPropiedades().size()+" Propiedades en Total")));

        propiedades.escribirArchivo(texto.toString(), true);
    }

    public static void esrcibirPropietarios(Archivo comunidad, Archivo propietarios){
        StringBuilder texto = new StringBuilder();
        texto.append(String.format("%s %22s %22s %14s", "C.", "Nombre Propietario", "Email", "Propiedades"));
        texto.append("\n---------------------------------------------------------------");
        for(Propietario propietario: comunidad.getListaPropietarios().getPropietarios()){
            texto.append(String.format("\n%s %22s %22s", propietario.getId(), propietario.getNombre(), propietario.getCorreo()));

            StringBuilder propiedades = new StringBuilder();
            int cont=0, propiedadesT = propietario.getListaPropiedades().getPropiedades().size();
            for(Propiedad propiedad: propietario.getListaPropiedades().getPropiedades()){
                propiedades.append(String.format("%s", propiedad.getCodigo()));
                if(cont<propiedadesT-1){
                    propiedades.append(", ");
                }
                cont++;
            }
            texto.append(String.format("%15s", propiedades));
        }
        texto.append("\n---------------------------------------------------------------\n");
        texto.append(comunidad.getListaPropietarios().getPropietarios().size()).append(" Propietarios en Total");

        propietarios.escribirArchivo(texto.toString(), true);
    }

}
