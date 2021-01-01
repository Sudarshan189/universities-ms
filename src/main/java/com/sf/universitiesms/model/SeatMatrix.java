package com.sf.universitiesms.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SeatMatrix {
    @JsonProperty("seat_id")
    private String seatId;
    @JsonProperty("course")
    private String course;
    @JsonProperty("total_seat_count")
    private Integer totalSeatNumber;
    @JsonProperty("available_seat_count")
    private Integer availableSeatNumber;

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public Integer getTotalSeatNumber() {
        return totalSeatNumber;
    }

    public void setTotalSeatNumber(Integer totalSeatNumber) {
        this.totalSeatNumber = totalSeatNumber;
    }

    public Integer getAvailableSeatNumber() {
        return availableSeatNumber;
    }

    public void setAvailableSeatNumber(Integer availableSeatNumber) {
        this.availableSeatNumber = availableSeatNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "SeatMatrix{" +
                "seatId='" + seatId + '\'' +
                ", course='" + course + '\'' +
                ", totalSeatNumber=" + totalSeatNumber +
                ", availableSeatNumber=" + availableSeatNumber +
                '}';
    }
}
