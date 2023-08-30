package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.SchoolApplication;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(classes = SchoolApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int testPort;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    void deleteAll(){
        facultyRepository.deleteAll();
    }



    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void create()  {
        String name = "math";
        String color = "red";
        ResponseEntity<Faculty> response = createFaculty(name, color);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(name);
        assertThat(response.getBody().getColor()).isEqualTo(color);

    }
    @Test
    void update(){
       ResponseEntity<Faculty>response= createFaculty("math", "red");
        Long facultyId= response.getBody().getId();

        restTemplate.put("/faculty/",new Faculty(facultyId,"math","green"));

        response=restTemplate.getForEntity("/faculty/" + facultyId,Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("green");

    }

    @Test
    void getById(){
       ResponseEntity<Faculty>response= createFaculty("math", "red");
        Long facultyId= response.getBody().getId();

        response=restTemplate.getForEntity("/faculty/" + facultyId,Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("red");
        assertThat(response.getBody().getName()).isEqualTo("math");

    }

    @Test
    void getAll(){
       createFaculty("math", "red");
       createFaculty("physics", "blue");
       ResponseEntity<Collection>response=
               restTemplate.getForEntity("/faculty",Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);

    }
    @Test
    void getByColorOrName(){
       createFaculty("math", "red");
       createFaculty("physics", "blue");
       ResponseEntity<Collection>response=
               restTemplate.getForEntity("/faculty/sort?color=blue",Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(1);


    }

    @Test
    void byStudent() {
        ResponseEntity<Faculty> response = createFaculty("math", "red");
        Faculty faculty = response.getBody();
        Student student = new Student();
        student.setId(2);
        student.setName("Piter");
        student.setAge(12);
        student.setFaculty(faculty);

        ResponseEntity<Student>studentResponse =restTemplate.postForEntity("/student",student, Student.class);
        assertThat(studentResponse.getBody()).isEqualTo(student);
        String studentName = studentResponse.getBody().getName();
        restTemplate.getForEntity("/faculty/find?name=Piter)",Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(faculty);

    }



    @Test
    void delete(){
       ResponseEntity<Faculty>response= createFaculty("math", "red");
        Long facultyId= response.getBody().getId();

        restTemplate.delete("/faculty/" + facultyId,Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);


    }



    private ResponseEntity<Faculty> createFaculty(String name,String color) {
        ResponseEntity<Faculty> response =restTemplate.postForEntity("/faculty",
                new Faculty(null, name, color), Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        return response;

    }











}
