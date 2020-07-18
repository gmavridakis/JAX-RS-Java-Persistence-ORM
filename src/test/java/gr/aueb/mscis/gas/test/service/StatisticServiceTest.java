package gr.aueb.mscis.gas.test.service;

import javax.persistence.EntityManager;
import gr.aueb.mscis.gas.model.Job;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.persistence.Initializer;
import gr.aueb.mscis.gas.persistence.JPAUtil;
import gr.aueb.mscis.gas.service.StatisticService;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StatisticServiceTest {
	
	protected EntityManager em;
	
	@Before
	public void setup(){
	em = JPAUtil.getCurrentEntityManager();
	Initializer dataHelper = new Initializer();
	}
	
	@After
	public void tearDown(){
		em.close();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void TestfindAllCrewJobs()
	{
	StatisticService service = new StatisticService(em);
	
	Address crewAddress1 = new Address("Evelpidwn","35","Athens","11362","Greece");
	Assert.assertNotEquals(crewAddress1.getStreet(), "35");
	Address crewAddress2 = new Address("Patisiwn","10","Athens","11362","Greece");
	Assert.assertEquals(crewAddress2.getCountry(), "Greece");
	Crew dreamteam = new Crew("dreamteam", crewAddress1);
	Assert.assertEquals(dreamteam.getCrewName(), "dreamteam");
	Crew ghostbuster = new Crew("ghostbuster", crewAddress2);
	Assert.assertEquals(ghostbuster.getCrewName(), "ghostbuster");
	
	Job job1 = new Job("episkevi kalorifer1");
	Assert.assertEquals(job1.getJobName(), "episkevi kalorifer1");
	Job job2 = new Job("episkevi kalorifer2");
	Assert.assertEquals(job2.getJobName(), "episkevi kalorifer2");
	Job job3 = new Job("episkevi kalorifer3");
	Assert.assertEquals(job3.getJobName(), "episkevi kalorifer3");
	
	GregorianCalendar hmeromhnia1=new GregorianCalendar(2018,03,07);
	GregorianCalendar hmeromhnia2=new GregorianCalendar(2018,03,02);
	GregorianCalendar hmeromhnia3=new GregorianCalendar(2018,03,01);
	GregorianCalendar hmeromhnia4=null;
	
	job1.setFinalDate(hmeromhnia1);
	Assert.assertEquals(job1.getFinalDate(), hmeromhnia1);
	job1.setFinalDate(hmeromhnia2);
	Assert.assertEquals(job1.getFinalDate(), hmeromhnia2);
	job2.setFinalDate(hmeromhnia3);
	Assert.assertEquals(job2.getFinalDate(), hmeromhnia3);
	job2.setFinalDate(hmeromhnia2);
	Assert.assertEquals(job2.getFinalDate(), hmeromhnia2);
	job3.setFinalDate(hmeromhnia4);
	Assert.assertEquals(job3.getFinalDate(), null);
	
	job1.setCrew(dreamteam);
	Assert.assertEquals(job1.getCrew(), dreamteam);
	job2.setCrew(dreamteam);
	Assert.assertEquals(job2.getCrew(), dreamteam);
	job3.setCrew(ghostbuster);
	Assert.assertEquals(job3.getCrew(), ghostbuster);
	

	em.persist(job1);
	em.persist(job2);
	em.persist(job3);
	
	job1.setJobCost(100.00);
	job2.setJobCost(100.00);
	job1.setCrew(dreamteam);
	job1.setCrew(dreamteam);
	List<Job> alljobs = new ArrayList();
	alljobs.add(job1);
	alljobs.add(job2);
	Assert.assertEquals(alljobs.size(),2);
	
	float testavg1=0;
	testavg1=service.AverageCostStatistic(dreamteam,alljobs);
	
	Assert.assertEquals(testavg1, 100 , 0);
    
	List<Job> res = service.findAllCrewJobs("dreamteam");
	
	}
}