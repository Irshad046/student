package com.assignment.student.service.contract;

import com.assignment.student.dto.StudentDTO;
import com.assignment.student.entity.Student;

import java.util.List;

public interface IStudentService {
    String createStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getIndividualStudent(String id);
}
