package com.assignment.student.entity;

import com.assignment.student.entity.base.Audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "department")
@Builder(toBuilder = true)
public class Department extends Audit {

    @Id
    private UUID id;
    private String departmentName;

//    @DocumentReference
//    private List<Student> studentList;
}
