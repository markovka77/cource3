package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> facultyHashMap = new HashMap<>();
    private long tmpId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++tmpId);
        facultyHashMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyHashMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        facultyHashMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty removeFaculty(long id) {
        return facultyHashMap.remove(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return facultyHashMap.values();
    }

    public Collection<Faculty> sortByColor(String color) {
        return facultyHashMap.values().stream()
                .filter(f -> f.getColor().equals(color))
                .collect(Collectors.toList());
    }


}
