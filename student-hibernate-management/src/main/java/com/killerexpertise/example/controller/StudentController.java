package com.killerexpertise.example.controller;

import com.killerexpertise.example.model.Student;
import com.killerexpertise.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService service;

    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        logger.info("Creating student: {}", student);
        return new ResponseEntity<>(service.createStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") int studentId) {
        logger.info("Deleting student with ID: {}", studentId);
        return new ResponseEntity<>(service.deleteStudent(studentId), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> retrieveStudent(@PathVariable("id") int studentId) {
        logger.info("Retrieving student with ID: {}", studentId);
        return new ResponseEntity<>(service.retrieveStudent(studentId), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public List<Student> retrieveStudents() {
        logger.info("Retrieving all students");
        return service.retrieveStudents();
    }
}
