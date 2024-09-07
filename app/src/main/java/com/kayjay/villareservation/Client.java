package com.kayjay.villareservation;

import java.util.Date;

public class Client {

    private String firstName;
    private String lastName;
    private String nic;
    private String email;
    private String contactNo;
    private String streetNo;
    private String streetName;
    private String city;
    private String postalCode;
    private String country;
    private String username;
    private String password;
    private String confirmPassword;
    private String type;
    private int createUpdateUID;
    private Date createUpdateDate;
    private int clientId;

    private static String idNo;
    private static String userRole;

    public Client() {
    }



    public Client(String firstName, String lastName, String nic, String email, String contactNo, String streetNo, String streetName, String city, String postalCode, String country, String username, String password, String confirmPassword, String type, int createUpdateUID, Date createUpdateDate, int clientId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.email = email;
        this.contactNo = contactNo;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.type = type;
        this.createUpdateUID = createUpdateUID;
        this.createUpdateDate = createUpdateDate;
        this.clientId = clientId;
    }

    public Client(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Client(String firstName, String lastName, String nic, String email, String contactNo, String streetNo, String streetName, String city, String postalCode, String country, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.email = email;
        this.contactNo = contactNo;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCreateUpdateUID() {
        return createUpdateUID;
    }

    public void setCreateUpdateUID(int createUpdateUID) {
        this.createUpdateUID = createUpdateUID;
    }

    public Date getCreateUpdateDate() {
        return createUpdateDate;
    }

    public void setCreateUpdateDate(Date createUpdateDate) {
        this.createUpdateDate = createUpdateDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public static String getIdNo() {
        return idNo;
    }

    public static void setIdNo(String idNo) {
        Client.idNo = idNo;
    }


    public static String getUserRole() {
        return userRole;
    }

    public static void setUserRole(String userRole) {
        Client.userRole = userRole;
    }


}
