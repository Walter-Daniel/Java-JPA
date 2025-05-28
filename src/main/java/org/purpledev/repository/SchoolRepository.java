package org.purpledev.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.purpledev.model.School;
import org.purpledev.model.Student;

public class SchoolRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public SchoolRepository() {
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public SchoolRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
    }

    public School addSchool(School school){
        entityManager.getTransaction().begin();
        entityManager.persist(school);
        entityManager.getTransaction().commit();
        return  school;
    }

    public School find(Long id){
        return entityManager.find(School.class, id);
    }

    public School findById(Long id) {
        return entityManager.find(School.class, id);
    }

    public School updateSchool(School school){
        School schoolToUpdate = find(school.getId());
        entityManager.getTransaction().begin();

        schoolToUpdate.setName(school.getName());
        schoolToUpdate.setCity(school.getCity());

        entityManager.getTransaction().commit();
        return schoolToUpdate;
    }

    public void deleteSchool(School school){
        entityManager.getTransaction().begin();
        entityManager.remove(school);
        entityManager.getTransaction().commit();
    }

    public void addStudent(Long id, Student student){
        entityManager.getTransaction().begin();
        School school = find(id);
        if(school!= null) {
            school.getStudents().add(student);
        }
        entityManager.persist(school);
        entityManager.getTransaction().commit();
    }

    public void removeStudent(Long id, Student student) {
        entityManager.getTransaction().begin();
        School school = find(id);
        if(school!= null) {
            school.getStudents().remove(student);
        }
        entityManager.persist(school);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
