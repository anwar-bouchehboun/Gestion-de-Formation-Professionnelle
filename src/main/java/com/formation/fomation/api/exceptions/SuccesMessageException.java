package com.formation.fomation.api.exceptions;

import com.formation.fomation.api.utilitaire.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class SuccesMessageException {

    @ExceptionHandler(ClassSucesseExption.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SuccessResponse> succeClasseException(ClassSucesseExption ex) {
        SuccessResponse success = new SuccessResponse(
                HttpStatus.OK.value(),
                "Operation Successful",
                ex.getMessage()
        );
        return new ResponseEntity<>(success, HttpStatus.OK);
    }

}
