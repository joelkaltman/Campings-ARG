package com.campingsarg.bigbambu.campingsarg;

import java.text.Normalizer;
import java.util.ArrayList;

public class CampingsManager {
    public static ArrayList<Camping> campings = new ArrayList<>();

    public static Camping encontrarPorId(int id){
        for(int i = 0; i < campings.size(); i++){
            if(campings.get(i).getId() == id){
                return campings.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Camping> encontrarPorTexto(String texto){
        ArrayList<Camping> campingsTexto = new ArrayList<>();
        if(texto.isEmpty()){
            return campingsTexto;
        }

        String textoLimpio = limpiarAcentos(texto.toLowerCase());
        String[] palabras = textoLimpio.split("\\s+");
        for(int i = 0; i < campings.size(); i++) {
            Camping actual = campings.get(i);
            String lowcaseNombre = limpiarAcentos(actual.getNombre().toLowerCase());
            String lowcaseUbicacion = limpiarAcentos(actual.getUbicacion().toLowerCase());

            boolean todasLasPalabras = true;
            for(int j = 0; j < palabras.length; j++) {
                if (!lowcaseNombre.contains(palabras[j]) && !lowcaseUbicacion.contains(palabras[j])) {
                    todasLasPalabras = false;
                }
            }
            if(todasLasPalabras) {
                campingsTexto.add(actual);
            }
        }
        return campingsTexto;
    }

    public static ArrayList<Camping> encontrarPorFiltros(String ciudad,
                                                         String provincia,
                                                         Integer distancia,
                                                         boolean mascotasSi,
                                                         boolean mascotasNo,
                                                         ArrayList<String> alojamientos,
                                                         ArrayList<String> servicios,
                                                         ArrayList<String> actividades,
                                                         ArrayList<String> naturaleza){
        ArrayList<Camping> campingsFiltro = new ArrayList<>(campings);

        for(int i = 0; i < campingsFiltro.size(); i++) {
            Camping actual = campingsFiltro.get(i);
            boolean borrar = false;

            if(ciudad != null && !actual.getCiudad().equals(ciudad)){
                borrar = true;
            }
            if(provincia != null && !actual.getProvincia().equals(provincia)){
                borrar = true;
            }
            if(actual.isMascotas() == null) {
                if (mascotasSi || mascotasNo) {
                    borrar = true;
                }
            } else {
                if ((mascotasSi && !actual.isMascotas()) || (mascotasNo && actual.isMascotas())){
                    borrar = true;
                }
            }

            ArrayList<String> alojamientosActual = actual.getAlojamientos();
            if(alojamientosActual == null && alojamientos.size() > 0){
                borrar = true;
            } else {
                for (int j = 0; j < alojamientos.size(); j++) {
                    if (!alojamientosActual.contains(alojamientos.get(j))) {
                        borrar = true;
                        break;
                    }
                }
            }
            ArrayList<String> serviciosActual = actual.getServicios();
            if(serviciosActual == null && servicios.size() > 0){
                borrar = true;
            } else {
                for (int j = 0; j < servicios.size(); j++) {
                    if (!serviciosActual.contains(servicios.get(j))) {
                        borrar = true;
                        break;
                    }
                }
            }
            ArrayList<String> actividadesActual = actual.getActividades();
            if(actividadesActual == null && actividades.size() > 0){
                borrar = true;
            } else {
                for (int j = 0; j < actividades.size(); j++) {
                    if (!actividadesActual.contains(actividades.get(j))) {
                        borrar = true;
                        break;
                    }
                }
            }
            ArrayList<String> naturalezaActual = actual.getNaturaleza();
            if(naturalezaActual == null && naturaleza.size() > 0){
                borrar = true;
            } else {
                for (int j = 0; j < naturaleza.size(); j++) {
                    if (!naturalezaActual.contains(naturaleza.get(j))) {
                        borrar = true;
                        break;
                    }
                }
            }

            if(borrar){
                campingsFiltro.remove(actual);
                i--;
            }
        }

        return  campingsFiltro;
    }


    static String limpiarAcentos(String cadena) {
        String limpio =null;
        if (cadena !=null) {
            String valor = cadena;
            valor = valor.toUpperCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }

    public enum TipoLista{
        ACTIVIDADES,
        SERVICIOS,
        NATURALEZA,
        ALOJAMIENTOS,
        CIUDADES,
        PROVINCIAS,
        TIPOS
    };

    public static ArrayList<String> obtenerTodos(TipoLista tipo){
        ArrayList<String> actividades = new ArrayList<>();

        for(int i = 0; i < campings.size(); i++) {
            ArrayList<String> listaActual = new ArrayList<>();
            switch (tipo) {
                case CIUDADES:
                    listaActual.add(campings.get(i).getCiudad());
                    break;
                case PROVINCIAS:
                    listaActual.add(campings.get(i).getProvincia());
                    break;
                case TIPOS:
                    listaActual.add(campings.get(i).getTipo());
                    break;
                case ACTIVIDADES:
                    listaActual = campings.get(i).getActividades();
                    break;
                case SERVICIOS:
                    listaActual = campings.get(i).getServicios();
                    break;
                case NATURALEZA:
                    listaActual = campings.get(i).getNaturaleza();
                    break;
                case ALOJAMIENTOS:
                    listaActual = campings.get(i).getAlojamientos();
                    break;
            }

            if(listaActual != null) {
                for (int j = 0; j < listaActual.size(); j++) {
                    String actual = listaActual.get(j);
                    if (!actividades.contains(actual)) {
                        actividades.add(actual);
                    }
                }
            }
        }
        return actividades;
    }


}
