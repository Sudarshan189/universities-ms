package com.sf.universitiesms.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sf.universitiesms.model.SeatMatrix;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "university_table")
public class UniversityDao {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "university_id", nullable = false, unique = true)
    private String universityId;
    @Column(name = "university_name")
    private String universityName;
    @Column(name = "university_address")
    private String universityAddress;


    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    @Override
    public String toString() {
        return "UniversityDao{" +
                "universityId='" + universityId + '\'' +
                ", universityName='" + universityName + '\'' +
                ", universityAddress='" + universityAddress + '\'' +
                '}';
    }
}
