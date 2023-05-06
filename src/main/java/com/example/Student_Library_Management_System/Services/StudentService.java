package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.RequestDTOs.StudentUpdateMobNoDTO;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentrepository;
    public String createStudent(Student student){
       //before saving any entity we need to set the attributes of every entity there are two types of attributes
        //one is auto generated which we don't need to set but others we need to set but in others some of them
        // are set from postman and some are set in service layer.and some are not possible to set then we don't set them.

        //these attributes are from card entity.
        Card card = new Card();
        card.setCardstatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        //these attributes are from student entity.
        student.setCard(card);


        studentrepository.save(student);
        return "Student and Card added.";
    }

    public  String findNameByEmail(String email){

        Student student = studentrepository.findByEmail(email);

        return student.getName();
    }

    public List<String> findByCountry(String country){
        List<Student>studentList = studentrepository.findByCountry(country);

        List<String>names = new ArrayList<>();
        for(Student s:studentList){
            names.add(s.getName());
        }
        return names;
    }

    public String updateMobNo( StudentUpdateMobNoDTO studentUpdateMobNoDTO){

        Student student1 = studentrepository.findById(studentUpdateMobNoDTO.getId()).get();

        student1.setMobNo(studentUpdateMobNoDTO.getMobNo());

        studentrepository.save(student1);

        return "mobile number updated successfully.";
    }
}
