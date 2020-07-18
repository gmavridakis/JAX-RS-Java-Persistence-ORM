package gr.aueb.mscis.gas.test.model;
import org.junit.Assert;
import org.junit.Test;

import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Tool;


public class ToolTest {
	@Test
	public void testTool() {
		String tname = "katsavidi";
		Tool tool1 = new Tool (tname);
		
		 Assert.assertEquals(tool1.getName(), "katsavidi");
	}
}
