package com.assignment.student.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRunTimeException extends RuntimeException{
    private final String error;
}
