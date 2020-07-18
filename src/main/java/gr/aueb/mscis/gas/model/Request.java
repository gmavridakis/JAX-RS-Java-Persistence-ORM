package gr.aueb.mscis.gas.model;

import javax.persistence.*;

@Entity
@Table(name = "Request")

public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "surname", length = 100, nullable = false)
	private String surname;

	@Column(name = "name", length = 100, nullable = true)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="job")
    private Job job;
	
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	@Embedded
	private Address address;
	
	
	public Request() {
	}
	
	public Request(String surname, String name, Address address) {
		this.surname = surname;
		this.name = name;
		this.address = address;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

}