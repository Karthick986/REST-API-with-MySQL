package com.springwithsql.springwithsql.service;

import java.util.List;
import com.springwithsql.springwithsql.model.Student;
import com.springwithsql.springwithsql.model.Success;
import com.springwithsql.springwithsql.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository repository;
    


    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> saveStudents(List<Student> students) {
        return repository.saveAll(students);
    }

    public List<Student> getStudents() {
        return repository.findAll();
    }

    public Student getStudentsByRollNo(int rollNo) {
        return repository.findById(rollNo).orElse(null);
    }

    public Student getStudentsByName(String name) {
        return repository.findByName(name);
    }

    public String deleteStudent(int rollNo) {
        repository.deleteById(rollNo);
        return "Student record deleted: "+rollNo;
    }
    
    public String deleteAllStudents() {
        repository.deleteAll();
        return "Student record deleted";
    }

    public Student updateStudent(Student student) {
        Student existingStudent = repository.findById(student.getRollNo()).orElse(null);
        existingStudent.setName(student.getName());
        existingStudent.setPercentage(student.getPercentage());
        existingStudent.setBranch(student.getBranch());
        return repository.save(existingStudent);
    }
}
