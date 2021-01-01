package com.sf.universitiesms.service.impl;

import com.sf.universitiesms.dao.UniversityDao;
import com.sf.universitiesms.exception.AppException;
import com.sf.universitiesms.exception.NoUniversityFound;
import com.sf.universitiesms.model.University;
import com.sf.universitiesms.repository.UniversityRepository;
import com.sf.universitiesms.service.UniversityService;
import com.sf.universitiesms.utils.UniversityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class UniversityServiceImpl implements UniversityService {

    static final Logger LOGGER = LoggerFactory.getLogger(UniversityServiceImpl.class);

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public University addNewUniversityDetails(University university) {
        try {
            UniversityDao savedUniversityDao = universityRepository.save(UniversityMapper.mapUniversityToDao(university));
            return UniversityMapper.mapDaotoUniversity(savedUniversityDao);
        } catch (Exception e) {
            AppException appException = new AppException("Something went wrong");
            appException.initCause(e);
            appException.printStackTrace();
            throw appException;
        }
    }

    @Override
    public University getUniversityById(String universityId) {
        Optional<UniversityDao> optionalUniversityDao = universityRepository.findById(universityId);
        if (optionalUniversityDao.isPresent()) {
            return UniversityMapper.mapDaotoUniversity(optionalUniversityDao.get());
        }
        throw new NoUniversityFound("No university found with id "+ universityId);
    }
}
