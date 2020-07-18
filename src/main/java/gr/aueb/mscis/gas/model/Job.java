package gr.aueb.mscis.gas.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import sun.util.calendar.BaseCalendar.Date;


@Entity
@Table(name = "job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int jobid;

	public int getJobid() {
		return jobid;
	}


	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	@Column(name = "jobname", length = 100, nullable = false)
	private String jobname;
	
	@Column(name = "expectedstart", length = 100, nullable = true)
	private GregorianCalendar expectedstart = new GregorianCalendar();

	@Column(name = "expectedend", length = 100, nullable = true)
	private GregorianCalendar expectedend = new GregorianCalendar();
	
	@Column(name = "finalstart", length = 100, nullable = true)
	private GregorianCalendar finalstart = new GregorianCalendar();
	
	@Column(name = "finaldate", length = 100, nullable = true)
	private GregorianCalendar finaldate = new GregorianCalendar();

	@Column(name = "jobcost", length = 100, nullable = true)
	private double jobcost;
	
	@Column(name = "jobtype", length = 100, nullable = false)
	private int jobtype;
	
	@Column(name = "antiloghsmenh", length = 100, nullable = true)
	private int antiloghsmenh;
		
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="supervisor")
    private Supervisor supervisor;
	
    
	@ManyToOne (fetch=FetchType.LAZY)
    @JoinColumn(name="crew")
    private Crew crew;
	
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}) 
    //mappedBy="job", fetch=FetchType.LAZY)
    private Set<Tool> tools = new HashSet<Tool>();
	
	public Job() {}
	
    
	public Job(Supervisor supervisor, String jobname){
		this.supervisor=supervisor;
		this.jobname=jobname;
		this.expectedstart=null;
		this.expectedend=null;
		this.finalstart=null;
		this.finaldate=null;
		this.jobcost=0.00;
		this.jobtype=1;
		this.antiloghsmenh=1;
		
	}
	
	public Job(String jobname){
		this.jobname=jobname;
		this.expectedstart=null;
		this.expectedend=null;
		this.finalstart=null;
		this.finaldate=null;
		this.jobcost=0.00;
		this.jobtype=1;
		this.antiloghsmenh=1;
		
	}

	public void setJobCost(double amount) {
		this.jobcost=amount;
		
	}
	
	public double getJobCost() {
		return this.jobcost;
		
	}
	public void setJobName(String name) {
		this.jobname=name;
		
	}
	public String getJobName() {
		return this.jobname;
	}

	public void setExpectedStart(GregorianCalendar a) {	
		this.expectedstart=a;
	}
	
	public GregorianCalendar getExpectedStart() {	
		return this.expectedstart;
	}	

	
	public void setExpectedEnd(GregorianCalendar a)  {	
		this.expectedend=a;
	}
	
	public GregorianCalendar getExpectedEnd() {	
		return this.expectedend;
	}	
	
	
	
	
	public void setFinalStart(GregorianCalendar a)  {	
		this.finalstart=a;
	}
	
	public GregorianCalendar getFinalStart() {	
		return this.finalstart;
	}	
	
	

	public GregorianCalendar getFinalDate() {	
		return this.finaldate;
	}	
	
	public void setFinalDate(GregorianCalendar a)  {	
		this.finaldate=a;
	}
	
    public void setSupervisor(Supervisor supervisor) {
        if (this.supervisor != null) {
            this.supervisor.friendJobs().remove(this);
        }
        this.supervisor = supervisor;
        if (supervisor != null) {
            this.supervisor.friendJobs().add(this);
        }
    }
    
    public Supervisor getSupervisor() {
        return supervisor;
    }
    
    public void setAntilogismenh()  {
    	
		this.antiloghsmenh=0;
	}


	public int getJobtype() {
		return jobtype;
	}


	public void setJobtype(int jobtype) {
		this.jobtype = jobtype;
	}


	public int getAntiloghsmenh() {
		return antiloghsmenh;
	}


	public void setAntiloghsmenh(int antiloghsmenh) {
		this.antiloghsmenh = antiloghsmenh;
	}
	


	public Crew getCrew() {
		return crew;
	}


	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	
	 public Set<Tool> getTools() {
	        return new HashSet<Tool>(tools);
	 }
	 
	 
	 Set<Tool> friendTool() {
	        return tools;
	    }
	 
	 public void addTool(Tool tool) {
		 if (tool != null ) { tools.add(tool); }
		 }
		 public void removeTool(Tool tool) {
		 if (tool != null) {
			 tools.remove(tool);
		 }
	 }
}