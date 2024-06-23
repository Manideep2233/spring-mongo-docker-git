package com.spring_mongo_docker_git.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleApiController {
    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "This is an admin-only endpoint.";
    }

    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER')")
    public String userAccess() {
        return "This is a user-only endpoint.";
    }

    @GetMapping("/producer")
//    @PreAuthorize("hasRole('ROLE_PRODUCER')")
    public String producerAccess() {
        return "This is a producer-only endpoint.";
    }

    @GetMapping("/producerAndAdmin")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PRODUCER')")
    public String producerAndAdminAccess() {
        return "This is a producer-Admin endpoint.";
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
//        Optional<Employee> byName = employeeRepo.findByUsername(loginDto.getUsername());
//        if (byName.isEmpty()){
//            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
//        }
//        Employee user = byName.get();
//        if (passwordEncoder.matches(loginDto.getPassword(),user.getPassword())){
//            String token = JwtUtil.generateToken(loginDto.getUsername());
//            return new ResponseEntity<>(loginDto.getUsername()+" Logged In Successfully!\n"+token,
//                    HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Invalid Password", HttpStatus.BAD_REQUEST);
//    }


}
