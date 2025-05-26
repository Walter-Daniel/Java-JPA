package org.purpledev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.purpledev.model.Student;

import java.util.List;

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

    public Student findById(Long id) {
        Query query = entityManager.createNamedQuery("find student by id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }

    public Student updateFirstNameById(String firstname, Long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Update Student set firstname = '" + firstname + "' where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
    }

    public Student updateLastNameById(String lastname, Long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Update Student set lastname = '" + lastname + "' where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
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

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("Delete from Student where id =" + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();

    }

    public void deleteStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    public List<String> findFirstnames(){
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("Select s.firstname from Student s");
        //entityManager.getTransaction().commit();
        return query.getResultList();
    }

    public List<String> findLastnames(){
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("Select s.lastname from Student s");
        //entityManager.getTransaction().commit();

        return query.getResultList();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
