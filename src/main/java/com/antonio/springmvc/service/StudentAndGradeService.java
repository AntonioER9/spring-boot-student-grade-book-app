package com.antonio.springmvc.service;

import com.antonio.springmvc.models.CollegeStudent;
import com.antonio.springmvc.models.MathGrade;
import com.antonio.springmvc.repository.MathGradeDao;
import com.antonio.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;

    @Autowired
    private MathGradeDao mathGradeDao;

    public void createStudent(String firstName, String lastName, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstName, lastName, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if (checkIfStudentIsNull(id)) {
            studentDao.deleteById(id);
        }
    }

    public Iterable<CollegeStudent> getGradebook() {
        return studentDao.findAll();
    }

    public boolean createGrade(double grade, int studentId, String gradeType) {

        if(!checkIfStudentIsNull(studentId)) {
            return false;
        }

        if(grade >= 0 && grade <= 100) {
            switch(gradeType) {
                case "math":
                    mathGrade.setId(0);
                    mathGrade.setStudentId(studentId);
                    mathGrade.setGrade(grade);
                    mathGradeDao.save(mathGrade);
                    return true;
                case "science":
                    // logic to create science grade
                    return true;
                case "history":
                    // logic to create history grade
                    return true;
                default:
                    return false;
            }
        }

        return false;
    }
}
