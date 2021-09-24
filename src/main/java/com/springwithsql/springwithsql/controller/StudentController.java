package com.springwithsql.springwithsql.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springwithsql.springwithsql.model.Student;
import com.springwithsql.springwithsql.model.Success;
import com.springwithsql.springwithsql.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@CrossOrigin
@RestController
public class StudentController {
    
    @Autowired
    private StudentService service;
    Map<String, Object> map = new HashMap<>();

    @PostMapping("/addStudent")
    @CrossOrigin(methods = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody Student addStudent,
    		@RequestHeader("Access-Control-Allow-Origin") String star, 
    		@RequestHeader("Access-Control-Allow-Headers") String accept) {
    	
    	map.clear();
//    	Student student = service.saveStudent(addStudent);

//        if(student.getRollNo()==addStudent.getRollNo()) {
//        	map.put("status_code", 401);
//        	map.put("msg", "Student already exists!");
//             throw new RecordNotFoundException("Error occurred");
//        }
        
        map.put("status_code", 200);
    	map.put("msg", "Success");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
//        return map;
    }
    
    @PostMapping("/addStudents")
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody List<Student> students) {
    	map.clear();
    	
    	if(students == null) {
        	map.put("status_code", 401);
        	map.put("msg", "Student already exists!");
             throw new RecordNotFoundException("Error occurred");
        }
    	
        service.saveStudents(students);
        map.put("status_code", 200);
        map.put("msg", "Success");
        
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);  
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<Map<String, Object>> findAllStudents() {
    	map.clear();
    	
         service.getStudents();
         map.put("status_code", 200);
         map.put("msg", "Success");
         map.put("student_list", service.getStudents());
         return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @GetMapping("/getStudentByRollNo/{roll_no}")
    public ResponseEntity<Map<String, Object>> findStudentByRollNo(@PathVariable int roll_no) {
    	map.clear();
    	Student student = service.getStudentsByRollNo(roll_no);
    	
        if(student == null) {
        	map.put("status_code", 401);
        	map.put("msg", "Student Not found");
        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
        }
        
        map.put("status_code", 200);
    	map.put("msg", "Student found!");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        
//        return service.getStudentsByRollNo(roll_no);
    }

    @GetMapping("/getStudentByName/{name}")
    public ResponseEntity<Map<String, Object>> findStudentByName(@PathVariable String name) {
       
        Student student = service.getStudentsByName(name);
        map.clear();
    	
        if(student == null) {
        	map.put("status_code", 401);
        	map.put("msg", "Student Not found");
        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
        }
        
        map.put("status_code", 200);
    	map.put("msg", "Student found!");
    	
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<Map<String, Object>> updateStudent(@RequestBody Student updateStudent) {
        
        map.clear();
        
        if(service.getStudentsByRollNo(updateStudent.getRollNo()) == null) {
        	map.put("status_code", 401);
        	map.put("msg", "Student Not found");
        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
        }
        Student student = service.updateStudent(updateStudent);
        map.put("status_code", 200);
    	map.put("msg", "Success");
    	
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roll_no}")
    @CrossOrigin(methods = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable int roll_no) {
    	map.clear();
    	
    	Student student = service.getStudentsByRollNo(roll_no);
    	
        if(student == null) {
        	map.put("status_code", 401);
        	map.put("msg", "Student Not found");
        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
        }
        service.deleteStudent(roll_no);
        
        map.put("status_code", 200);
    	map.put("msg", "Success");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteAll")
    @CrossOrigin(methods = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Object>> deleteAllStudents() {
    	
    	String student = service.deleteAllStudents();
    	map.clear();
        if(student == null) {
        	map.put("status_code", 401);
        	map.put("msg", "Student Not found");
        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
        }
       
        map.put("status_code", 200);
    	map.put("msg", "Success");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class RecordNotFoundException extends RuntimeException
    {
        public RecordNotFoundException(String exception) {
            super(exception);
        }
    }
}
