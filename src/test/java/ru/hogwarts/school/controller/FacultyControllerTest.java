package ru.hogwarts.school.controller;

import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int testPort;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;


    Faculty faculty = new Faculty(1, "Math", "Green");



    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void createFaculty() throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/" + "faculty", String.class))
                .isNotNull();
    }

    @Test
    public void editFacultyTest()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/faculty", String.class))
                .isNotNull();
    }

    @Test
    public void findFacultyById()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/faculty" + faculty.getId(), String.class))
                .isNotNull();
    }

    @Test
    public void sortByColor() throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/faculty"+"/" +"sort?color="+ faculty.getColor(), String.class))
                .isNotEmpty();

    }

    @Test
    public void sortByName() throws Exception{

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/faculty/sort?color="+ faculty.getName(), String.class))
                .isNotEmpty();

    }




}
