package com.antonio.springmvc;

import com.antonio.springmvc.models.CollegeStudent;
import com.antonio.springmvc.repository.StudentDao;
import com.antonio.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute("insert into student(firstname, lastname, email_address) " +
                "values ('Antonio', 'Espinoza', 'antonio@gmail.com')");
    }

    @Test
    public void createStudentService() {
        studentService.createStudent("Antonio", "Espinoza", "antonio@gmail.com");

        CollegeStudent student = studentDao.findByEmailAddress("antonio@gmail.com");

        assertEquals("antonio@gmail.com", student.getEmailAddress(), "find by email");
    }

    @Test
    public void isStudentNullCheck() {
        assertTrue(studentService.checkIfStudentIsNull(1));

        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("delete from student");
        jdbc.execute("alter table student alter column ID restart with 1");
    }

}