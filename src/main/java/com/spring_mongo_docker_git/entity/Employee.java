package com.spring_mongo_docker_git.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document("employee")
public class Employee {
    @Id
    private String eid;
    private String username;
    private String password;
    @DBRef
    private List<Role> roles;
}
