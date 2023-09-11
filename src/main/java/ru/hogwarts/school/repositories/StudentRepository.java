package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findStudentByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student>findStudentByFaculty (String name);

    Student getById(Long id);
}