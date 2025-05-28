package org.purpledev.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.purpledev.model.Tutor;

public class TutorRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TutorRepository() {
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public TutorRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
    }

    public Tutor addTutor(Tutor tutor){
        entityManager.getTransaction().begin();
        entityManager.persist(tutor);
        entityManager.getTransaction().commit();
        return tutor;
    }

    public Tutor find(Long id){
        return entityManager.find(Tutor.class, id);
    }

    public Tutor updateTutor(Tutor tutor){
        Tutor tutorToUpdate = find(tutor.getId());
        entityManager.getTransaction().begin();

        tutorToUpdate.setFirstname(tutor.getFirstname());
        tutorToUpdate.setLastname(tutor.getLastname());

        entityManager.getTransaction().commit();
        return tutorToUpdate;
    }

    public void deleteTutor(Tutor tutor){
        entityManager.getTransaction().begin();
        entityManager.remove(tutor);
        entityManager.getTransaction().commit();
    }

    public void close(){
        this.entityManager.close();
        this.emf.close();
    }
}
