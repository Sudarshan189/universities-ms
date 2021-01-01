package com.sf.universitiesms.utils;

import com.sf.universitiesms.dao.SeatMatrixDao;
import com.sf.universitiesms.dao.UniversityDao;
import com.sf.universitiesms.model.SeatMatrix;
import com.sf.universitiesms.model.University;

import java.util.stream.Collectors;

public class UniversityMapper {

    public static University mapDaotoUniversity(UniversityDao universityDao) {
        University university = new University();
        university.setUniversityId(universityDao.getUniversityId());
        university.setUniversityName(universityDao.getUniversityName());
        university.setUniversityAddress(universityDao.getUniversityAddress());
        return university;
    }

    public static SeatMatrix mapDaotoSeatMatrix(SeatMatrixDao seatMatrixDao) {
        SeatMatrix seatMatrix = new SeatMatrix();
        seatMatrix.setSeatId(seatMatrixDao.getSeatId());
        seatMatrix.setCourse(seatMatrixDao.getCourse());
        seatMatrix.setAvailableSeatNumber(seatMatrixDao.getAvailableSeatNumber());
        seatMatrix.setTotalSeatNumber(seatMatrixDao.getTotalSeatNumber());
        return seatMatrix;
    }

    public static  SeatMatrixDao mapSeatMatrixtoDao(SeatMatrix seatMatrix) {
        SeatMatrixDao seatMatrixDao = new SeatMatrixDao();
        seatMatrixDao.setSeatId(seatMatrix.getSeatId());
        seatMatrixDao.setCourse(seatMatrix.getCourse());
        seatMatrixDao.setAvailableSeatNumber(seatMatrix.getAvailableSeatNumber());
        seatMatrixDao.setTotalSeatNumber(seatMatrix.getTotalSeatNumber());
        return seatMatrixDao;
    }


    public static UniversityDao mapUniversityToDao(University university) {
        UniversityDao universityDao = new UniversityDao();
        universityDao.setUniversityId(university.getUniversityId());
        universityDao.setUniversityName(university.getUniversityName());
        universityDao.setUniversityAddress(university.getUniversityAddress());
        return universityDao;
    }
}
