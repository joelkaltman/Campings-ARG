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
}
