package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public Collection<Faculty> getAllFaculty() {
        return facultyService.getAllFaculty();
    }

    @GetMapping("/sort")
    public ResponseEntity< Collection<Faculty>> sortByColorOrName(@RequestParam(required = false)String name,
                                                            @RequestParam(required = false)String color) {
        if( name != null && !name.isBlank()){
            return ResponseEntity.ok(facultyService.sortByName(name));
        }

        return ResponseEntity.ok(facultyService.sortByColor(color));


    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty facultyCheck = facultyService.editFaculty(faculty);
        if (facultyCheck == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> removeFaculty(@PathVariable long id) {
        facultyService.removeFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public Collection<Faculty>findByFaculty(String name){
        return facultyService.getStudentsOfFaculty(name);
    }

    @GetMapping("/longestName")
    public String longestNameOfFaculty(){
        return facultyService.longestNameOfFaculty();
    }

    @GetMapping("/sum")
    public int sum (){
        return facultyService.sum();
    }

}
