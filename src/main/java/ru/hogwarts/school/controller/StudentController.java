package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public Collection<Student> getStudent(){

        return studentService.getAllStudent();
    }

    @GetMapping("/sort")
    public ResponseEntity<Collection<Student>> sortByAge(@RequestParam(required = false) Integer age,
                                         @RequestParam(required = false)Integer min,
                                         @RequestParam(required = false)Integer max) {
        if( min != null || max != null){
            return ResponseEntity.ok(studentService.findStudentByAgeBetween(min, max));
        }

        return ResponseEntity.ok( studentService.sortByAge(age));
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student studentCheck = studentService.editStudent(student);
        if (studentCheck == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable long id) {
        studentService.removeStudent(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/findByFaculty")
    public Collection<Student>findByStudentByFaculty(String name){
        return studentService.getFacultyId(name);
    }
}
