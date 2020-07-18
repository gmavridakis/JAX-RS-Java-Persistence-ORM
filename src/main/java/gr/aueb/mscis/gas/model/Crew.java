package gr.aueb.mscis.gas.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Crew")

public class Crew {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "crewName", length = 500, nullable = false)
	private String crewName;
	
	@Embedded
	private Address address;
	
	/**
     * Προκαθορισμένος κατασκευαστής.
     */
	public Crew() {}
	
	public Crew(String crewName, Address address) {

			this.crewName = crewName;
			this.address = address;

		}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address adsress) {
		this.address = address;
	}

	public Integer getId() {
		return this.id;
	}
	
	public String getCrewName() {
		return crewName;
	}

	public void setCrewName(String crewName) {
		this.crewName = crewName;
	}
	
}
