package com.assignment.student.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    static final String RESPONSE_CODE = "Response Code";
    static final String RESPONSE_MESSAGE = "message";
    static final String TIME_STAMP = "Time Stamp";
    static final String LEVEL = "Level";
    static final String ERROR = "ERROR";

    @ExceptionHandler(StudentRunTimeException.class)
    public ResponseEntity<?> handleStudentRunTimeException(StudentRunTimeException srte){
        log.info("Handling StudentRunTimeException, transforming exception in response.");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(handleCustomExceptions(srte.getError()));
    }

    @ExceptionHandler(DepartmentRunTimeException.class)
    public ResponseEntity<?> handleDepartmentRunTimeException(DepartmentRunTimeException drte){
        log.info("Handling DepartmentRunTimeException, transforming exception in response.");
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(handleCustomExceptions(drte.getError()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        log.info("Handling Exception, transforming exception in response.");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(handleCustomExceptions(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(Exception e){
        log.info("Handling RuntimeException, transforming exception in response.");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(handleCustomExceptions(e.getMessage()));
    }
    private Map<String, String> handleCustomExceptions(String error){
        Map<String, String> responseObject = new HashMap<>();
        responseObject.put(TIME_STAMP, String.valueOf(Instant.now()));
        responseObject.put(LEVEL, ERROR);
        responseObject.put(RESPONSE_MESSAGE, error);

        log.info("Code is : " + responseObject.get(RESPONSE_CODE));
        log.info("Message is : " + responseObject.get(RESPONSE_MESSAGE));

        return responseObject;
    }


}
