package com.antonio.springmvc.repository;

import com.antonio.springmvc.models.MathGrade;
import org.springframework.data.repository.CrudRepository;

public interface MathGradeDao extends CrudRepository<MathGrade, Integer> {

    public Iterable<MathGrade> findGradeByStudentId(int studentId);
}
