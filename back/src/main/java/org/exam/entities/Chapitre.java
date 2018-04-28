package org.exam.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Chapitre implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private int nbQuestion;

	@OneToMany(mappedBy = "chapitre")
	private Collection<Question> questions;

	@ManyToOne
	@NotFound(action=NotFoundAction.IGNORE)
	private Categorie categorie;

	public Chapitre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chapitre(String libelle, int nbQuestion) {
		super();
		this.libelle = libelle;
		this.nbQuestion = nbQuestion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getNbQuestion() {
		return nbQuestion;
	}

	public void setNbQuestion(int nbQuestion) {
		this.nbQuestion = nbQuestion;
	}

	@JsonIgnore
	public Collection<Question> getQuestions() {
		return questions;
	}

	@JsonSetter
	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}
