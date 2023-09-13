package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("createFaculty is running");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.debug("findFaculty is running");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("editFaculty is running");
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        logger.debug("removeFaculty is running");
        facultyRepository.deleteAllById(id);
    }

    public Collection<Faculty> getAllFaculty() {
        logger.debug("getAllFaculty is running");
        return facultyRepository.findAll();
    }

    public  Collection<Faculty> sortByColor(String color) {
        logger.debug("sortByColor is running");
        return facultyRepository.findFacultyByColor(color);
    }
    public Collection<Faculty> sortByName(String name){
        logger.debug("sortByName is running");
        return facultyRepository.findByNameIgnoreCase(name);
    }
    public Collection<Faculty>getStudentsOfFaculty(String name){
        logger.debug("getStudentsOfFaculty is running");
        return facultyRepository.findFacultiesByStudents(name);
    }


// 2 и 3 доделать

}
