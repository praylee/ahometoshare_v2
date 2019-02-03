package app.withyou.ahometoshare.model;

import java.util.Date;

public class Property {
    private Integer propertyId;

    private Integer hostId;

    private String address;

    private String city;

    private String postalCode;

    private String province;

    private String country;

    private Integer familyMembers;

    private Boolean smoker;

    private Boolean pets;

    private Boolean hydro;

    private Boolean water;

    private Boolean gas;

    private Boolean cable;

    private Boolean internet;

    private Boolean parking;

    private Boolean laundry;

    private Boolean familyRoom;

    private Boolean privateBedroom;

    private Boolean sharedBedroom;

    private Boolean privateKitchen;

    private Boolean sharedKitchen;

    private Boolean privateWashroom;

    private Boolean sharedWashroom;

    private Double price;

    private Date hostStartDate;

    private Date hostEndDate;

    private String sharedChore;

    private Integer availability;

    public Property(Integer propertyId, Integer hostId, String address, String city, String postalCode, String province, String country, Integer familyMembers, Boolean smoker, Boolean pets, Boolean hydro, Boolean water, Boolean gas, Boolean cable, Boolean internet, Boolean parking, Boolean laundry, Boolean familyRoom, Boolean privateBedroom, Boolean sharedBedroom, Boolean privateKitchen, Boolean sharedKitchen, Boolean privateWashroom, Boolean sharedWashroom, Double price, Date hostStartDate, Date hostEndDate, String sharedChore, Integer availability) {
        this.propertyId = propertyId;
        this.hostId = hostId;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
        this.country = country;
        this.familyMembers = familyMembers;
        this.smoker = smoker;
        this.pets = pets;
        this.hydro = hydro;
        this.water = water;
        this.gas = gas;
        this.cable = cable;
        this.internet = internet;
        this.parking = parking;
        this.laundry = laundry;
        this.familyRoom = familyRoom;
        this.privateBedroom = privateBedroom;
        this.sharedBedroom = sharedBedroom;
        this.privateKitchen = privateKitchen;
        this.sharedKitchen = sharedKitchen;
        this.privateWashroom = privateWashroom;
        this.sharedWashroom = sharedWashroom;
        this.price = price;
        this.hostStartDate = hostStartDate;
        this.hostEndDate = hostEndDate;
        this.sharedChore = sharedChore;
        this.availability = availability;
    }

    public Property() {
        super();
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public Integer getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Integer familyMembers) {
        this.familyMembers = familyMembers;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public Boolean getPets() {
        return pets;
    }

    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public Boolean getHydro() {
        return hydro;
    }

    public void setHydro(Boolean hydro) {
        this.hydro = hydro;
    }

    public Boolean getWater() {
        return water;
    }

    public void setWater(Boolean water) {
        this.water = water;
    }

    public Boolean getGas() {
        return gas;
    }

    public void setGas(Boolean gas) {
        this.gas = gas;
    }

    public Boolean getCable() {
        return cable;
    }

    public void setCable(Boolean cable) {
        this.cable = cable;
    }

    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getLaundry() {
        return laundry;
    }

    public void setLaundry(Boolean laundry) {
        this.laundry = laundry;
    }

    public Boolean getFamilyRoom() {
        return familyRoom;
    }

    public void setFamilyRoom(Boolean familyRoom) {
        this.familyRoom = familyRoom;
    }

    public Boolean getPrivateBedroom() {
        return privateBedroom;
    }

    public void setPrivateBedroom(Boolean privateBedroom) {
        this.privateBedroom = privateBedroom;
    }

    public Boolean getSharedBedroom() {
        return sharedBedroom;
    }

    public void setSharedBedroom(Boolean sharedBedroom) {
        this.sharedBedroom = sharedBedroom;
    }

    public Boolean getPrivateKitchen() {
        return privateKitchen;
    }

    public void setPrivateKitchen(Boolean privateKitchen) {
        this.privateKitchen = privateKitchen;
    }

    public Boolean getSharedKitchen() {
        return sharedKitchen;
    }

    public void setSharedKitchen(Boolean sharedKitchen) {
        this.sharedKitchen = sharedKitchen;
    }

    public Boolean getPrivateWashroom() {
        return privateWashroom;
    }

    public void setPrivateWashroom(Boolean privateWashroom) {
        this.privateWashroom = privateWashroom;
    }

    public Boolean getSharedWashroom() {
        return sharedWashroom;
    }

    public void setSharedWashroom(Boolean sharedWashroom) {
        this.sharedWashroom = sharedWashroom;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getHostStartDate() {
        return hostStartDate;
    }

    public void setHostStartDate(Date hostStartDate) {
        this.hostStartDate = hostStartDate;
    }

    public Date getHostEndDate() {
        return hostEndDate;
    }

    public void setHostEndDate(Date hostEndDate) {
        this.hostEndDate = hostEndDate;
    }

    public String getSharedChore() {
        return sharedChore;
    }

    public void setSharedChore(String sharedChore) {
        this.sharedChore = sharedChore == null ? null : sharedChore.trim();
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }
}