package org.purpledev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.purpledev.model.Student;

public class StudentRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public StudentRepository() {
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public Student addStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }

    public Student findStudent(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student updateStudent(Student student){
        Student studentToUpdate = findStudent(student.getId());
        entityManager.getTransaction().begin();

        studentToUpdate.setFirstname(student.getFirstname());
        studentToUpdate.setLastname(student.getLastname());
        studentToUpdate.setBirthday(student.getBirthday());

        entityManager.getTransaction().commit();
        return studentToUpdate;
    }

    public void deleteStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
