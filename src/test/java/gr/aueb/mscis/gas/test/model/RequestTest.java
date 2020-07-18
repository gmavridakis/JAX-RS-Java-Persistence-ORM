package gr.aueb.mscis.gas.test.model;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;

public class RequestTest {
	
		@Test
	    public void testAddress() {
	        Address requestAddress = new Address();
	        requestAddress.setStreet("Evelpidon");
	        requestAddress.setNumber("47a");
	        requestAddress.setCity("Athens");
	        requestAddress.setAreaCode("11362");
	        requestAddress.setCountry("Greece");
			Crew testCrew = new Crew("testCrew", requestAddress);
	        
	        Assert.assertEquals(requestAddress, testCrew.getAddress());
	        
	        requestAddress.setCity("Patra");
	        
	        Assert.assertTrue(requestAddress.equals(testCrew.getAddress()));
	        Assert.assertTrue(testCrew.getAddress().getCity().equals("Patra"));
	        
	        Address newCrewAddress = testCrew.getAddress();
	        Assert.assertSame(newCrewAddress, testCrew.getAddress());
	        Assert.assertTrue(newCrewAddress.equals(testCrew.getAddress()) );
	        
	        Assert.assertTrue(newCrewAddress.getCity().equals(testCrew.getAddress().getCity()) );
	        newCrewAddress.setCountry("Italy");
	        
	        Assert.assertTrue(newCrewAddress.getCity().equals(testCrew.getAddress().getCity()) );
	        
	        
	    }
}
