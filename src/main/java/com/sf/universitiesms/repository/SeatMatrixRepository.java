package com.sf.universitiesms.repository;

import com.sf.universitiesms.dao.SeatMatrixDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatMatrixRepository extends JpaRepository<SeatMatrixDao, String> {


    @Query(value = "select * from seatmatrix_table where course=?1 and university_id=?2", nativeQuery = true)
    public SeatMatrixDao findSeatMatrixByIDandCourse(String course, String universityId);

    @Query(value = "update seatmatrix_table set available_number_of_seats=?1 where course=?2 and university_id=?3", nativeQuery = true)
    public SeatMatrixDao decreaseAvailabilityCountBy1(Integer availableSeatCount, String course, String universityId);
}

