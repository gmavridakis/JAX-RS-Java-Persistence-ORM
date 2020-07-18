package gr.aueb.mscis.gas.test.model;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;


public class CrewTest {
	
		@Test
	    public void testAddress() {
	        Address crewAddress = new Address();
	        crewAddress.setStreet("Evelpidon");
			crewAddress.setNumber("47a");
			crewAddress.setCity("Athens");
			crewAddress.setAreaCode("11362");
			crewAddress.setCountry("Greece");
			Crew testCrew = new Crew("testCrew", crewAddress);
	        
	        Assert.assertEquals(crewAddress, testCrew.getAddress());
	        
	        crewAddress.setCity("Patra");
	        
	        Assert.assertTrue(crewAddress.equals(testCrew.getAddress()));
	        Assert.assertTrue(testCrew.getAddress().getCity().equals("Patra"));
	        
	        Address newCrewAddress = testCrew.getAddress();
	        Assert.assertSame(newCrewAddress, testCrew.getAddress());
	        Assert.assertTrue(newCrewAddress.equals(testCrew.getAddress()) );
	        
	        Assert.assertTrue(newCrewAddress.getCity().equals(testCrew.getAddress().getCity()) );
	        newCrewAddress.setCountry("Italy");
	        
	        Assert.assertTrue(newCrewAddress.getCity().equals(testCrew.getAddress().getCity()) );
	        
	        
	    }
}
