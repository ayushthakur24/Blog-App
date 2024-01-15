package com.springboot.springbootrestapi.controller;

import com.springboot.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//defining a base url on class level
@RequestMapping("student")
public class StudentController {

    @GetMapping("/1")
    public Student getStudentDetails(){
        Student student = new Student(
                1,
                "Ayush",
                "Thakur"
        );
        //returns student object as JSON in response to the client.
        return student;
    }


    @GetMapping("/allstudents")
    public List<Student> getAllStudentDetails(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"Aush","Thakur"));
        studentList.add(new Student(2,"Kunal","Thakur"));
        studentList.add(new Student(3,"Rohit","Thakur"));
        studentList.add(new Student(4,"Kamal","Thakur"));

        return studentList;
    }

    //single path variable
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1,"Aush","Thakur"));
        studentList.add(new Student(2,"Kunal","Thakur"));
        studentList.add(new Student(3,"Rohit","Thakur"));
        studentList.add(new Student(4,"Kamal","Thakur"));
        return studentList.get(id-1);
    }

    //multiple Path variables can also be passed
    @GetMapping("/{id}/{first-name}/{last-name}")
    public Student getByDetails(@PathVariable int id, @PathVariable("first-name") String firstName,
                                @PathVariable("last-name") String lastName){
        return new Student(id,firstName,lastName);
    }


    //How to handle query parameters in the api
    //ex - http://localhost:8080/student/query?id=8
    @GetMapping("/students/query")
    public Student getByQuery(@RequestParam int id){
        return  new Student(id,"Unknown","Caller");
    }

    //How to handle multiple query parameters
    @GetMapping("/student/query")
    public Student getByMQuery(@RequestParam int id,
                               @RequestParam String fName,
                               @RequestParam String lName){
        return new Student(id,fName,lName);
    }
    //Post Mapping and RequestBody
    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //With specific/custom HTTP Status code/s
    @PostMapping("/students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudentWithHTTP(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //PUT Request
    @PutMapping("/students/{id}/update")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //delete mapping
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){

        return "Student with " + id + " deleted successfully";
    }




}
