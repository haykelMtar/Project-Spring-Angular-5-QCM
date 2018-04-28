package org.exam.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Reponse implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean validite;
	private String proposition;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="q_id",nullable=false)
	private Question question;

	public Reponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reponse(boolean validite, String proposition) {
		super();
		this.validite = validite;
		this.proposition = proposition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isValidite() {
		return validite;
	}

	public void setValidite(boolean validite) {
		this.validite = validite;
	}

	public String getProposition() {
		return proposition;
	}

	public void setProposition(String proposition) {
		this.proposition = proposition;
	}

	@JsonIgnore
	public Question getQuestion() {
		return question;
	}

	@JsonSetter
	public void setQuestion(Question question) {
		this.question = question;
	}


}
