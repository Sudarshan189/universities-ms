package com.sf.universitiesms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoUniversityFound extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(NoUniversityFound.class);
    public NoUniversityFound(String message) {
        super(message);
        logger.warn(message);
    }
}
