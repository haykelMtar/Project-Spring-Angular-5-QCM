package org.exam.dao;

import org.exam.entities.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
	@Query("select c from Categorie c where c.libelle like %:x%")
	public Page<Categorie> getCategories (@Param("x") String titre, Pageable pageable);

}
