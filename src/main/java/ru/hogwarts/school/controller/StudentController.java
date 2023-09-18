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
import java.util.List;

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

    @GetMapping("/count")
    public int getCountOfStudents(){
        return studentService.getCountOfStudents();
    }

    @GetMapping("/avgAge")
    public int getAvgAgeOfStudent(){
        return studentService.getAvgAgeOfStudents();
    }

    @GetMapping("/lastStudents")
    public Collection<Student> getLastFiveStudents(){
        return studentService.getFiveLastStudents();
    }


    @GetMapping("/studentNameStartWith{l}")
    public Collection<Student>studentNameStartWith(@PathVariable String l){
        return studentService.studentNameStartWith(l);
    }

    @GetMapping("/avgAgeOfStudents")
    public double avgAgeOfStudents(){
        return studentService.avgAgeStudent();
    }

    @GetMapping("/getFromThreads")
    public void getStudentFromThreads(){
        studentService.getStudentsFromTread();
    }

    @GetMapping("/getFromSyncTreads")
    public void getStudentFromSyncTreads(){
        studentService.getStudentsWithSyncTread();
    }


}
