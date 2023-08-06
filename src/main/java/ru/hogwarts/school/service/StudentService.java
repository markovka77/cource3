package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> studentHashMap = new HashMap<>();
    long tmpId = 0;

    public Student createStudent(Student student) {
        student.setId(++tmpId);
        studentHashMap.put(student.getId(), student);
        return student;
    }

    public Student findStudent(long id) {
        return studentHashMap.get(id);
    }

    public Student editStudent(Student student) {
        if (studentHashMap.containsKey(student.getId())) {
            studentHashMap.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student removeStudent(long id) {
        return studentHashMap.remove(id);
    }

    public Collection<Student> getAllStudent() {
        return studentHashMap.values();
    }

    public Collection<Student> sortByAge(int age) {
        return studentHashMap.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }


}
