package com.sf.universitiesms.controller;

import com.sf.universitiesms.exception.AppException;
import com.sf.universitiesms.exception.NoUniversityFound;
import com.sf.universitiesms.model.University;
import com.sf.universitiesms.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/university")
public class UniversityController {

    Logger logger = LoggerFactory.getLogger(UniversityController.class);

    @Autowired
    private UniversityService universityService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<University> addNewUniversity(@RequestBody University university) {
        logger.info(university.toString());
        if (university!=null) {
            University savedUniversity = universityService.addNewUniversityDetails(university);
            return new ResponseEntity<>(savedUniversity, HttpStatus.CREATED);
        } else {
            throw new AppException("University details provided are null");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<University> getUniversityDetailsById(@PathVariable("id") String universityId) {
        if (universityId!=null) {
            University university = universityService.getUniversityById(universityId);
            return new ResponseEntity<>(university, HttpStatus.OK);
        } else {
            throw new NoUniversityFound("no university found with id "+ universityId);
        }
    }


}
