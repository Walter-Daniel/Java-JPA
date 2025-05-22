package org.purpledev;

import org.purpledev.model.Student;

import java.text.SimpleDateFormat;
import java.util.Date;

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

           System.out.println("Added student: " + student.toString());
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }
}