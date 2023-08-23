package ru.hogwarts.school.service;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.internal.builders.NullBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

        @LocalServerPort
        private int testPort;

        @Autowired
        private StudentController studentController;

        @Autowired
        private TestRestTemplate restTemplate;


    Student student = new Student(228,"andy",23);


    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    public void getStudentTest()throws Exception{
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort +"/"+ "student", String.class))
                .isNotNull();
    }

   @Test
    public void createStudentTest()throws Exception{
        Student student = new Student();
        student.setName("Nikolay");
        student.setAge(23);
       Assertions
               .assertThat(this.restTemplate.postForObject("http://localhost:" + testPort + "/" + "student",student ,String.class))
               .contains("Nikolay");
    }

    @Test
    public void getStudentByIdTest(){
        Long id= student.getId();

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/" + "student" + "/" + id, String.class))
                .isNotNull();


    }
    @Test
    public void sortByAge()throws Exception{
        Student student = new Student(228,"andy",50);
        studentController.createStudent(student);
        int age = 50;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/" + "student" + "/" + "sort?age=" + age, Object.class))
                .isNotNull();
    }






}
