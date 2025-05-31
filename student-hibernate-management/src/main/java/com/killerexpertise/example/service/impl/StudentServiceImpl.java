package com.killerexpertise.example.service.impl;

import com.killerexpertise.example.exception.StudentNotFoundException;
import com.killerexpertise.example.model.Student;
import com.killerexpertise.example.repository.StudentRepository;
import com.killerexpertise.example.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Student createStudent(Student student) {
        logger.info("Creating student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(int id) {
        Student deletedStudent = studentRepository.deleteById(id);
        logger.info("Deleted student: {}", deletedStudent);
        return deletedStudent;
    }

    @Override
    public Student retrieveStudent(int id) {
        Optional<Student> retrievedStudent = studentRepository.findById(id);
        if (retrievedStudent.isEmpty()) {
            logger.warn("Student with ID {} not found", id);
            throw new StudentNotFoundException("Student not found");
        }
        logger.info("Retrieved student: {}", retrievedStudent.get());
        return retrievedStudent.get();
    }

    @Override
    public List<Student> retrieveStudents() {
        logger.info("Retrieving all students");
        return studentRepository.findAll();
    }
}
