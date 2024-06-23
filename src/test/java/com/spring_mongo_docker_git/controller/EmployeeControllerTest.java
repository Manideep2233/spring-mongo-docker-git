package com.spring_mongo_docker_git.controller;

import com.spring_mongo_docker_git.entity.Employee;
import com.spring_mongo_docker_git.entity.Role;
import com.spring_mongo_docker_git.entity.RoleEnum;
import com.spring_mongo_docker_git.repo.EmployeeRepo;
import com.spring_mongo_docker_git.repo.RoleRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private static Employee employee;
    @Mock
    private RoleRepo roleRepo;
    @Mock
    private EmployeeRepo employeeRepo;
    @InjectMocks
    private EmployeeController employeeController;

    @BeforeAll
    public static void setUp() {
        Role role = new Role("1", RoleEnum.ADMIN);
        employee = new Employee("1", "Mani-Test", "Test@123", List.of(role));
    }

    @Test
    public void getEmployeeByUsernameTest() {
        when(employeeRepo.findByUsername("Mani-Test")).thenReturn(Optional.of(employee));

        ResponseEntity<?> response = employeeController.getByUsername("Mani-Test");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    public void getEmployeeByUsernameNotFoundTest() {
        when(employeeRepo.findByUsername("Nonexistent-User")).thenReturn(Optional.empty());

        ResponseEntity<?> response = employeeController.getByUsername("Nonexistent-User");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Employee is not found in DB with username: Nonexistent-User", response.getBody());
    }
}