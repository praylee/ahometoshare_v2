/*
 * File: PropertyPictureBase64.java
 * Author: Milos Boskovic
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.model;

public class PropertyPictureBase64 {
    private Integer pictureId;

    private Integer propertyId;

    private String pictureBase64;

    public PropertyPictureBase64(Integer pictureId, Integer propertyId, String pictureBase64) {
        this.pictureId = pictureId;
        this.propertyId = propertyId;
        this.pictureBase64 = pictureBase64;
    }

    public PropertyPictureBase64() {
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }
}
