package org.exam.services;

import java.util.List;

import org.exam.dao.ChapitreRepository;
import org.exam.entities.Chapitre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chapitre")
public class ChapitreRestService {

	private ChapitreRepository chapitreRepository;

	@Autowired
	public ChapitreRestService(ChapitreRepository chapitreRepository) {
		this.chapitreRepository = chapitreRepository;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Chapitre saveChapitre(@RequestBody Chapitre c) {
		return chapitreRepository.save(c);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public boolean updateChapitre(@RequestBody Chapitre c, @PathVariable Long id) {
		c.setId(id);
		chapitreRepository.save(c);
		return true;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean deleteChapitre(@PathVariable Long id) {
		Chapitre c = chapitreRepository.findById(id).get();
		chapitreRepository.delete(c);
		return true;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Chapitre> getAllChapitres() {
		return chapitreRepository.findAll();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Page<Chapitre> getChapitres(
			@RequestParam(name = "libelle", defaultValue = "") String libelle,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		return chapitreRepository.getChapitres(libelle, new PageRequest(page, size));

	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Chapitre getChapitre(@PathVariable Long id) {
		return chapitreRepository.findById(id).get();
	}

}
