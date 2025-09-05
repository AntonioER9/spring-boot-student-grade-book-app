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
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void deleteStudentService() {
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return True");

        studentService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return False");
    }

    @Sql("/insertData.sql")
    @Test
    public void getGradebookService() {
        Iterable<CollegeStudent> iterableCollegeStudents = studentService.getGradebook();

        List<CollegeStudent> collegeStudents = new ArrayList<>();

        for (CollegeStudent collegeStudent : iterableCollegeStudents) {
            collegeStudents.add(collegeStudent);
        }

        assertEquals(5, collegeStudents.size());
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("delete from student");
        jdbc.execute("alter table student alter column ID restart with 1");
    }

}