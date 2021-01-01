package com.sf.universitiesms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class University {
    @JsonProperty("university_id")
    private String universityId;
    @JsonProperty("university_name")
    private String universityName;
    @JsonProperty("university_address")
    private String universityAddress;

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

}
