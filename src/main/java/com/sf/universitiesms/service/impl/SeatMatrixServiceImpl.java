package com.sf.universitiesms.service.impl;

import com.sf.universitiesms.dao.SeatMatrixDao;
import com.sf.universitiesms.exception.AppException;
import com.sf.universitiesms.exception.SeatFullException;
import com.sf.universitiesms.model.SeatMatrix;
import com.sf.universitiesms.model.University;
import com.sf.universitiesms.repository.SeatMatrixRepository;
import com.sf.universitiesms.service.SeatMatrixService;
import com.sf.universitiesms.service.UniversityService;
import com.sf.universitiesms.utils.UniversityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class SeatMatrixServiceImpl implements SeatMatrixService {

    static final Logger LOGGER = LoggerFactory.getLogger(SeatMatrixServiceImpl.class);

    @Autowired
    private SeatMatrixRepository seatMatrixRepository;

    @Autowired
    private UniversityService universityService;

    @Override
    public SeatMatrix getSeatMatrixByIdandCourse(String universityId, String course) {
        LOGGER.debug("getSeatMatrixByIdandCourse : getting seatmatrix for university_id {} and course {}",universityId, course);
        try {
            SeatMatrixDao seatMatrixDao = getSeatMatrixDaoByCourseandUniversityId(universityId, course);
            return UniversityMapper.mapDaotoSeatMatrix(seatMatrixDao);
        } catch (Exception e) {
            AppException appException = new AppException("Something went wrong");
            appException.initCause(e);
            appException.printStackTrace();
            throw appException;
        }
    }

    private SeatMatrixDao getSeatMatrixDaoByCourseandUniversityId(String universityId, String course) throws Exception {
        SeatMatrixDao seatMatrixDao = seatMatrixRepository.findSeatMatrixByIDandCourse(course, universityId);
        if (seatMatrixDao != null) {
            LOGGER.debug("getSeatMatrixByIdandCourse : found SeatMatrix for course {}", seatMatrixDao.getCourse());
            LOGGER.debug("getSeatMatrixByIdandCourse : found SeatMatrix for university {}", seatMatrixDao.getUniversityDao().getUniversityName());
            return seatMatrixDao;
        } else {
            LOGGER.debug("getSeatMatrixByIdandCourse : No data returned from database");
            throw new Exception("Null data returned from database");
        }
    }


    @Override
    @Transactional
    public SeatMatrix decreaseSeatAvaiabilityByOne(String universityId, String course) {
        LOGGER.debug("decreaseSeatAvaiabilityByOne : decreasing available seat by 1 for university {} and course {}", universityId, course);
        try {
            SeatMatrixDao seatMatrixDao = getSeatMatrixDaoByCourseandUniversityId(universityId, course);
            if (seatMatrixDao.getAvailableSeatNumber() == 0) {
                throw new RuntimeException("Seat full for "+ seatMatrixDao.getUniversityDao().getUniversityName());
            }
            LOGGER.debug("decreaseSeatAvaiabilityByOne : found seat availability {}", seatMatrixDao.getAvailableSeatNumber());
            seatMatrixDao = seatMatrixRepository.save(seatMatrixDao.decreaseAvailableCount());
            LOGGER.debug("decreaseSeatAvaiabilityByOne : updated seat {} with available count {}", seatMatrixDao.getSeatId(), seatMatrixDao.getAvailableSeatNumber());
            return UniversityMapper.mapDaotoSeatMatrix(seatMatrixDao);
        }  catch (RuntimeException r) {
            LOGGER.debug("decreaseSeatAvaiabilityByOne : seats are full for {}", universityId);
            throw new SeatFullException(r.getMessage());
        } catch (Exception e) {
            LOGGER.debug("decreaseSeatAvaiabilityByOne : found exception "+ e.getMessage());
            AppException appException = new AppException("Something went wrong");
            appException.initCause(e);
            appException.printStackTrace();
            throw appException;
        }
    }

    @Override
    public List<SeatMatrix> addSeatMatrix(String universityId, List<SeatMatrix> seatMatrices) {
        LOGGER.debug("addSeatMatrix : Adding seat matrix for university "+ universityId);
        LOGGER.debug("addSeatMatrix : Request for count "+ seatMatrices.size());
        try {
            University university = universityService.getUniversityById(universityId);
            LOGGER.debug("addSeatMatrix : Found university details for "+ university.getUniversityName());
            List<SeatMatrixDao> seatMatrixDaos = seatMatrices.stream()
                    .map(seatMatrix -> {
                        SeatMatrixDao seatMatrixDao = UniversityMapper.mapSeatMatrixtoDao(seatMatrix);
                        seatMatrixDao.setUniversityDao(UniversityMapper.mapUniversityToDao(university));
                        return seatMatrixDao;
                    }).collect(Collectors.toList());
            LOGGER.debug("addSeatMatrix : Conversion to Dao successful "+ seatMatrixDaos.size());
            List<SeatMatrixDao> savedSeatMatrix = seatMatrixRepository.saveAll(seatMatrixDaos);
            LOGGER.debug("addSeatMatrix : Details saved successfully " + savedSeatMatrix.size());
            return savedSeatMatrix.stream().map(UniversityMapper::mapDaotoSeatMatrix)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.debug("addSeatMatrix : Exception occured "+ e.getMessage());
            AppException appException = new AppException(e.getMessage());
            appException.initCause(appException);
            appException.printStackTrace();
            throw  appException;
        }
    }


}
