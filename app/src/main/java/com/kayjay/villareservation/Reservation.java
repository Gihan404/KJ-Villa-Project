package com.kayjay.villareservation;

public class Reservation {

    private String checkInDate;
    private String checkOutDate;
    private int noOfRooms;
    private String boardingType;
    private double price;
    private int noOfAdults;
    private int noOfChildren;
    private String resName ;
    private String resEmail;
    private String resContactNo ;
    private int status;
    private int empId;
    private String  roomType;

    public Reservation() {
    }

    public Reservation(String roomType) {
        this.roomType = roomType;
    }

    public Reservation(String checkInDate, String checkOutDate, int noOfRooms, String boardingType, double price, int noOfAdults, int noOfChildren, String resName, String resEmail, String resContactNo, int status, String roomType) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.noOfRooms = noOfRooms;
        this.boardingType = boardingType;
        this.price = price;
        this.noOfAdults = noOfAdults;
        this.noOfChildren = noOfChildren;
        this.resName = resName;
        this.resEmail = resEmail;
        this.resContactNo = resContactNo;
        this.status = status;
        this.roomType = roomType;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getBoardingType() {
        return boardingType;
    }

    public void setBoardingType(String boardingType) {
        this.boardingType = boardingType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(int noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResEmail() {
        return resEmail;
    }

    public void setResEmail(String resEmail) {
        this.resEmail = resEmail;
    }

    public String getResContactNo() {
        return resContactNo;
    }

    public void setResContactNo(String resContactNo) {
        this.resContactNo = resContactNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
