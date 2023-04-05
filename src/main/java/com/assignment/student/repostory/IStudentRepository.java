package com.assignment.student.repostory;

import com.assignment.student.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IStudentRepository extends MongoRepository<Student, UUID> {



}
