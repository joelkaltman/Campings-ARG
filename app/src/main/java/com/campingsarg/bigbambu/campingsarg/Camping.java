package com.campingsarg.bigbambu.campingsarg;

import android.location.Location;

public class Camping {
    String name;
    private String address;
    private String city;
    private String province;
    private String web;
    private String googleMyBusines;

    public Camping(){

    }

    public Camping(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
}
