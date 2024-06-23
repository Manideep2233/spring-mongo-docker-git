package com.spring_mongo_docker_git.controller;

import com.spring_mongo_docker_git.entity.Employee;
import com.spring_mongo_docker_git.entity.Role;
import com.spring_mongo_docker_git.entity.RoleEnum;
import com.spring_mongo_docker_git.repo.EmployeeRepo;
import com.spring_mongo_docker_git.repo.RoleRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private EmployeeRepo employeeRepo;


    @PostConstruct
    public void addRoleAndEmployee() {
        if (roleRepo.findAll().isEmpty()) {
            addRolesToDb();
        }
        if (employeeRepo.findAll().isEmpty()) {
            addEmployeesToDb();
        }
    }

    public void addRolesToDb() {

        RoleEnum[] roleNames = new RoleEnum[]{RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.PRODUCER};
        List<Role> list = Arrays.stream(roleNames).map(
                roleEnum -> {
                    Role role = new Role();
                    role.setRole(roleEnum);
                    return role;
                }
        ).toList();

        roleRepo.saveAll(list);
    }

    public void addEmployeesToDb() {

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder()
                .username("Mani-User")
                .password("Mani@123")
//                .password(passwordEncoder.encode("Manideep@223"))
                .roles(List.of(roleRepo.findByRole(RoleEnum.USER).get()))
                .build());
        employeeList.add(Employee.builder()
                .username("Mani-Admin")
                .password("Mani@123")
//                .password(passwordEncoder.encode("Manideep@223"))
                .roles(List.of(roleRepo.findByRole(RoleEnum.ADMIN).get(),
                        roleRepo.findByRole(RoleEnum.PRODUCER).get(),
                        roleRepo.findByRole(RoleEnum.USER).get())
                )
                .build());
        employeeList.add(Employee.builder()
                .username("Mani-Producer")
                .password("Mani@123")
//                .password(passwordEncoder.encode("Manideep@223"))
                .roles(List.of(roleRepo.findByRole(RoleEnum.PRODUCER).get()))
                .build());
        employeeRepo.saveAll(employeeList);
    }

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return employeeRepo
                .findAll()
                .stream()
                .peek(e -> System.out.println(e.toString()))
                .toList();
    }


    @GetMapping("/welcome")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeRepo.save(employee);
        return new ResponseEntity<>("Employee saved with id: " + employee1.getEid(),
                HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Optional<Employee> emp = employeeRepo.findById(employee.getEid());
        if (emp.isEmpty()) {
            return new ResponseEntity<>("Employee is not found in DB with id: " + employee.getEid(),
                    HttpStatus.NO_CONTENT);
        }
        emp.get().setUsername(employee.getUsername());
        emp.get().setPassword(employee.getPassword());
        emp.get().setRoles(employee.getRoles());
        Employee employee1 = employeeRepo.save(employee);
        return new ResponseEntity<>("Employee saved with id: " + employee1.getEid(),
                HttpStatus.OK);
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        Optional<Employee> emp = employeeRepo.findByUsername(username);
        if (emp.isEmpty()) {
            return new ResponseEntity<>("Employee is not found in DB with username: " + username,
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(emp.get(),
                HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmployees() {
        return new ResponseEntity<>(employeeRepo.findAll(),
                HttpStatus.OK);
    }


}
