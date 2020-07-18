package gr.aueb.mscis.gas.test.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;

import gr.aueb.mscis.gas.model.User;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.UserService;
import junit.framework.Assert;

public class UserServiceTest {

	   private Initializer initializer;

	    @Before
	    public void setUpJpa() {
	    	initializer = new Initializer();
	        initializer.prepareData();
	    }
	    

		
		@SuppressWarnings("deprecation")
		@Test
		public void loginTest() {
			EntityManager em = JPAUtil.getCurrentEntityManager();
			UserService userservice = new UserService(em);
		
			String username="tastsak@gmail.com";
			String password="123456";
			User user=userservice.login(username, password);
			Assert.assertTrue(user.getName()=="Anastasios");
			user.setPassword("123a");
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();

	        em.persist(user);
	        tx.commit();
			userservice.login(username, "123a");
			Assert.assertTrue(user.getName()=="Anastasios");
			userservice.setPassword(user, "123456789a");
			User user2=userservice.login(username, "123456789a");
			Assert.assertTrue(user2.getName()=="Anastasios");

			
			
	}
		
		
		

}
