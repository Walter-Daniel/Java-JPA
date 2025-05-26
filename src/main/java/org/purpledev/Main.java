package org.purpledev;

import org.purpledev.model.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       try{
           SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
           Date birthday = sdf.parse("28-06-1998");

           Student student = new Student();
           student.setFirstname("Adrian");
           student.setLastname("Lopez");
           student.setBirthday(birthday);

           StudentRepository repository = new StudentRepository();
           repository.addStudent(student);

           student = repository.findById(student.getId());
           System.out.println("Found student (JPQL" + student.toString());

       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }
}