package org.purpledev.model;

import jakarta.persistence.*;

import javax.lang.model.element.Name;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SCHOOL")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String city;

    @OneToMany(targetEntity =Student.class)
    private Set<Student> students = new HashSet<>();

    public School(String city, String name) {
        this.city = city;
        this.name = name;
    }

    public School() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
