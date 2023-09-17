package ru.hogwarts.school.service;

import net.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import ru.hogwarts.school.exception.BadRequestException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyService {
   private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
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
        return facultyRepository.findById(id).orElseThrow(BadRequestException::new);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("editFaculty is running");
        Optional<Faculty> test = facultyRepository.findById(faculty.getId());
        if(test.isPresent()){
            facultyRepository.save(faculty);
        }else throw new BadRequestException("no faculty with this ID");
        return faculty;
    }

    public void removeFaculty(long id) {
        logger.debug("removeFaculty is running");
        Optional<Faculty> test = facultyRepository.findById(id);
        if(test.isPresent()){
            facultyRepository.deleteById(id);
        }else throw new BadRequestException("no faculty with this ID");

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
                .max(Comparator.comparing(f -> f.getName().length()))
                .map(Faculty::getName)
                .orElseThrow(BadRequestException::new);
    }

    public void sum(){
        long time1 = System.currentTimeMillis();
        int sum = Stream.iterate(1, a -> a +1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b );
        long time = System.currentTimeMillis()-time1;
        logger.info("completed in "+time);


        long time2 = System.currentTimeMillis();
         int sum1 =IntStream.rangeClosed(1,1_000_000)
                .reduce(0, Integer::sum);
        long time3 = System.currentTimeMillis()-time2;
        logger.info("completed in "+time3);
    }







}
