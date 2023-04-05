package com.assignment.student.service.contract;

import com.assignment.student.dto.DepartmentDTO;
import com.assignment.student.entity.Department;

public interface IDepartmentService {

    Department getDepartmentByName(String name);

    String addDepartment(String name);

}
