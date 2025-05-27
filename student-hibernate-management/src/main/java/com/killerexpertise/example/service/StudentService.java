package com.killerexpertise.example.service;

import com.killerexpertise.example.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student deleteStudent(int id);
    Student retrieveStudent(int id);
    List<Student> retrieveStudents();
}
