package gr.aueb.mscis.gas.test.model;
import gr.aueb.mscis.gas.model.*;
import org.junit.*;


import org.junit.Test;

public class SupervisorTest {

	@Test
	public void testsupervisor() {
		 Supervisor tasos = new Supervisor("tsakiris", "tasos", "tastsak@gmail.com");
		 Supervisor nikos = new Supervisor("petrou", "nikos", "petrouN@gmail.com");		
		 
		 Assert.assertEquals(tasos.getName(), "tasos");
		 Assert.assertEquals(nikos.getSurname(), "petrou");	 	
		 Assert.assertEquals(tasos.getEmail(),"tastsak@gmail.com");
		 Assert.assertEquals(tasos.getId(), 0);
		 tasos.setId(2);

		 
		 

	}

}
