package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

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

    public Collection<Student> getFacultyId(String name) {
        logger.debug("getFacultyId is running");

        return studentRepository.findStudentByFaculty(name);
    }

    public int getCountOfStudents() {
        logger.debug("getCountOfStudents is running");
        return studentRepository.getCountOfStudents();
    }

    public int getAvgAgeOfStudents() {
        logger.debug("getAvgAgeOfStudents is running");
        return studentRepository.getAvgAgeOfStudents();
    }

    public Collection<Student> getFiveLastStudents() {
        logger.debug("getFiveLastStudents is running");
        return studentRepository.getFiveLastStudents();
    }

    public List<Student> studentNameStartWith(String l) {

        return getAllStudent().stream()
                .filter(student -> student.getName().toUpperCase().startsWith(String.valueOf(l)))
                .sorted()
                .toList();

    }

    public double avgAgeStudent() {
        double avgAge = getAllStudent().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(RuntimeException::new);
        return avgAge;

    }


    public void getStudentsFromTread() {
        List<Student> allStudents = studentRepository.findAll();
        System.out.println(allStudents.get(0));
        System.out.println(allStudents.get(1));
        Thread thread1 = new Thread(() -> {
            System.out.println(allStudents.get(2));
            System.out.println(allStudents.get(3));
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(allStudents.get(4));
            System.out.println(allStudents.get(5));
        });

        thread1.start();
        thread2.start();


    }


    private synchronized void printStudent(Student student) {
        System.out.println(student);
    }

    public void getStudentsWithSyncTread() {
        List<Student> allStudents = studentRepository.findAll();
        printStudent(allStudents.get(0));
        printStudent(allStudents.get(1));
        Thread thread1 = new Thread(() -> {
            printStudent(allStudents.get(2));
            printStudent(allStudents.get(3));
        });
        Thread thread2 = new Thread(() -> {
            printStudent(allStudents.get(4));
            printStudent(allStudents.get(5));
        });

        thread1.start();
        thread2.start();
    }


}
