package gr.aueb.mscis.gas.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Technician;

@XmlRootElement
public class TechnicianInfo {
	
	private int id;
	private String name;
	private String surname;
	private Address address;
	private String telNo;
	private Crew crew;
	
	public TechnicianInfo() {}
	
	public TechnicianInfo(String name, String surname, Address address, String telNo, Crew crew) {

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
