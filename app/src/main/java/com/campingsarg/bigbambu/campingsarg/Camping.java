package com.campingsarg.bigbambu.campingsarg;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Camping {
    private String nombre;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String web;
    private String googleMyBusines;
    private double latitud;
    private double longitud;
    private String telefono;
    private String tipo;
    private String abierto;
    private int parcelas;
    private boolean mascotas;
    private String emergencias;
    private ArrayList<String> servicios;
    private ArrayList<String> naturaleza;
    private ArrayList<String> actividades;
    private ArrayList<String> alojamientos;
    private ArrayList<String> formasDePago;

    public Camping(){
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getGoogleMyBusines() {
        return googleMyBusines;
    }

    public void setGoogleMyBusines(String googleMyBusines) {
        this.googleMyBusines = googleMyBusines;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public LatLng getLatLng(){
        return new LatLng(latitud, longitud);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAbierto() {
        return abierto;
    }

    public void setAbierto(String abierto) {
        this.abierto = abierto;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isMascotas() {
        return mascotas;
    }

    public void setMascotas(boolean mascotas) {
        this.mascotas = mascotas;
    }

    public String getEmergencias() {
        return emergencias;
    }

    public void setEmergencias(String emergencias) {
        this.emergencias = emergencias;
    }

    public ArrayList<String> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<String> actividades) {
        this.actividades = actividades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public ArrayList<String> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(ArrayList<String> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public ArrayList<String> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<String> servicios) {
        this.servicios = servicios;
    }

    public ArrayList<String> getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(ArrayList<String> naturaleza) {
        this.naturaleza = naturaleza;
    }

    public ArrayList<String> getFormasDePago() {
        return formasDePago;
    }

    public void setFormasDePago(ArrayList<String> formasDePago) {
        this.formasDePago = formasDePago;
    }
}
