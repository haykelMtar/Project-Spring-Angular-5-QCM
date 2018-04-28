package org.exam.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Categorie  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String libelle;
	private String description;

	@ManyToOne
	@JoinColumn(name = "CODE_CATEG_SUP")
	@NotFound(action=NotFoundAction.IGNORE)
	private Categorie categSup;

	@OneToMany(mappedBy = "categorie",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Collection<Examen> examens;

	@OneToMany(mappedBy = "categorie")
	private Collection<Chapitre> chapitres;

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categorie(String libelle, String description) {
		super();
		this.libelle = libelle;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	//@JsonIgnore
	public Categorie getCategSup() {
		return categSup;
	}

	@JsonSetter
	public void setCategSup(Categorie categSup) {
		this.categSup = categSup;
	}

	@JsonIgnore
	public Collection<Examen> getExamens() {
		return examens;
	}

	@JsonSetter
	public void setExamens(Collection<Examen> examens) {
		this.examens = examens;
	}

	@JsonIgnore
	public Collection<Chapitre> getChapitres() {
		return chapitres;
	}

	@JsonSetter
	public void setChapitres(Collection<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

}
