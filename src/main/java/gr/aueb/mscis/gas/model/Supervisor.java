package gr.aueb.mscis.gas.model;

import java.util.Calendar;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("S")
public class Supervisor extends User {
	

	
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
            mappedBy="supervisor", fetch=FetchType.LAZY)
   private Set<Job> jobs = new HashSet<Job>();
	
		
	public Supervisor() {}
		
	public Supervisor(String surname, String name, String email) {
			super(surname, name, email);
		}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	/*
    * Επιστέφει τη συλλογή των εργασιών του επιβλέπων.
    * Η συλλογή είναι αντίγραφο της συλλογής των εργασιών.
    * @return Η συλλογή των εργασιών
    */
	 public Set<Job> getJobs() {
	        return new HashSet<Job>(jobs);
	 }
	 
	 
	 Set<Job> friendJobs() {
	        return jobs;
	    }
	
	    /**
	     * Επιστρέφει τον αριθμό των pending εργασιών του επιβλέποντος.
	     * Είναι ο αριθμός εργασιών που έχει αναλάβει ο επιβλέπων 
	     * @return Ο αριθμός των pending εργασιών που επιβλέπει που δεν έχουν επιστραφεί.
	     */
	 public int getPendingJobs() {
	    int pendingJobs = 0;
	    for (Job job : jobs) {
	        Calendar finaldate=job.getFinalDate();
	        if (finaldate==null)
	        	pendingJobs++;
	        }
	    
	    return pendingJobs;
	 }	
	
}
		
