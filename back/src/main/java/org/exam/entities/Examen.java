package org.exam.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Examen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titre;
	private String duree;
	private int nbQuestion;
	private int nbChapitre;
	private Date date;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "EXAMEN_USERS")
	private List<User> users;

	@ManyToOne
	private Categorie categorie;
	
	
	public Examen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Examen(String titre, String duree, int nbQuestion, int nbChapitre, Date date) {
		super();
		this.titre = titre;
		this.duree = duree;
		this.nbQuestion = nbQuestion;
		this.nbChapitre = nbChapitre;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public int getNbQuestion() {
		return nbQuestion;
	}

	public void setNbQuestion(int nbQuestion) {
		this.nbQuestion = nbQuestion;
	}

	public int getNbChapitre() {
		return nbChapitre;
	}

	public void setNbChapitre(int nbChapitre) {
		this.nbChapitre = nbChapitre;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}
	@JsonSetter
	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	public Categorie getCategorie() {
		return categorie;
	}
	
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	
	
}
