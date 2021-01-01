package com.sf.universitiesms.service;

import com.sf.universitiesms.model.University;

public interface UniversityService {
    University addNewUniversityDetails(University university);

    University getUniversityById(String universityId);
}
