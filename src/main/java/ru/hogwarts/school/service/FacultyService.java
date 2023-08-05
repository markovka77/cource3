package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
@Service
public class FacultyService {
    private HashMap<Long, Faculty> facultyHashMap = new HashMap<>();
    private long tmpId = 0L;
    public Faculty createFaculty(Faculty faculty){
        faculty.setId(++tmpId);
        facultyHashMap.put(faculty.getId(), faculty);
        return faculty;
    }
    public Faculty findFaculty(long id){
        return facultyHashMap.get(id);
    }
    public Faculty editFaculty(Faculty faculty){
        facultyHashMap.put(faculty.getId(), faculty);
        return faculty;
    }
    public Faculty removeFaculty(long id){
        return facultyHashMap.remove(id);
    }


}
