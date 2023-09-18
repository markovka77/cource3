package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private StudentRepository studentRepository;

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

        String info1 = allStudents.get(0) + " ; " + allStudents.get(1);
        logger.info(info1);

        Thread thread1 = new Thread(() -> {
            String info2 = allStudents.get(2) + " ; " + allStudents.get(3);
            logger.info(info2);
        });
        Thread thread2 = new Thread(() -> {
            String info3 = allStudents.get(4) + " ; " + allStudents.get(5);
            logger.info(info3);
        });

        thread1.start();
        thread2.start();


    }
    static private int count = 0;


    private synchronized String printStudent() {
        List<Student> allStudents = studentRepository.findAll();
        String st= String.valueOf(allStudents.get(count));
        count++;
        return st;
    }

    public void getStudentsWithSyncTread() {
    logger.info(printStudent());
    logger.info(printStudent());

        Thread thread1 = new Thread(() -> {
            logger.info(printStudent());
            logger.info(printStudent());


        });
        Thread thread2 = new Thread(() -> {
            logger.info(printStudent());
            logger.info(printStudent());

        });

        thread1.start();
        thread2.start();
    }


}
