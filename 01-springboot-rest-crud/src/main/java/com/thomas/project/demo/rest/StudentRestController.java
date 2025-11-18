package com.thomas.project.demo.rest;

import com.thomas.project.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return theStudents.get(studentId);
    }
}
