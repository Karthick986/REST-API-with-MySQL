package com.springwithsql.springwithsql.repository;

import com.springwithsql.springwithsql.model.Student;
import com.springwithsql.springwithsql.model.Success;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByName(String name);

}