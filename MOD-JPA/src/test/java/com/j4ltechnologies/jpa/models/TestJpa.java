package com.j4ltechnologies.jpa.models;


import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.j4ltechnologies.jpa.dao.TravailleurDao;

public class TestJpa {
	
	private static EntityManagerFactory emf;
	private static TravailleurDao travailleurDao;
	private EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("MOD-JPA");
		travailleurDao = new TravailleurDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@Before
	public void setUp() throws Exception {
		em = emf.createEntityManager();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test() {
		em.getTransaction().begin();;
		em.getTransaction().commit();
	}
	
	
	@Test
	public void testInsert() {
		
		em.getTransaction().begin();
		Travailleur travailleur = new Travailleur();
		travailleur.setDdn(LocalDate.of(1982, 03, 20));
		travailleur.setEmail("joachim@zadi.org");
		travailleur.setTelephone("06-07-08-09-10");
		travailleur.setSiteweb("forumala-one-java.de");
		travailleur.setNom("Zadi");
		travailleur.setPrenom("Joachim");
		
		Geo geo = new Geo("15.31", "95.1");
		
		Adresse adresse = new Adresse();
		
		adresse.setCodePostal("75017");
		adresse.setNum("73");
		adresse.setVoie("BD Bessieres");
		adresse.setVille("Paris");
		
		adresse.setTravailleurs(new ArrayList<>());
		adresse.getTravailleurs().add(travailleur);
		
		geo.setAdresses(new ArrayList<>());
		geo.getAdresses().add(adresse);
		
		em.persist(geo);
		adresse.setGeo(geo);
		em.persist(adresse);
		
		Entreprise entreprise = new Entreprise();
		
		entreprise.setActivity("Informatique");
		entreprise.setNom("j4l");
		entreprise.setSlogan("Je code à la vitesse de Michael Schumacher et je m'en fiche des stagiaires d'Aston");
		entreprise.setTravailleurs(new ArrayList<>());
		entreprise.getTravailleurs().add(travailleur);
		
		em.persist(entreprise);
		
		
		travailleur.setAdresse(adresse);
		travailleur.setEntreprise(entreprise);
		
		em.persist(travailleur);
		
		em.getTransaction().commit();;
		
		
		assertNotNull(travailleur.getId());

		
		
	}
	
	@Test
	public void allTravailleurs() {


		System.out.println("Nous avons " +travailleurDao.allTravailleurs().size() + " travailleurs.\n");

	}
	
	@Test
	public void allGeos() {
		System.out.println("Nous avons " +travailleurDao.allGeos().size() + " Geo.\n");
	}


	@Test
	public void allAdresses() {
		System.out.println("Nous avons " +travailleurDao.allAdresses().size() + " adresses.\n");
	}


	@Test
	public void allEntreprises() {
		System.out.println("Nous avons " +travailleurDao.allEntreprises().size() + " entreprises.\n");
	}

	@Test
	public void addTravailleur(){
		Travailleur joaSenior = new Travailleur();
		joaSenior.setDdn(LocalDate.of(1969, 03, 20));
		joaSenior.setEmail("joachim@zadi.org");
		joaSenior.setTelephone("06-07-08-09-10");
		joaSenior.setSiteweb("forumala-one-java.de");
		joaSenior.setNom("Zadi Java");
		joaSenior.setPrenom("Joachim");

		travailleurDao.create(joaSenior);

		System.out.println("Bienvenue " +joaSenior.getPrenom() + " " + joaSenior.getNom());
	}

	@Test
	public void findTravailleur() {
		System.out.println(" Cet id correspond le travailleur : " +
				travailleurDao.read(1).getPrenom() +
				" " + travailleurDao.read(1).getNom());
	}

	@Test
	public void updateTravailleur(){
		Travailleur joaSenior = new Travailleur();
		joaSenior.setDdn(LocalDate.of(1969, 03, 20));
		joaSenior.setEmail("joachim@zadi.org");
		joaSenior.setTelephone("06-07-08-09-10");
		joaSenior.setSiteweb("forumala-one-java.de");
		joaSenior.setNom("Zadi Java");
		joaSenior.setPrenom("Joachim");

		travailleurDao.create(joaSenior);

		System.out.println("Bienvenue " +joaSenior.getPrenom() + " " + joaSenior.getNom());

		joaSenior.setNom("Zadi C++");
		joaSenior.setPrenom("Joa");

		travailleurDao.update(joaSenior);

		System.out.println("Bienvenue " +joaSenior.getPrenom() + " " + joaSenior.getNom());

	}

	@Test
	public void deleteTravailleur(){

		travailleurDao.delete(1);
		System.out.println("ok.\n");
	}
	
	@Test
	public void findGeo() {
		em.getTransaction().begin();
		Geo geo = new Geo("10.2", "8.8");
		em.persist(geo);
		em.getTransaction().commit();;

		System.out.println(travailleurDao.read(geo).getLat() + " " + travailleurDao.read(geo).getLng());
		
	}

	@Test
	public void findAdresse(){

		em.getTransaction().begin();

		Adresse adresse = new Adresse();

		adresse.setCodePostal("75017");
		adresse.setNum("73");
		adresse.setVoie("BD Bessieres");
		adresse.setVille("Paris");

		em.persist(adresse);

		em.getTransaction().commit();

		System.out.println(travailleurDao.read(adresse).getNum() + ", " + travailleurDao.read(adresse).getVoie() + "\n" +
				travailleurDao.read(adresse).getCodePostal() + " " + travailleurDao.read(adresse).getVille());

	}

	@Test
	public void findEntreprise(){
		em.getTransaction().begin();

		Entreprise entreprise = new Entreprise();

		entreprise.setActivity("Informatique");
		entreprise.setNom("j4l");
		entreprise.setSlogan("Je code à la vitesse de Michael Schumacher et je m'en fiche des stagiaires d'Aston");

		em.persist(entreprise);

		em.getTransaction().commit();

		System.out.println(travailleurDao.read(entreprise).getNom() + "\n" + travailleurDao.read(entreprise).getActivity() + "\n"
		+ travailleurDao.read(entreprise).getSlogan());
	}

}
