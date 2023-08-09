package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.repositories.StudentsRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentsRepository studentsRepository;

    public StudentService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }


    public Student createStudent(Student student) {
       return studentsRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentsRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentsRepository.save(student);
    }

    public void removeStudent(long id) {
         studentsRepository.deleteAllById(Collections.singleton(id));
    }

    public Collection<Student> getAllStudent() {
        return studentsRepository.findAll();
    }

    public Collection<Student> sortByAge(int age) {
        return studentsRepository.findAll().stream()
                .filter(s->s.getAge()==age)
                .collect(Collectors.toList());
    }


}
