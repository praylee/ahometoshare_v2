package app.withyou.ahometoshare.model;

import java.util.Date;

public class Renter {
    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private Integer gender;

    private String dateOfBirth;

    private Boolean student;

    private Boolean employed;

    private Boolean smoker;

    private Date rentStartDate;

    private Date rentEndDate;

    private Integer availability;

    private Double lowPrice;

    private Double highPrice;

    private String referralSource;

    private Boolean criminalityCheck;

    private byte[] password;

    public Renter(Integer id, String email, String firstName, String lastName, String phone, Integer gender, String dateOfBirth, Boolean student, Boolean employed, Boolean smoker, Date rentStartDate, Date rentEndDate, Integer availability, Double lowPrice, Double highPrice, String referralSource, Boolean criminalityCheck, byte[] password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.student = student;
        this.employed = employed;
        this.smoker = smoker;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
        this.availability = availability;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.referralSource = referralSource;
        this.criminalityCheck = criminalityCheck;
        this.password = password;
    }

    public Renter() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth == null ? null : dateOfBirth.trim();
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    public Boolean getEmployed() {
        return employed;
    }

    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public Date getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(Date rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public Date getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(Date rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource == null ? null : referralSource.trim();
    }

    public Boolean getCriminalityCheck() {
        return criminalityCheck;
    }

    public void setCriminalityCheck(Boolean criminalityCheck) {
        this.criminalityCheck = criminalityCheck;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}