package com.spring_mongo_docker_git.repo;

import com.spring_mongo_docker_git.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {
    Optional<Employee> findByUsername(String userName);
}
