package com.antonio.springmvc.repository;

import com.antonio.springmvc.models.HistoryGrade;
import org.springframework.data.repository.CrudRepository;

public interface HistoryGradesDao extends CrudRepository<HistoryGrade, Integer> {

    public Iterable<HistoryGrade> findGradeByStudentId(int id);

    public void deleteByStudentId(int id);
}
