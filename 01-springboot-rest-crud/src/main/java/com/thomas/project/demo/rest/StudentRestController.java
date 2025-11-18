package com.thomas.project.demo.rest;

import com.thomas.project.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //Define @PostConstruct to load the student data ... only once!

    @PostConstruct

    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Anthomas", "Longobardi"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Mary", "Jane"));
    }

    //Define endpoint for "/students}" - return student list
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    //Define endpoint for "/student/{studentId}" - return student
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId) {
        //just index into the list ... keep it simple for now
        //check studentID to verify in bounds
        if (studentId < 0 || studentId >= theStudents.size()) {
            throw new StudentNotFoundException("Student id not found: " + studentId);
        }
        return theStudents.get(studentId);
    }

    //add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());

        error.setMessage(exc.getMessage());

        error.setTimestamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    //add another exception handler ... to catch any exception

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}





































