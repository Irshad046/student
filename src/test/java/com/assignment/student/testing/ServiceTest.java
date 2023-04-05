package com.assignment.student.testing;


import com.assignment.student.dto.StudentDTO;
import com.assignment.student.entity.Department;
import com.assignment.student.entity.Student;
import com.assignment.student.exception.DepartmentRunTimeException;
import com.assignment.student.exception.StudentRunTimeException;
import com.assignment.student.repostory.IDepartmentRepository;
import com.assignment.student.repostory.IStudentRepository;
import com.assignment.student.service.contract.IDepartmentService;
import com.assignment.student.service.contract.IStudentService;
import com.assignment.student.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private IDepartmentService departmentService;
    @Mock
    private IStudentService studentService;

    @Test
    void departmentCreationService() {
        final var departmentToSave = Department.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .departmentName("Test-Dept")
                .build();
        when(departmentService.addDepartment(any(String.class))).thenReturn("5dcf843d-4090-4506-a4a8-66236705039e");
        String resp = departmentService.addDepartment("Test-Dept");
        assertEquals(resp, "5dcf843d-4090-4506-a4a8-66236705039e");
    }

    @Test
    void duplicateDepartmentNotAllowedService() {
        String exception = null;

        final var departmentToSave = Department.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .departmentName("Test-Dept")
                .build();
        try{
            when(departmentService.addDepartment(any(String.class))).thenThrow(new DepartmentRunTimeException(Constants.ERR_DEPARTMENT_ALREADY_EXISTS));
            String resp = departmentService.addDepartment("Test-Dept");
        }catch (DepartmentRunTimeException departmentRunTimeException){
            System.out.println("In the exception block");
            exception = departmentRunTimeException.getError();
        }
        assertEquals(exception, Constants.ERR_DEPARTMENT_ALREADY_EXISTS);
    }

    @Test
    void allowStudentCreationService() {
        final var studentDto = StudentDTO.builder()
                .lastName("Last Name")
                .firstName("First Name")
                .department("Test-Dept")
                .build();
        final var studentToSave = Student.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .firstName("First Name")
                .lastName("Last Name")
                .department(Department.builder()
                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                        .departmentName("Test-Dept")
                        .build())
                .build();
        when(studentService.createStudent(any(StudentDTO.class))).thenReturn("5dcf843d-4090-4506-a4a8-66236705039e");
        String resp = studentService.createStudent(studentDto);
        assertEquals(resp, "5dcf843d-4090-4506-a4a8-66236705039e");

    }

    @Test
    void disallowUnknownDepartmentService() {
        String exception = "";
        final var studentDto = StudentDTO.builder()
                .lastName("Last Name")
                .firstName("First Name")
                .department("Test-Dept")
                .build();
        final var studentToSave = Student.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .firstName("First Name")
                .lastName("Last Name")
                .department(Department.builder()
                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                        .departmentName("Test-Dept")
                        .build())
                .build();
        try{
            when(studentService.createStudent(any(StudentDTO.class))).thenThrow(new StudentRunTimeException(Constants.ERR_DEPARTMENT_NOT_FOUND));
            String resp = studentService.createStudent(studentDto);
        }catch (StudentRunTimeException studentRunTimeException){
            System.out.println("In the exception block");
            exception = studentRunTimeException.getError();
        }
        assertEquals(exception, Constants.ERR_DEPARTMENT_NOT_FOUND);
    }

    @Test
    void studentNotFoundService() {
        when(studentService.getIndividualStudent(any(String.class))).thenReturn(null);
        StudentDTO resp = studentService.getIndividualStudent("5dcf843d-4090-4506-a4a8-66236705039e");
        assertEquals(resp, null);
    }

    @Test
    void fetchStudentService() {
        final var studentDto = StudentDTO.builder()
                .lastName("Last Name")
                .firstName("First Name")
                .department("Test-Dept")
                .build();
        final var student = Student.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .firstName("First Name")
                .lastName("Last Name")
                .department(Department.builder()
                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                        .departmentName("Test-Dept")
                        .build())
                .build();

        when(studentService.getIndividualStudent(any(String.class))).thenReturn(studentDto);
        StudentDTO resp = studentService.getIndividualStudent("5dcf843d-4090-4506-a4a8-66236705039e");
        assertInstanceOf(resp.getClass().getClass(), Student.class);
        assertNotNull(resp.getClass().getClass());
    }

    @Test
    void fetchAllStudentService() {
        final var studentDto = StudentDTO.builder()
                .lastName("Last Name")
                .firstName("First Name")
                .department("Test-Dept")
                .build();
        final var studentList =
                new ArrayList<>(List.of(
                        StudentDTO.builder()
                                .id("123f843d-4090-4506-a4a8-66236705039e")
                                .firstName("First One")
                                .lastName("Last One")
                                .department("Test-Dept")
                                .build(),

                        StudentDTO.builder()
                                .id("123f843d-4090-4506-a4a8-66236705039e")
                                .firstName("First Two")
                                .lastName("Last Two")
                                .department("Test-Dept")
                                .build(),

                        StudentDTO.builder()
                                .id("123f843d-4090-4506-a4a8-66236705039e")
                                .firstName("First Three")
                                .lastName("Last Three")
                                .department("DATA")
                                .build(),

                        StudentDTO.builder()
                                .id("123f843d-4090-4506-a4a8-66236705039e")
                                .firstName("First Four")
                                .lastName("Last Four")
                                .department("CS")
                                .build()
                ));

        when(studentService.getAllStudents()).thenReturn(studentList);
        List<StudentDTO> resp = studentService.getAllStudents();
        assertEquals(resp.size(), 4);
    }
}
