package com.kayjay.villareservation;

public class RoomType {

    private String roomName;
    private String roomDescription;
    private double roomPrice;
    private int numAdults;
    private int numChildren;
    private boolean hasWiFi;
    private boolean hasAC;
    private boolean hasTV;
    private boolean hasMinibar;

    public RoomType(String roomName, String roomDescription, double roomPrice, int numAdults, int numChildren, boolean hasWiFi, boolean hasAC, boolean hasTV, boolean hasMinibar) {
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.roomPrice = roomPrice;
        this.numAdults = numAdults;
        this.numChildren = numChildren;
        this.hasWiFi = hasWiFi;
        this.hasAC = hasAC;
        this.hasTV = hasTV;
        this.hasMinibar = hasMinibar;
    }

    public RoomType(String selectedRoomType) {
    }

    // Getters and Setters

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getNumAdults() {
        return numAdults;
    }

    public void setNumAdults(int numAdults) {
        this.numAdults = numAdults;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public boolean isHasWiFi() {
        return hasWiFi;
    }

    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }

    public boolean isHasAC() {
        return hasAC;
    }

    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasMinibar() {
        return hasMinibar;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }
}
