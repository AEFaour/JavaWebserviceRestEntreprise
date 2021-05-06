package com.j4ltechnologies.rest;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;


import com.j4ltechnologies.exceptions.ErrorMessage;
import com.j4ltechnologies.exceptions.NotFoundException;
import com.j4ltechnologies.jpa.dao.IDao;
import com.j4ltechnologies.jpa.dao.TravailleurDao;
import com.j4ltechnologies.jpa.models.Travailleur;


@Path("travailleurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TravailleurResource {

	private IDao dao;

	public TravailleurResource() {
		dao = new TravailleurDao();
	}

	@GET
	public List<Travailleur> getTravailleurs() {
		List<Travailleur> travailleurs = dao.allTravailleurs();
		if (travailleurs.size() == 0) {
			ErrorMessage message = new ErrorMessage("404", "Aucun travailleur present en BDD",
					"http://localhost:8080/ws/travailleurs/", Response.Status.NOT_FOUND);
			
			throw new NotFoundException(message);

		}else {
			return travailleurs;
		}
		
	}
	
	@GET
	@Path("{id}")
	public Travailleur read(@PathParam("id") Integer id) {
		
		Travailleur travailleur = dao.read(id);
		if (travailleur == null) {
			ErrorMessage message = new ErrorMessage("404", "Travailleur Introuvable en BDD",
					"http://localhost:8080/ws/travailleurs/" + id, Response.Status.NOT_FOUND);
			
			throw new NotFoundException(message);

		}else {
			return travailleur;
		}
	}
	
	@POST
	public void create(Travailleur travailleur) {
		dao.create(travailleur);
	}
	
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Integer id, Travailleur travailleur) {
		
		Travailleur courant = dao.read(id);
		if (courant == null) {
			ErrorMessage message = new ErrorMessage("404", "Travailleur pour mettre à jour est introuvable",
					"http://localhost:8080/ws/travailleurs/" + id, Response.Status.NOT_FOUND);
			
			throw new NotFoundException(message);

		}else {
			dao.update(travailleur);
		}
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		
		Travailleur courant = dao.read(id);
		if (courant == null) {
			ErrorMessage message = new ErrorMessage("404", "Travailleur pour supprimer est introuvable",
					"http://localhost:8080/ws/travailleurs/" + id, Response.Status.NOT_FOUND);
			
			throw new NotFoundException(message);

		}else {
			dao.delete(id);
		}
	}

}
