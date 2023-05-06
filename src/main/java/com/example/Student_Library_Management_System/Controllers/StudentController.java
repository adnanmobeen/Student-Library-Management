package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.RequestDTOs.StudentUpdateMobNoDTO;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentservice;

    @PostMapping("/add_student")
    public String createStudent(@RequestBody Student student){

        return studentservice.createStudent(student);
    }

    @GetMapping("/get_student")
    public String findNameByEmail(@RequestParam("email") String email){

        return  studentservice.findNameByEmail(email);
    }

    @GetMapping("/find_name")
    public List<String>findByCountry(@RequestParam("country") String country){
        return studentservice.findByCountry(country);
    }

    @PutMapping("/update_mobNo")
    public String updateMobNo(@RequestBody StudentUpdateMobNoDTO studentUpdateMobNoDTO){
        return studentservice.updateMobNo(studentUpdateMobNoDTO);
    }
}
