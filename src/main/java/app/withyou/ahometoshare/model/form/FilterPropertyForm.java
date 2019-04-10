/*
 * File: FilterPropertyForm.java
 * Author: Milos Boskovic
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.model.form;

public class FilterPropertyForm {

    private String city;
    private String price;
    private boolean smoker;
    private boolean pets;
    private boolean parking;
    private boolean laundry;
    private boolean privateBedroom;
    private boolean hydro;
    private boolean internet;

    public boolean isInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isLaundry() {
        return laundry;
    }

    public void setLaundry(boolean laundry) {
        this.laundry = laundry;
    }

    public boolean isPrivateBedroom() {
        return privateBedroom;
    }

    public void setPrivateBedroom(boolean privateBedroom) {
        this.privateBedroom = privateBedroom;
    }

    public boolean isHydro() {
        return hydro;
    }

    public void setHydro(boolean hydro) {
        this.hydro = hydro;
    }
}
