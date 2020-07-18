package gr.aueb.mscis.gas.test.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Technician;

public class TechnicianTest {

	@Test
    public void testAddress() {
        Address technicianAddress = new Address();
        technicianAddress.setStreet("Evelpidon");
		technicianAddress.setNumber("47a");
		technicianAddress.setCity("Athens");
		technicianAddress.setAreaCode("11362");
		technicianAddress.setCountry("Greece");
		Crew testCrew = new Crew();
		Technician testTechnician = new Technician("Nikos", "Vamvakoulas", technicianAddress, "6999000000", testCrew);
        
        Assert.assertEquals(technicianAddress, testTechnician.getAddress());
        
        technicianAddress.setCity("Patra");
        
        Assert.assertTrue(technicianAddress.equals(testTechnician.getAddress()));
        Assert.assertTrue(testTechnician.getAddress().getCity().equals("Patra"));
        
        Address newTechnicianAddress = testTechnician.getAddress();
        Assert.assertSame(newTechnicianAddress, testTechnician.getAddress());
        Assert.assertTrue(newTechnicianAddress.equals(testTechnician.getAddress()) );
        
        Assert.assertTrue(newTechnicianAddress.getCity().equals(testTechnician.getAddress().getCity()) );
        newTechnicianAddress.setCountry("Italy");
        
        Assert.assertTrue(newTechnicianAddress.getCity().equals(testTechnician.getAddress().getCity()) );
        
        
    }
}
