package com.sf.universitiesms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(AppException.class);

    public AppException(String message) {
        super(message);
        logger.warn(message);
    }
}
