package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerWithMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private AvatarService avatarService;

    @MockBean
    private FacultyService facultyService;

    @SpyBean
    private StudentService studentService;
    @SpyBean
    private AvatarController avatarController;
    @InjectMocks
    private StudentController studentController;

    @Test
    public void studentSaveAndGetByIdTest() throws Exception {

        final String name = "Garry";
        final int age = 14;
        final long id = 1;
        JSONObject studentObject = new JSONObject();
        studentObject.put(name, age);


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById((any(Long.class)))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));


    }

    @Test
    public void studentSortByAgeAndBetweenAgeTest() throws Exception {

        final String name = "Garry";
        final int age = 14;
        final long id = 1;
        JSONObject studentObject = new JSONObject();
        studentObject.put(name, age);


        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);

        final Collection<Student> testCollection = new ArrayList<>(6);
        testCollection.add(student);

        when(studentRepository.findStudentByAge(any(int.class))).thenReturn(testCollection);
        when(studentRepository.findByAgeBetween(any(int.class), any(int.class))).thenReturn(testCollection);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/sort?age=" + age)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/sort?min=" + age + "&max=" + age + 2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
