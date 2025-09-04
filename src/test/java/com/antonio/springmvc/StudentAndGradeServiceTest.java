package com.antonio.springmvc;

import com.antonio.springmvc.models.CollegeStudent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Test
    public void createStudentService() {
        studentService.createStudent("Antonio", "Espinoza", "antonio@gmail.com");

//        CollegeStudent student = studentDao.findByEmailAddress("antonio@gmail.com");

//        assertEquals("antonio@gmail.com", student.getEmailAddress(), "find by email");
    }
}
