public class Principal {
    public static void main(String[] args) {
        Archivo comunidad = new Archivo("comunidad.txt");
        Archivo gastos = new Archivo("gastos.txt");
        Archivo resumen = new Archivo("resumen.txt");
        Archivo propiedades = new Archivo("propiedades.txt");
        Archivo propietarios = new Archivo("propietarios.txt");
        Archivo cuotas = new Archivo("cuotas.txt");

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

        cuotas.crearArchivo();
        escribirCuotas(comunidad, gastos, cuotas);
        cuotas.abrirArchivo();
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

    public static void escribirCuotas(Archivo comunidad, Archivo gastos, Archivo cuotas){
        double gastosE=0, gastosG=0, gastosC=0;
        double total = 0;
        for(Gasto gasto: gastos.getListaGastos().getGastos()){
            switch (gasto.getZonaReparto()) {
                case "E" -> gastosE += gasto.getImporte();
                case "G" -> gastosG += gasto.getImporte();
                case "C" -> gastosC += gasto.getImporte();
            }
        }

        StringBuilder texto = new StringBuilder("#CuotasPorPropiedad");
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        texto.append(String.format("\n %50s %32s", "Porcentajes", "Importes"));
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        texto.append(String.format("\n%s %22s %14s %5s %5s %15s %14s %14s %15s", "CPd", "Nombre Propietario", "%E", "%G", "%C", "E.", "G.", "C.", "Total"));
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        double pE = 0, pG = 0, pC = 0;

        //Cuotas por propiedad
        for(Propiedad propiedad: comunidad.getListaPropiedades().getPropiedades()){
            texto.append(String.format("\n%s %22s", propiedad.getCodigo(), propiedad.getPropietario().getNombre()));

            double gasto = 0;
            double e=0, g=0, c=0;
            int contE=0, contG=0, contC=0;

            for(PorcentajeReparto cuota: propiedad.getPorcentajesReparto()){
                switch (cuota.getZona()) {
                    case "E" -> {
                        texto.append(String.format("%15.0f ", cuota.getPorcentaje()));
                        contE++;
                        pE += cuota.getPorcentaje();
                        e = cuota.getPorcentaje();
                    }
                    case "G" -> {
                        if (contE == 0) {
                            texto.append(String.format("%15s %5.0f ", "0", cuota.getPorcentaje()));
                        } else {
                            texto.append(String.format("%5.0f ", cuota.getPorcentaje()));
                        }
                        contG++;
                        pG += cuota.getPorcentaje();
                        g = cuota.getPorcentaje();
                    }
                    case "C" -> {
                        if (contE == 0) {
                            if (contG == 0) {
                                texto.append(String.format("%15s %5s %5.0f ", "0", "0", cuota.getPorcentaje()));
                            }
                        } else if (contG == 0) {
                            texto.append(String.format("%5s %5.0f ", "0", cuota.getPorcentaje()));
                        } else {
                            texto.append(String.format("%5.0f ", cuota.getPorcentaje()));
                        }
                        contC++;
                        pC += cuota.getPorcentaje();
                        c = cuota.getPorcentaje();
                    }
                }
            }
            if(e==0){
                if(g==0){
                    if(c==0){
                        texto.append(String.format("%15s %5s %5s ", "0", "0", "0"));
                    }
                }else{
                    if(c==0){
                        texto.append(String.format("%5s ", "0"));
                    }
                }
            }else{
                if(g==0){
                    if(c==0){
                        texto.append(String.format("%5s %5s ", "0", "0"));
                    }
                }else{
                    if(c==0){
                        texto.append(String.format("%5s ", "0"));
                    }
                }
            }

            if(e!=0){
                texto.append(String.format("%15.2f", (gastosE*(e/100))));
                gasto+=gastosE*(e/100);
            }else{
                texto.append(String.format("%15s", "0.00"));
            }

            if(g!=0){
                texto.append(String.format("%15.2f", (gastosG*(g/100))));
                gasto+=gastosG*(g/100);
            }else{
                texto.append(String.format("%15s", "0.00"));
            }

            if(c!=0){
                texto.append(String.format("%15.2f", (gastosC*(c/100))));
                gasto+=gastosC*(c/100);
            }else{
                texto.append(String.format("%15s", "0.00"));
            }

            texto.append(String.format("%16.2f", gasto));
            total+=gasto;
        }
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        texto.append(String.format("\n%s %26.0f %5.0f %5.0f %15.0f %14.0f %14.0f %15.2f\n\n\n", (comunidad.getListaPropiedades().getPropiedades().size() + " propiedades"), pE, pG, pC, gastosE, gastosG, gastosC, total));



        //cuotas por propietario
        pE=0;
        pG=0;
        pC=0;
        total=0;
        texto.append("#CuotasPorPropietario");
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        texto.append(String.format("\n %50s %32s", "Porcentajes", "Importes"));
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        texto.append(String.format("\n%s %22s %14s %5s %5s %15s %14s %14s %15s", "Cod", "Nombre Propietario", "%E", "%G", "%C", "E.", "G.", "C.", "Total"));
        texto.append("\n-------------------------------------------------------------------------------------------------------------------");

        for(Propietario propietario: comunidad.getListaPropietarios().getPropietarios()){
            double e=0, g=0, c=0, gasto=0;
            texto.append(String.format("\n%s %23s", propietario.getId(), propietario.getNombre()));
            for(Propiedad propiedad: propietario.getListaPropiedades().getPropiedades()){
                for(PorcentajeReparto cuota: propiedad.getPorcentajesReparto()){
                    switch (cuota.getZona()) {
                        case "E" -> e += cuota.getPorcentaje();
                        case "G" -> g += cuota.getPorcentaje();
                        case "C" -> c += cuota.getPorcentaje();
                    }
                }
            }

            if(e!=0){
                texto.append(String.format("%15.0f ", e));
            }else{
                texto.append(String.format("%15s ", "0"));
            }

            if(g!=0){
                texto.append(String.format("%5.0f ", g));
            }else{
                texto.append(String.format("%5s ", "0"));
            }

            if(c!=0){
                texto.append(String.format("%5.0f ", c));
            }else{
                texto.append(String.format("%5s ", "0"));
            }

            pE+=e;
            pG+=g;
            pC+=c;


            if(e!=0){
                texto.append(String.format("%15.2f", (gastosE*(e/100))));
                gasto+=gastosE*(e/100);
            }else{
                texto.append(String.format("%15s", "0.00"));
            }

            if(g!=0){
                texto.append(String.format("%15.2f", (gastosG*(g/100))));
                gasto+=gastosG*(g/100);
            }else{
                texto.append(String.format("%15s", "0.00"));
            }

            if(c!=0){
                texto.append(String.format("%15.2f", (gastosC*(c/100))));
                gasto+=gastosC*(c/100);
            }else{
                texto.append(String.format("%15s", "0.00"));
            }

            texto.append(String.format("%16.2f", gasto));
            total+=gasto;
        }

        texto.append("\n-------------------------------------------------------------------------------------------------------------------");
        texto.append(String.format("\n%s %25.0f %5.0f %5.0f %15.0f %14.0f %14.0f %15.2f", (comunidad.getListaPropietarios().getPropietarios().size() + " propietarios"), pE, pG, pC, gastosE, gastosG, gastosC, total));
        cuotas.escribirArchivo(texto.toString(), true);
    }

}
