package ru.hogwarts.school.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findStudentByAge(int age);

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student>findStudentByFaculty (String name);

    Student getById(Long id);

    @Query(value = "select count(*)from student",nativeQuery = true)
    int getCountOfStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    int getAvgAgeOfStudents();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    Collection<Student> getFiveLastStudents();


}
