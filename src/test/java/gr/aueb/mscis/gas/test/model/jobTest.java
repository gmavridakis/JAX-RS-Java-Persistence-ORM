package gr.aueb.mscis.gas.test.model;
import gr.aueb.mscis.gas.model.*;
import sun.util.calendar.Gregorian;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class jobTest {

	  @Test
	    public void setJobToSupervisor() {
	        Supervisor supervisor = new Supervisor("tsakiris", "anastasios", "tastsak@gmail.com");
	        
	        Assert.assertTrue(supervisor.getJobs().size() == 0 );
	        
	        Job job1 = new Job("Επισκευή καλοριφέρ");
	        
	       job1.setSupervisor(supervisor);
	       Assert.assertEquals(job1.getSupervisor().getSurname(), "tsakiris");
	       
	        
	        Assert.assertTrue(supervisor.getJobs().size() == 1);
	        Assert.assertTrue(supervisor.getJobs().contains(job1));
	        
	        Job job2 = new Job("Επισκευή κλιματιστικού");
	        job2.setSupervisor(supervisor);
	        
	        Assert.assertEquals(job2.getSupervisor().getSurname(), "tsakiris");
	        Assert.assertTrue(supervisor.getJobs().size() == 2);
	        Assert.assertTrue(supervisor.getJobs().contains(job1));
	        Assert.assertTrue(supervisor.getJobs().contains(job2));
	        Job job3= new Job();
	        job3.setSupervisor(supervisor);
	        Assert.assertTrue(supervisor.getJobs().contains(job3));	 
	        
	        Supervisor allos = new Supervisor();
	        Job job4=new Job(allos, "Συντήρηση κ. Δημήτρη");
	        Assert.assertEquals(job4.getSupervisor(), allos);
	        
	        
	      
	    }

	  
	  @Test
	    public void checkPendingTasks() {
	        Supervisor supervisor = new Supervisor("tsakiris", "anastasios", "tastsak@gmail.com");
	        Assert.assertTrue(supervisor.getJobs().size() == 0 );
	        
	        Job job1 = new Job("Επισκευή καλοριφέρ");
	        
	       job1.setSupervisor(supervisor);
	       Assert.assertEquals(job1.getSupervisor().getSurname(), "tsakiris");
	       
	        
	        Assert.assertTrue(supervisor.getJobs().size() == 1);
	        Assert.assertTrue(supervisor.getJobs().contains(job1));
	        
	        Job job2 = new Job("Επισκευή κλιματιστικού");
	        job2.setSupervisor(supervisor);
	        
	        Assert.assertEquals(job2.getSupervisor().getSurname(), "tsakiris");
	        Assert.assertTrue(supervisor.getJobs().size() == 2);
	        Assert.assertTrue(supervisor.getJobs().contains(job1));
	        Assert.assertTrue(supervisor.getJobs().contains(job2));	       
	        Assert.assertTrue(job1.getFinalDate()==null);
	        Assert.assertTrue(supervisor.getPendingJobs() == 2 ); 
	        
	        
	        // Ορίζω σε μία εργασία την ημερομηνία ολοκλήρωσης που σημαίνει ότι δεν είναι πια pending αλλά έχει ολοκληρωθεί 
	        GregorianCalendar date0= new GregorianCalendar ();
	        date0.set(GregorianCalendar.YEAR, 2018);
	        date0.set(GregorianCalendar.MONTH, 2);
	        date0.set(GregorianCalendar.DATE, 28);
	        job2.setFinalDate(date0);
	        Assert.assertTrue(job2.getFinalDate().get(1)==2018);
	        Assert.assertTrue(job2.getFinalDate().get(2)==2);
	        
	        //Ο supervisor Θα πρέπει από 2 να έχει 1 Pending καθώς η μία έχει ολοκληρωθεί
	        Assert.assertTrue(supervisor.getPendingJobs() == 1 ); 
	        
	        Assert.assertEquals(job1.getFinalStart(), null);
	        GregorianCalendar date1= new GregorianCalendar ();
	        date1.set(GregorianCalendar.YEAR, 2018);
	        date1.set(GregorianCalendar.MONTH, 3);
	        date1.set(GregorianCalendar.DATE, 2);

	        
	        job1.setFinalStart(date1);
	        Assert.assertEquals(job1.getFinalStart(), date1);	        
	        
	        Assert.assertEquals(job1.getExpectedEnd(), null);
	        GregorianCalendar date5= new GregorianCalendar ();
	        date5.set(GregorianCalendar.YEAR, 2018);
	        date5.set(GregorianCalendar.MONTH, 3);
	        date5.set(GregorianCalendar.DATE, 3);
	        job1.setExpectedEnd(date5);
	        Assert.assertEquals(job1.getExpectedEnd(), date5);
	        
	       

	        
	        
	        Assert.assertEquals(job1.getExpectedStart(), null);
	        GregorianCalendar date2= new GregorianCalendar ();
	        date2.set(GregorianCalendar.YEAR, 2018);
	        date2.set(GregorianCalendar.MONTH, 3);
	        date2.set(GregorianCalendar.DATE, 4);
	        
	        job1.setExpectedStart(date2);
	       
	        Assert.assertEquals(job1.getExpectedStart(), date2);
	        Assert.assertTrue(job1.getExpectedStart().get(1)==2018);
	        Assert.assertTrue(job1.getExpectedStart().get(2)==3);
	        
	        
	      //κόστος εργασίας  
	        Assert.assertTrue(job1.getJobCost()==0.00);
	        job1.setJobCost(25.01);
	        Assert.assertTrue(job1.getJobCost()== 25.01);
	        
	        

	  
	        Crew crew =new Crew();
	        Request request=new Request();
	        Tool tool=new Tool();
	        
	        job1.setCrew(crew);
	        Crew dokcrew=job1.getCrew();
	        Assert.assertEquals(dokcrew, crew);

	        

	        
	  

      //αντιλογίζουμε μία εργασία
      Assert.assertTrue(job1.getJobtype()==1);	        
      job1.setAntilogismenh();
      int x=job1.getAntiloghsmenh();
      Assert.assertTrue(x== 0);
  }
	  
	  
	  @Test
	    public void setJobTools() {
		  Job job1=new Job();
		  
	      Tool tool1=new Tool("trypani");
	      Tool tool2=new Tool("trypani2");
	      Assert.assertTrue(job1.getTools().size() == 0);
	      job1.addTool(tool1);
	      Assert.assertTrue(job1.getTools().size() == 1);
	      job1.addTool(tool2);
	      Assert.assertTrue(job1.getTools().size() == 2);
	      job1.removeTool(tool2);
	      Assert.assertTrue(job1.getTools().size() == 1);
	      Assert.assertTrue(job1.getTools().contains(tool1));
	    }
	  
	  @Test
	    public void setCrew() {
		  Job job1=new Job();
		  
		  Address address1=new Address("dagklh", "4", "kavala", "65302", "Ellada");
				  
		  
	      Crew crew1=new Crew("sunergeio1", address1);
	      job1.setCrew(crew1);
	      
	      Assert.assertEquals(job1.getCrew(), crew1);
	    }
  
	  
}
