package com.assignment.student.controller;

import com.assignment.student.response.GenericResponse;
import com.assignment.student.service.contract.IStudentService;
import com.assignment.student.util.Constants;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/student")
public class StudentQueryController {

    private final IStudentService studentService;

    @GetMapping(path = "get-all-students",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Student retrieval API",
            notes = "This API will retrieval all record in the database for the students")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok(new GenericResponse(Constants.INT_SUCCESS, Constants.STR_SUCCESS, studentService.getAllStudents()));
    }

    @GetMapping(path = "{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Student retrieval API",
            notes = "This API will retrieval single record in the database for the specific student")
    public ResponseEntity<?> getIndividualStudent(@PathVariable String id){
        return ResponseEntity.ok(new GenericResponse(Constants.INT_SUCCESS, Constants.STR_SUCCESS, studentService.getIndividualStudent(id)));
    }

}
