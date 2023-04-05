package com.assignment.student.testing;

import com.assignment.student.controller.StudentCommandController;
import com.assignment.student.entity.Department;
import com.assignment.student.repostory.IDepartmentRepository;
import com.assignment.student.service.contract.IDepartmentService;
import com.assignment.student.service.contract.IStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.StringAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = StudentCommandController.class)
public class StudentTestClass {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IStudentService studentService;

    @Mock
    private IDepartmentService departmentService;

    @Mock
    private IDepartmentRepository departmentRepository;

    /*
    1 - when we try to add a student with a department that does not exists, it should not add the student ---- ok
    2 - when we try to add a student with a department that does exists, it should add one student in the system ---- ok
    3 - should not allow to duplicate department name, without case sensitive ---- ok
    4 - should allow a department to add ----- ok

    5 - when we try to get an individual student with non existing id, then it should return student not found ---- ok
    6 - when we try to get an individual student with existing id, then it should return student data ---- ok
    7 - when we try to get all the students in the system, all the existing students shall be returned as list in response object ----

    */



}
