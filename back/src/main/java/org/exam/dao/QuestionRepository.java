package org.exam.dao;


import org.exam.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	
	@Query("select q from Question q where q.enonce like %:x%")
	public Page<Question>getPageQuestions(@Param("x") String enonce ,Pageable pageable);

}
