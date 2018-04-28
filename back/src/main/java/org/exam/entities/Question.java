package org.exam.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String enonce;
	private String image;
	@ManyToOne
	private Chapitre chapitre;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@Size(min=2, max=4)
	private Collection<Reponse> reponses;

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(String enonce, String image) {
		super();
		this.enonce = enonce;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Chapitre getChapitre() {
		return chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}

//	@JsonIgnore
	public Collection<Reponse> getReponses() {
		return reponses;
	}

	@JsonSetter
	public void setReponses(Collection<Reponse> reponses) {
		this.reponses = reponses;
	}

}
