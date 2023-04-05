package com.assignment.student.converter;


import com.assignment.student.dto.StudentDTO;
import com.assignment.student.entity.Department;
import com.assignment.student.entity.Student;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StudentConverter {

    public Student DtoToEntity(StudentDTO studentDTO, Department department){
        return Student.builder()
                .id(UUID.randomUUID())
                .department(department)
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .build();
    }

    public StudentDTO entityToDto(Student student){
        return StudentDTO.builder()
                .id(student.getId().toString())
                .department(student.getDepartment().getDepartmentName())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .build();
    }
}
