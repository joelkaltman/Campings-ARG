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
