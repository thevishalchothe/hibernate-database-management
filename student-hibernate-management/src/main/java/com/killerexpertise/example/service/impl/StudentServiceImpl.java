package com.killerexpertise.example.service.impl;

import com.killerexpertise.example.exception.StudentNotFoundException;
import com.killerexpertise.example.model.Student;
import com.killerexpertise.example.repository.StudentRepository;
import com.killerexpertise.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Student createStudent(Student student) {
        System.out.println("Inside service: " + student);
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(int id) {
        Student deletedStudent = studentRepository.deleteById(id);
        System.out.println("Deleted student: " + deletedStudent);
        return deletedStudent;
    }

    @Override
    public Student retrieveStudent(int id) {
        Optional<Student> retrievedStudent = studentRepository.findById(id);
        if (retrievedStudent.isEmpty()) {
            throw new StudentNotFoundException("student not found");
        }
        System.out.println("retrievedStudent : " + retrievedStudent);
        return retrievedStudent.get();
    }

    @Override
    public List<Student> retrieveStudents() {
        return studentRepository.findAll();
    }
}
