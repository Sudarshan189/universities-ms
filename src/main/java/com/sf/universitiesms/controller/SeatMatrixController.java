package com.sf.universitiesms.controller;

import com.sf.universitiesms.exception.AppException;
import com.sf.universitiesms.model.SeatMatrix;
import com.sf.universitiesms.service.SeatMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seatmatrix")
public class SeatMatrixController {


    @Autowired
    private SeatMatrixService seatMatrixService;

    @RequestMapping(value = "/{university_id}", method = RequestMethod.GET)
    public ResponseEntity<SeatMatrix> getSeatMatrixByCollegeIdandCourse(@PathVariable("university_id") String universityId,
                                                                        @RequestParam("course") String course) {
        if (universityId!=null) {
            if (course!=null) {
                SeatMatrix seatMatrix = seatMatrixService.getSeatMatrixByIdandCourse(universityId, course);
                return new ResponseEntity<>(seatMatrix, HttpStatus.OK);
            } else {
                throw new AppException("Course is given null");
            }
        } else {
            throw new AppException("UniversityId is given null");
        }
    }

    @RequestMapping(value = "/{university_id}", method = RequestMethod.POST)
    public ResponseEntity<List<SeatMatrix>> addSeatMatrix(@PathVariable("university_id") String universityId,@RequestBody  List<SeatMatrix> seatMatrices) {
        if (universityId!=null) {
                List<SeatMatrix> seatMatrix = seatMatrixService.addSeatMatrix(universityId, seatMatrices);
                return new ResponseEntity<>(seatMatrix, HttpStatus.OK);
        } else {
            throw new AppException("UniversityId is given null");
        }
    }

    @RequestMapping(value = "/{university_id}", method = RequestMethod.PATCH)
    public ResponseEntity<SeatMatrix> updateSeatMatrixByCollegeIdandCourse(@PathVariable("university_id") String universityId,
                                                                           @RequestParam("course") String course) {
        if (universityId!=null) {
            if (course != null) {
                SeatMatrix seatMatrix = seatMatrixService.decreaseSeatAvaiabilityByOne(universityId, course);
                return new ResponseEntity<>(seatMatrix, HttpStatus.OK);
            } else {
                throw new AppException("Course is given null");
            }
        } else {
            throw new AppException("UniversityId is given null");
        }
    }
}
