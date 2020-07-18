package gr.aueb.mscis.gas.service;
import javax.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Technician;
import gr.aueb.mscis.gas.model.Tool;


import gr.aueb.mscis.gas.persistence.JPAUtil;

/**
 * CRUD operations and other functionality related to crews
 * 
 * @author AUEB.mscispt.team1
 *
 */
public class ToolService {
	private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

	public ToolService(EntityManager em) {
		this.em = em;
	}
	
	public Tool save(Tool tool) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (tool.getId() != null) {
			// beware, always use the result of merge
			tool = em.merge(tool);
		} else {
			em.persist(tool);
		}
		tx.commit();
		return tool;

	}
	
	public boolean deleteTool(Tool t) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		if (t != null) {
			em.remove(t);
			return true;
		}
		tx.commit();
		return false;
	}
	
	public boolean deleteTool(int toolId) {
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Tool tool = em.getReference(Tool.class, toolId);
			em.remove(tool);
		} catch (EntityNotFoundException e) {
			tx.rollback();
			return false;
		}

		tx.commit();

		return true;
	}
	
	
	public void modifyTool(Tool tool ,String name) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		tool.setName(name);
		tx.commit();
		
	}
	
	public Tool createTool(String name){
		//if name of tool is null return null
		if (name == null){
			return null;
		}
		//else create the new tool and add it in database
		Job ajob = new Job("Î•Ï€Î¹ÏƒÎºÎµÏ…Î® ÎºÎ±Î»Î¿Ï�Î¹Ï†Î­Ï�");
		Tool newTool = new Tool(name);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(newTool);
		tx.commit();

		return newTool;
	}
	
}
