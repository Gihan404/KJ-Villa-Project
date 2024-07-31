package com.kayjay.villareservation;

public class Employee {

    private int empId;
    private String staffId;
    private String firstName;
    private String lastName;
    private String designation;
    private String email;
    private String contactNo;
    private String username;
    private String userRole;
    private String password;
    private int empStatus;

    public Employee() {
    }

    public Employee(String staffId) {
        this.staffId = staffId;
    }

    public Employee(String staffId, int empStatus) {
        this.staffId = staffId;
        this.empStatus = empStatus;
    }

    public Employee(String staffId, String firstName, String lastName, String designation, String email, String contactNo, String username, String userRole, String password, int empStatus) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.email = email;
        this.contactNo = contactNo;
        this.username = username;
        this.userRole = userRole;
        this.password = password;
        this.empStatus = empStatus;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }
}
