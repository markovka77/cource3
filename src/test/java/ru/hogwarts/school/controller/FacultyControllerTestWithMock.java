package ru.hogwarts.school.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerTestWithMock {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentService studentService;
    @MockBean
    private AvatarService avatarService;

    @MockBean
    private FacultyService facultyService;

    @SpyBean
    private AvatarController avatarController;
    @InjectMocks
    private FacultyController facultyController;



    @Test
    public void createFacultyTest() throws Exception {
        final String name = "PhisMath";
        final String color = "Blue";
        final long id = 3;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, color);

        final Faculty faculty = new Faculty();
        faculty.setName("PhisMath");
        faculty.setColor("Blue");
        faculty.setId(3);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void findFacultyByColorTest() throws Exception {
        final String name = "PhisMath";
        final String color = "Blue";
        final Long id = 3L;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, color);

        final Faculty faculty = new Faculty();
        faculty.setName("PhisMath");
        faculty.setColor("Blue");
        faculty.setId(3);
        when(facultyRepository.findFacultyByColor(any(String.class))).thenReturn(Collections.singleton(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/sort?color=" + color)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void editFacultyTest() throws Exception {
        final String name = "PhisMath";
        final String color = "Blue";
        final Long id = 3L;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, color);

        final Faculty faculty = new Faculty();
        faculty.setName("PhisMath");
        faculty.setColor("Green");
        faculty.setId(3);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void findFacultyByIdTest() throws Exception {
        final String name = "PhisMath";
        final String color = "Blue";
        final Long id = 3L;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, color);

        final Faculty faculty = new Faculty();
        faculty.setName("PhisMath");
        faculty.setColor("Green");
        faculty.setId(3);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/sort?id=" + id)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
    @Test
    public void getAllFacultyTest() throws Exception {
        final String name = "PhisMath";
        final String color = "Blue";
        final Long id = 3L;

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, color);

        final Faculty faculty = new Faculty();
        faculty.setName("PhisMath");
        faculty.setColor("Green");
        faculty.setId(3);
        when(facultyRepository.findAll()).thenReturn(List.of(faculty));


                mockMvc.perform(MockMvcRequestBuilders
                                .get("/faculty")
                                .content(facultyObject.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

    }




}
