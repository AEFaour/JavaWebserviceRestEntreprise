package com.j4ltechnologies.jpa.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.j4ltechnologies.jpa.models.Adresse;
import com.j4ltechnologies.jpa.models.Entreprise;
import com.j4ltechnologies.jpa.models.Geo;
import com.j4ltechnologies.jpa.models.Travailleur;

public class TravailleurDao implements IDao {

	private final EntityManagerFactory entityManagerFactory;

	public TravailleurDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("MOD-JPA");
	}

	protected EntityManager getEntityFactory() {
		return entityManagerFactory.createEntityManager();
	}

	@Override
	public List<Travailleur> allTravailleurs() {

		TypedQuery<Travailleur> requette = this.getEntityFactory().createQuery("select t from Travailleur t",
				Travailleur.class);

		return requette.getResultList();
	}

	@Override
	public List<Geo> allGeos() {

		TypedQuery<Geo> requette = this.getEntityFactory().createQuery("select g from Geo g", Geo.class);

		return requette.getResultList();
	}

	@Override
	public List<Adresse> allAdresses() {

		TypedQuery<Adresse> requette = this.getEntityFactory().createQuery("select a from Adresse a", Adresse.class);

		return requette.getResultList();
	}

	@Override
	public List<Entreprise> allEntreprises() {

		TypedQuery<Entreprise> requette = this.getEntityFactory().createQuery("select e from Entreprise e", Entreprise.class);

		return requette.getResultList();
	}

	@Override
	public void create(Travailleur travailleur) {
		
		if(!allTravailleurs().contains(travailleur)) {
			this.getEntityFactory().persist(travailleur);
		}

	}

	@Override
	public Travailleur read(Integer id) {

		return this.getEntityFactory().find(Travailleur.class, id);
	}

	@Override
	public void update(Travailleur travailleur) {
		List<Travailleur> travailleurs = this.allTravailleurs();
		if(travailleurs.contains(travailleur)) {
			this.getEntityFactory().merge(travailleur);
		}

	}

	@Override
	public void delete(Integer id) {
		Travailleur travailleurForDelete = this.getEntityFactory().find(Travailleur.class, id);
		if(travailleurForDelete != null) {
			this.getEntityFactory().remove(this.getEntityFactory().merge(travailleurForDelete));
		}

	}

	@Override
	public Geo read(Geo geo) {

		return this.getEntityFactory().find(Geo.class, geo.getId());
	}

	@Override
	public Adresse read(Adresse adresse) {

		return this.getEntityFactory().find(Adresse.class, adresse.getId());
	}

	@Override
	public Entreprise read(Entreprise entreprise) {

		return this.getEntityFactory().find(Entreprise.class, entreprise.getId());
	}

}
