package org.exam.services;

import org.exam.dao.QuestionRepository;
import org.exam.entities.Examen;
import org.exam.entities.Question;
import org.exam.entities.Reponse;
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
@RequestMapping(value="/question")
public class QuestionRestService {
	
	private QuestionRepository questionRepository;

	@Autowired
	public QuestionRestService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Question saveQuestion(@RequestBody Question q) {
		//q.setId(id);
//		System.out.println(q.getReponses().get(0).getId());
//		q.getReponses().get(0).setQuestion(q);
		for (Reponse reponse : q.getReponses()) {
			reponse.setQuestion(q);
		}
		return questionRepository.save(q);
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public boolean updateQuestion(@RequestBody Question q,@PathVariable Long id) {
		q.setId(id);
		questionRepository.save(q);
		return true ;
	}
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public boolean deleteQuestion(@PathVariable Long id) {
		Question q= questionRepository.findById(id).get();
		questionRepository.delete(q);
		return true ;
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public Page<Question> chercherQuestion(
			@RequestParam(name="enonce",defaultValue="") String enonce,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size){
		return questionRepository.getPageQuestions(enonce, new PageRequest(page, size));
		
	}


}
