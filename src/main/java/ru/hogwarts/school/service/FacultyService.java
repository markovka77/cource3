package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        facultyRepository.deleteAllById(Collections.singleton(id));
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    public  Collection<Faculty> sortByColor(String color) {
        return facultyRepository.findFacultyByColor(color);
    }
    public Collection<Faculty> sortByName(String name){
        return facultyRepository.findByNameIgnoreCase(name);
    }
    public Collection<Faculty>getStudentsOfFaculty(String name){
        return facultyRepository.findFacultiesByStudents(name);
    }


}
