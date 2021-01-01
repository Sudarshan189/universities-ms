package com.sf.universitiesms.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "seatmatrix_table")
public class SeatMatrixDao {
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String seatId;
    @Column(name = "course")
    private String course;
    @Column(name = "total_number_of_seats")
    private Integer totalSeatNumber;
    @Column(name = "available_number_of_seats")
    private Integer availableSeatNumber;
    @ManyToOne
    @JoinColumn(name = "university_id", referencedColumnName = "university_id")
    private UniversityDao universityDao;


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
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

    public UniversityDao getUniversityDao() {
        return universityDao;
    }

    public void setUniversityDao(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public SeatMatrixDao decreaseAvailableCount() {
        this.availableSeatNumber--;
        return this;
    }

    @Override
    public String toString() {
        return "SeatMatrixDao{" +
                "seatId='" + seatId + '\'' +
                ", course='" + course + '\'' +
                ", totalSeatNumber=" + totalSeatNumber +
                ", availableSeatNumber=" + availableSeatNumber +
                ", universityDao=" + universityDao +
                '}';
    }
}
