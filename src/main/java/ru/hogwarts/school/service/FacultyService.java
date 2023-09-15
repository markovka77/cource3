package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;


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

    public Collection<Faculty> sortByColor(String color) {
        logger.debug("sortByColor is running");
        return facultyRepository.findFacultyByColor(color);
    }

    public Collection<Faculty> sortByName(String name) {
        logger.debug("sortByName is running");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Faculty> getStudentsOfFaculty(String name) {
        logger.debug("getStudentsOfFaculty is running");
        return facultyRepository.findFacultiesByStudents(name);
    }

    public String longestNameOfFaculty() {
        return facultyRepository.findAll().stream()
                .max(Comparator.comparing(f->f.getName().length()))
                .map(Faculty::getName).toString();
    }

    public int sum(){

        return Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }


}
