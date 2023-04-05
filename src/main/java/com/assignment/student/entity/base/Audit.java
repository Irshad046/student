package com.assignment.student.entity.base;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Slf4j
@Data
//@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class Audit implements Serializable {

    private static final long serialVersionUID = 1844558484L;

    @Field(name = "created_on")
    private LocalDateTime createdOn;

    @Field(name = "created_by")
    private Long createdBy;

    @Field(name = "updated_on")
    private LocalDateTime updatedOn;

    @Field(name = "updated_by")
    private Long updatedBy;



}
