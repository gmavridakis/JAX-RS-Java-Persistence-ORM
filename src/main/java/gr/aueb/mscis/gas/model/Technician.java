package gr.aueb.mscis.gas.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Technician")
public class Technician {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "surname", length = 100, nullable = false)
	private String surname;
	
	@Embedded
	private Address address;
	
	@Column(name = "telno", length = 100, nullable = false)
	private String telNo;
	
	@ManyToOne
	@JoinColumn(name="crewId",	nullable = true)
	private Crew crew;
	
	/**
     * Προκαθορισμένος κατασκευαστής.
     */
	public Technician() {}
	
	public Technician(String name, String surname, Address address, String telNo, Crew crew) {

			this.name = name;
			this.surname = surname;
			this.address = address;
			this.telNo = telNo;
			this.crew = crew;

		}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}
	
	

}
