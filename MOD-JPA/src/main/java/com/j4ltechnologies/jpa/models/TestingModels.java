package com.j4ltechnologies.jpa.models;

//import javax.persistence.Persistence;

public class TestingModels {

	public static void main(String[] args) {

		// Persistence.generateSchema("MOD-JPA", null);

		Geo geo = new Geo();
		geo.setLat("10");
		geo.setLng("20");
		System.out.println(geo.toString());

		Adresse adresse = new Adresse();
		adresse.setGeo(geo);

		System.out.println(adresse.getGeo());

	}

}
