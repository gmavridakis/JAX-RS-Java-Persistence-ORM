package gr.aueb.mscis.gas.service;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.model.User;
import gr.aueb.mscis.gas.model.Supervisor;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.SupervisorService;


import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

public class UserService {

	

		private EntityManager em;  // δημιουργία μεταβλητής Entity Manager

		public UserService(EntityManager em) {
			this.em = em;
		}

		@SuppressWarnings("unchecked")
		public User login(String username, String password) {
			//User user;
			List<User> results = null;

			Query query = em.createQuery("select b from User b where username= :username AND password= :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			results = query.getResultList(); 	
			
			return results.get(0);
		}
		
		public void setUsername(User user, String username) {

			user.setUsername(username);
			
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
			
			
		}
		public void setPassword(User user, String password) {

			user.setPassword(password);
			
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.persist(user);
			tx.commit();
		}
		
		
		
		}