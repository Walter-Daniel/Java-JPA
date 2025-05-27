package org.purpledev;

import org.purpledev.model.Student;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date birthday = sdf.parse("28-06-1998");

            Student student = new Student();
            student.setFirstname("Adrian");
            student.setLastname("Lopez");
            student.setBirthday(birthday);

            StudentRepository repository = new StudentRepository();
            repository.addStudent(student);

            student = repository.findById(student.getId());
            System.out.println(student.getId());
            System.out.println("Estudiante encontrado " + student.toString());

            student = repository.updateFirstNameById("Exequiel", student.getId());
            System.out.println("Update firstname JQPL: " + student.toString());

            student = repository.updateLastNameById("Carranza", student.getId());
            System.out.println("Update lastname JQPL: " + student.toString());

            List<Student> students = repository.findByFirstNameStartWith("le");
            students.forEach(System.out::println);

            students = repository.findByLastNameEndWith("lo");
            students.forEach(System.out::println);

            System.out.println("Cantidad de estudiantes: " + repository.count());

            students = repository.findSortingByFirstName();
            students.forEach(System.out::println);

            repository.deleteById(student.getId());
            repository.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}