package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.debug("createStudent is running");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.debug("findStudent is running");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.debug("editStudent is running");
        return studentRepository.save(student);
    }

    public void removeStudent(long id) {
        logger.debug("removeStudent is running");
        studentRepository.deleteAllById(Collections.singleton(id));
    }

    public Collection<Student> getAllStudent() {
        logger.debug("getAllStudent is running");
        return studentRepository.findAll();
    }

    public Collection<Student> sortByAge(int age) {
        logger.debug("sortByAge is running");
        return studentRepository.findStudentByAge(age);
    }

    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        logger.debug("findStudentByAgeBetween is running");
        return studentRepository.findByAgeBetween(min, max);
    }
    public Collection<Student> getFacultyId(String name){
        logger.debug("getFacultyId is running");

        return studentRepository.findStudentByFaculty(name);
    }

    public int getCountOfStudents(){
        logger.debug("getCountOfStudents is running");
        return  studentRepository.getCountOfStudents();
    }

    public int getAvgAgeOfStudents() {
        logger.debug("getAvgAgeOfStudents is running");
        return studentRepository.getAvgAgeOfStudents();
    }

    public Collection<Student> getFiveLastStudents(){
        logger.debug("getFiveLastStudents is running");
        return studentRepository.getFiveLastStudents();
    }


}
