package com.killerexpertise.example.repository.impl;

import com.killerexpertise.example.model.Student;
import com.killerexpertise.example.repository.StudentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional  // manages transactions automatically
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public Student save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Optional<Student> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        return Optional.ofNullable(student);
    }

    @Override
    public Student deleteById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.byId(Student.class).load(id);
        if (student != null) {
            session.delete(student);
        }
        return student;
    }
}
