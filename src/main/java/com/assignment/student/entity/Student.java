package com.assignment.student.entity;


import com.assignment.student.entity.base.Audit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student")
@Builder(toBuilder = true)
public class Student extends Audit {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private Department department;

}
