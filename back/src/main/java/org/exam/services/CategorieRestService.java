package org.exam.services;

import java.util.Collection;
import java.util.List;

import org.exam.dao.CategorieRepository;
import org.exam.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorie")
public class CategorieRestService {

	private CategorieRepository categorieRepository;

	@Autowired
	public CategorieRestService(CategorieRepository categorieRepository) {

		this.categorieRepository = categorieRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Categorie saveCategorie(@RequestBody Categorie c) {
		return categorieRepository.save(c);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public boolean updateCategorie(@RequestBody Categorie c, @PathVariable Long id) {
		System.out.println(">>> ID "+ c.getId());
		c.setId(id);
		categorieRepository.save(c);
		return true;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean deleteCategorie(@PathVariable Long id) {
		Categorie c = categorieRepository.findById(id).get();
		categorieRepository.delete(c);
		return true;
	}

	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public Categorie getCategorie(@PathVariable Long id) {
		return categorieRepository.findById(id).get();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Collection<Categorie> getCategories() {
		return categorieRepository.findAll();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Page<Categorie> pagesCategories(
			@RequestParam(name = "libelle", defaultValue = "") String libelle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return categorieRepository.getCategories(libelle, new PageRequest(page, size));
	}

}
