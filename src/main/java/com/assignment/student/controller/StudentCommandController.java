package com.assignment.student.controller;

import com.assignment.student.dto.StudentDTO;
import com.assignment.student.response.GenericResponse;
import com.assignment.student.service.contract.IStudentService;
import com.assignment.student.util.Constants;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/student")
public class StudentCommandController {

    private final IStudentService studentService;

    @PostMapping(path = "add-student",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Student creation API",
            notes = "This API will create a new record in the database for the requested student")
    public ResponseEntity<?> addStudent(@RequestBody StudentDTO request){
        return ResponseEntity.ok(new GenericResponse(Constants.INT_SUCCESS, Constants.STR_SUCCESS, studentService.createStudent(request)));
    }

}
