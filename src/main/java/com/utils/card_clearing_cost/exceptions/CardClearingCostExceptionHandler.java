package com.utils.card_clearing_cost.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CardClearingCostExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiExceptionMessage> processValidationError(NotFoundException e) {

        logger.info("ERROR --> {}", e.getMessage());

        ApiExceptionMessage response = buildApiExceptionMessage(e, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ApiExceptionMessage buildApiExceptionMessage(RuntimeException e, HttpStatus status) {
        ApiExceptionMessage response = new ApiExceptionMessage();
        response.setStatus(status);
        response.setMessage(e.getMessage());
        return response;
    }
}