package com.sf.universitiesms.repository;

import com.sf.universitiesms.dao.UniversityDao;
import com.sf.universitiesms.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<UniversityDao, String> {
}
