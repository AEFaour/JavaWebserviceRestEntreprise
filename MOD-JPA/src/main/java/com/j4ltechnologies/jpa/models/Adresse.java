package com.j4ltechnologies.jpa.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.j4ltechnologies.jpa.tools.JpaTool;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlRootElement(name = "adresse")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String voie;
	private String num;
	private String ville;
	private String codePostal;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	private Geo geo;
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL)
	private List<Travailleur> travailleurs = new ArrayList<>();

	public Adresse() {
		geo = new Geo();
	}
	
	@PrePersist
	@PreUpdate
	@PostLoad
	private void initData() {
		voie= JpaTool.capitalize(voie);
		num = JpaTool.capitalize(num);
		ville = JpaTool.allCapitalize(ville);
	}
	

}
