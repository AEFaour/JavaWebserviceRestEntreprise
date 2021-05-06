package com.j4ltechnologies.jpa.dao;

import java.util.List;

import com.j4ltechnologies.jpa.models.*;

public interface IDao {
	
	List<Travailleur> allTravailleurs();
	List<Geo> allGeos();
	List<Adresse> allAdresses();
	List<Entreprise> allEntreprises();
	
	void create(Travailleur travailleur);
	Travailleur read(Integer id);
	void update (Travailleur travailleur);
	void delete(Integer id);
	
	Geo read(Geo geo);
	Adresse read(Adresse adresse);
	Entreprise read(Entreprise entreprise);
}
