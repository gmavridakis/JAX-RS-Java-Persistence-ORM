package gr.aueb.mscis.gas.test.model;
import gr.aueb.mscis.gas.model.*;
import org.junit.*;


import org.junit.Test;

public class SimpleUserTest {

	@Test
	public void testSimpleUser() {
		 SimpleUser tasos = new SimpleUser("tsakiris", "tasos", "tastsak@gmail.com");
		 SimpleUser nikos = new SimpleUser("petrou", "nikos", "petrouN@gmail.com");		
		 
		 Assert.assertEquals(tasos.getName(), "tasos");
		 Assert.assertEquals(nikos.getSurname(), "petrou");	 	
		 Assert.assertEquals(tasos.getEmail(),"tastsak@gmail.com");
		 Assert.assertEquals(tasos.getId(), 0);
		 tasos.setId(2);
		 Assert.assertNotEquals(tasos.getId(), 0);
		 


	}

}
