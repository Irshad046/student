package com.assignment.student.controller;

import com.assignment.student.request.AddDepartmentRequest;
import com.assignment.student.response.GenericResponse;
import com.assignment.student.service.contract.IDepartmentService;
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
@RequestMapping(value = "v1/department")
public class DepartmentCommandController {

    private final IDepartmentService departmentService;

    @PostMapping(path = "add-department",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Department creation API",
            notes = "This API will create a new record in the database for the requested department")
    public ResponseEntity<?> addDepartment(@RequestBody AddDepartmentRequest request){
        return ResponseEntity.ok(new GenericResponse(Constants.INT_SUCCESS, Constants.STR_SUCCESS, departmentService.addDepartment(request.getDepartmentName())));
    }

}
