package com.thomas.project.demo.rest;

import com.thomas.project.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    //Define endpoint for "/students" - return list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

        List<Student> theStudents = new ArrayList<>();

        theStudents.add(new Student("Anthomas", "Longobardi"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Mary", "Jane"));

        return theStudents;
    }
}
