package com.assignment.student.service.impl;

import com.assignment.student.converter.DepartmentConverter;
import com.assignment.student.dto.DepartmentDTO;
import com.assignment.student.entity.Department;
import com.assignment.student.exception.DepartmentRunTimeException;
import com.assignment.student.repostory.IDepartmentRepository;
import com.assignment.student.service.contract.IDepartmentService;
import com.assignment.student.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;

    @Override
    public Department getDepartmentByName(String name) {
        return departmentRepository.getByDepartmentNameIgnoreCase(name);
    }

    @Override
    public String addDepartment(String name) {
        var department = departmentRepository.getByDepartmentNameIgnoreCase(name);
        if(department != null){
            throw new DepartmentRunTimeException(Constants.ERR_DEPARTMENT_ALREADY_EXISTS);
        }
        var newDepartment = departmentRepository.save(Department.builder().id(UUID.randomUUID()).departmentName(name).build());
        return newDepartment.getId().toString();
    }


}
