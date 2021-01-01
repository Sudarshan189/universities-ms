package com.sf.universitiesms.service;

import com.sf.universitiesms.model.SeatMatrix;

import java.util.List;

public interface SeatMatrixService {
    SeatMatrix getSeatMatrixByIdandCourse(String universityId, String course);

    SeatMatrix decreaseSeatAvaiabilityByOne(String universityId, String course);


    List<SeatMatrix> addSeatMatrix(String universityId, List<SeatMatrix> seatMatrices);
}
