package com.assignment.student.repostory;

import com.assignment.student.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDepartmentRepository extends MongoRepository<Department, UUID> {

    Department getByDepartmentNameIgnoreCase(String name);

}
