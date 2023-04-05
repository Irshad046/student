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
public class RepositoryTest {

    @Mock
    private IStudentRepository studentRepository;
    @Mock
    private IDepartmentRepository departmentRepository;

    @Test
    void departmentCreationRepository() {
        final var departmentToSave = Department.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .departmentName("Test-Dept")
                .build();
        when(departmentRepository.save(any(Department.class))).thenReturn(departmentToSave);
        Department dept = departmentRepository.save(departmentToSave);
        assertEquals(dept.getDepartmentName(), departmentToSave.getDepartmentName());
    }

    @Test
    void duplicateDepartmentNotAllowedRepository() {
        String exception = null;

        final var departmentToSave = Department.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .departmentName("Test-Dept")
                .build();
        try{
            when(departmentRepository.save(any(Department.class))).thenThrow(new DepartmentRunTimeException(Constants.ERR_DEPARTMENT_ALREADY_EXISTS));
            Department dept = departmentRepository.save(departmentToSave);
            assertEquals(dept.getDepartmentName(), departmentToSave.getDepartmentName());
        }catch (DepartmentRunTimeException departmentRunTimeException){
            System.out.println("In the exception block");
            exception = departmentRunTimeException.getError();
        }
        assertEquals(exception, Constants.ERR_DEPARTMENT_ALREADY_EXISTS);
    }

    @Test
    void allowStudentCreationRepository() {
        final var studentToSave = Student.builder()
                .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                .firstName("First Name")
                .lastName("Last Name")
                .department(Department.builder()
                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                        .departmentName("Test-Dept")
                        .build())
                .build();
        when(this.studentRepository.save(any(Student.class))).thenReturn(studentToSave);
        Student student = this.studentRepository.save(studentToSave);
        assertEquals(student.getFirstName(), studentToSave.getFirstName());
        assertEquals(student.getLastName(), studentToSave.getLastName());
        assertEquals(student.getDepartment().getDepartmentName(), studentToSave.getDepartment().getDepartmentName());

    }

    @Test
    void disallowUnknownDepartmentRepository() {
        String exception = "";
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
            when(studentRepository.save(any(Student.class))).thenThrow(new StudentRunTimeException(Constants.ERR_DEPARTMENT_NOT_FOUND));
            Student resp = studentRepository.save(studentToSave);
        }catch (StudentRunTimeException studentRunTimeException){
            System.out.println("In the exception block");
            exception = studentRunTimeException.getError();
        }
        assertEquals(exception, Constants.ERR_DEPARTMENT_NOT_FOUND);
    }

    @Test
    void studentNotFoundRepository() {
        when(studentRepository.findById(any(UUID.class))).thenReturn(null);
        Optional<Student> resp = studentRepository.findById(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"));
        assertEquals(resp, null);
    }

    @Test
    void fetchStudentRepository() {
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

        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(student));
        Optional<Student> resp = studentRepository.findById(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"));
        assertInstanceOf(resp.getClass().getClass(), Student.class);
        assertNotNull(resp.getClass().getClass());
    }

    @Test
    void fetchAllStudentRepository() {
        final var studentDto = StudentDTO.builder()
                .lastName("Last Name")
                .firstName("First Name")
                .department("Test-Dept")
                .build();
        final var studentList =
                new ArrayList<>(List.of(
                        Student.builder()
                                .id(UUID.fromString("123f843d-4090-4506-a4a8-66236705039e"))
                                .firstName("First One")
                                .lastName("Last One")
                                .department(Department.builder()
                                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                                        .departmentName("Test-Dept")
                                        .build())
                                .build(),

                        Student.builder()
                                .id(UUID.fromString("123f843d-4090-4506-a4a8-66236705039e"))
                                .firstName("First Two")
                                .lastName("Last Two")
                                .department(Department.builder()
                                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                                        .departmentName("DATA")
                                        .build())
                                .build(),

                        Student.builder()
                                .id(UUID.fromString("123f843d-4090-4506-a4a8-66236705039e"))
                                .firstName("First Three")
                                .lastName("Last Three")
                                .department(Department.builder()
                                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                                        .departmentName("CS")
                                        .build())
                                .build(),

                        Student.builder()
                                .id(UUID.fromString("123f843d-4090-4506-a4a8-66236705039e"))
                                .firstName("First Four")
                                .lastName("Last Four")
                                .department(Department.builder()
                                        .id(UUID.fromString("5dcf843d-4090-4506-a4a8-66236705039e"))
                                        .departmentName("Test-Dept")
                                        .build())
                                .build()
                ));

        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> resp = studentRepository.findAll();
        assertEquals(resp.size(), 4);
    }

}
