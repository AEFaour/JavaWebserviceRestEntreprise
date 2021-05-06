package com.j4ltechnologies.jpa.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.j4ltechnologies.jpa.tools.JpaTool;
import com.j4ltechnologies.jpa.tools.LocalDateAdapter;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@XmlRootElement(name = "travailleur")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Travailleur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NonNull
	private String nom;
	
	@NonNull
	private String prenom;
	
	@NonNull
	@Column( unique = true)
	private String email;
	
	@NonNull
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate ddn;
	
	@Transient
	private Integer age;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Adresse adresse;
	
	@NonNull
	private String telephone;
	
	@NonNull
	private String siteweb;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Entreprise entreprise; 

	public Travailleur() {
		//adresse = new Adresse();
		//entreprise = new Entreprise();
	}
	
	@PrePersist
	@PreUpdate
	@PostLoad
	private void initData() {
		nom = JpaTool.capitalize(nom);
		prenom = JpaTool.capitalize(prenom);
		email = email.trim().toLowerCase();
		age = JpaTool.calculeAge(ddn);
	}
	
	
}
