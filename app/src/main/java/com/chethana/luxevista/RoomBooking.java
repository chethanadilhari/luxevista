package com.chethana.luxevista;

public class RoomBooking {
    private String roomId;
    private String roomName;
    private String roomImageUrl;
    private Long roomPrice;
    private int roomCount;
    private int noOfGuests;
    private String checkIn;
    private String checkOut;
    private Long totalAmount;

    private int maxPaxValue;

    public RoomBooking() {
    }

    public RoomBooking(String roomId, String roomName, String roomImageUrl, Long roomPrice, int roomCount, int noOfGuests, String checkIn, String checkOut, Long totalAmount) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomImageUrl = roomImageUrl;
        this.roomPrice = roomPrice;
        this.roomCount = roomCount;
        this.noOfGuests = noOfGuests;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomImageUrl() {
        return roomImageUrl;
    }

    public Long getRoomPrice() {
        return roomPrice;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public int getNoOfGuests() {
        return noOfGuests;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }


}
