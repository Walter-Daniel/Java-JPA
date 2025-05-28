package org.purpledev;

import org.purpledev.model.School;
import org.purpledev.model.Student;
import org.purpledev.model.Tutor;
import org.purpledev.repository.SchoolRepository;
import org.purpledev.repository.StudentRepository;
import org.purpledev.repository.TutorRepository;

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

            //REPOSITORIES
            StudentRepository repository = new StudentRepository();
            SchoolRepository schoolRepository = new SchoolRepository();
            TutorRepository tutorRepository = new TutorRepository();

            //ADD STUDENT
            repository.addStudent(student);

            //ADD TUTOR
            Tutor tutor = new Tutor();
            tutor.setFirstname("María");
            tutor.setLastname("Valenzuela");

            tutorRepository.addTutor(tutor);
            System.out.println("Se agregó tutor: " + tutor.toString());

            repository.addTutor(student.getId(), tutor);
            System.out.println("Estudiante y su tutor: " + student.toString());

            //ADD SCHOOL
            School school = new School("Tucumán", "Campos de las carreras");
            schoolRepository.addSchool(school);
            System.out.println("Se agregó Escuela: " + school.toString());

            schoolRepository.addStudent(school.getId(), student);
            school = schoolRepository.find(school.getId());
            school.getStudents().forEach(System.out::println);

            //Persistence operations and JPQL
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

            //repository.deleteById(student.getId());
            repository.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}