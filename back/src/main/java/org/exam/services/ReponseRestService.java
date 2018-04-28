package org.exam.services;

import org.exam.dao.ReponseRepository;
import org.exam.entities.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/reponse")
public class ReponseRestService {

	private ReponseRepository reponseRepository;

	@Autowired
	public ReponseRestService(ReponseRepository reponseRepository) {
		super();
		this.reponseRepository = reponseRepository;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Reponse saveReponse(@RequestBody Reponse r) {
		return reponseRepository.save(r);
	}
}
