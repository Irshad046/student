package com.assignment.student.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentRunTimeException extends RuntimeException{
    private final String error;
}
