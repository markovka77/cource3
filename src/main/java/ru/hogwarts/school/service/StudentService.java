package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void removeStudent(long id) {
        studentRepository.deleteAllById(Collections.singleton(id));
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Collection<Student> sortByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
    public Collection<Student> getFacultyId(String name){
        return studentRepository.findStudentByFaculty(name);
    }

    public int getCountOfStudents(){
       return  studentRepository.getCountOfStudents();
    }

    public int getAvgAgeOfStudents(){
        return studentRepository.getAvgAgeOfStudents();
    }

    public Collection<Student> getFiveLastStudents(){
        return studentRepository.getFiveLastStudents();
    }


}
