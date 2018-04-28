package org.exam.dao;

import org.exam.entities.Examen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExamenRepository extends JpaRepository<Examen, Long>  {

	
	@Query("select e from Examen e where e.titre like %:x%")
	public Page<Examen> getPageExamen(@Param("x") String titre, Pageable pageable);
}
