package org.exam.dao;

import org.exam.entities.Chapitre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChapitreRepository extends JpaRepository<Chapitre, Long> {
	
	@Query("Select c from Chapitre c where c.libelle like %:x%")
	public Page<Chapitre>getChapitres(@Param("x") String libelle,Pageable pageable);

}
