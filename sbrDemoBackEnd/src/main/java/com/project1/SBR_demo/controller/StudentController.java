package com.project1.SBR_demo.controller;


import com.project1.SBR_demo.model.Student;
import com.project1.SBR_demo.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
//@RequiredArgsConstructor
public class StudentController {
      private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
      public ResponseEntity<List<Student>> getStudent(){
          return new ResponseEntity<>(studentService.getStudent(), HttpStatus.FOUND);
      }


      @PostMapping
      public Student addStudent(@RequestBody Student student){
          return studentService.addStudent(student);
      }

      @PutMapping("/update/{id}")
      public Student  updateStudent(@RequestBody Student student,@PathVariable Long id){
          return studentService.updateStudent(student,id);
      }

      @DeleteMapping("/delete/{id}")
      public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
      }

      @GetMapping("/student/{id}")
      public Student getStudentById(@PathVariable Long id){
          return studentService.getStudentById(id);
      }

}
