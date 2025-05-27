package com.killerexpertise.example.repository;


import com.killerexpertise.example.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);

    List<Student> findAll();

    Optional<Student> findById(int id);

    Student deleteById(int id);
}
