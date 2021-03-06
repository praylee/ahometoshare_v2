/*
 * File: PropertyPicture.java
 * Author: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.model;

public class PropertyPicture {
    private Integer pictureId;

    private Integer propertyId;

    private byte[] picture;

    public PropertyPicture(Integer pictureId, Integer propertyId, byte[] picture) {
        this.pictureId = pictureId;
        this.propertyId = propertyId;
        this.picture = picture;
    }

    public PropertyPicture() {
        super();
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}