package com.assignment.student.service.impl;

import com.assignment.student.converter.StudentConverter;
import com.assignment.student.dto.DepartmentDTO;
import com.assignment.student.dto.StudentDTO;
import com.assignment.student.entity.Department;
import com.assignment.student.entity.Student;
import com.assignment.student.exception.StudentRunTimeException;
import com.assignment.student.repostory.IStudentRepository;
import com.assignment.student.service.contract.IDepartmentService;
import com.assignment.student.service.contract.IStudentService;
import com.assignment.student.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final IDepartmentService departmentService;
    private final IStudentRepository studentRepository;
    private final StudentConverter studentConverter;

    @Override
    public String createStudent(StudentDTO studentDTO) {
        var department = departmentService.getDepartmentByName(studentDTO.getDepartment());
        if(department == null){
            throw new StudentRunTimeException(Constants.ERR_DEPARTMENT_NOT_FOUND);
        }
        Student student = studentRepository.save(studentConverter.DtoToEntity(studentDTO, department));
        return String.valueOf(student.getId());
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        var studentList = studentRepository.findAll();
        return studentList.stream()
                .map(e -> studentConverter.entityToDto(e))
                .toList();
    }

    @Override
    public StudentDTO getIndividualStudent(String id) {
        var student = studentRepository.findById(UUID.fromString(id));
        if(!student.isPresent()){
            throw new StudentRunTimeException(Constants.ERR_STUDENT_NOT_FOUND);
        }
        return studentConverter.entityToDto(student.get());
    }

}
