package org.exam.services;


import java.util.Collection;


import org.exam.dao.ExamenRepository;
import org.exam.entities.Examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/examen")
@CrossOrigin("*")
public class ExamenRestService {
	
	
	private ExamenRepository examenRepository ;

	@Autowired
	public ExamenRestService(ExamenRepository examenRepository) {
		this.examenRepository = examenRepository;
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Examen saveExam(@RequestBody Examen e) {
		return examenRepository.save(e);
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Collection<Examen> getExames(){
		return examenRepository.findAll();
	}
	
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	public Examen getExamen(@PathVariable Long id) {
		return examenRepository.findById(id).get();
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public Page<Examen> chercherExam(
			@RequestParam(name="titre",defaultValue="") String titre,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size){
		return examenRepository.getPageExamen(titre, new PageRequest(page, size));
		
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public boolean updateExamen(@PathVariable Long id, @RequestBody Examen e) {
		e.setId(id);
		examenRepository.save(e);
		return true;
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean deleteExamen(@PathVariable Long id) {
		Examen e =examenRepository.findById(id).get();
		examenRepository.delete(e);
		return true;
	}
	
	
	

}
