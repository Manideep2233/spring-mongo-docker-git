package com.spring_mongo_docker_git.repo;


import com.spring_mongo_docker_git.entity.Role;
import com.spring_mongo_docker_git.entity.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<Role,String> {
    Optional<Role> findByRole(RoleEnum role);
}
