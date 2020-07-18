package gr.aueb.mscis.gas.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "type",
		discriminatorType = DiscriminatorType.STRING
		)
public abstract class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "username", length = 100, nullable = false)
	private String username;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "surname", length = 100, nullable = false)
	private String surname;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "email", length = 512, nullable = false)
	private String email;
	
	//@Column(name = "type", length = 512, nullable = false)
	//private String type;
	
	
	public User() {}

	
	
	public User(String surname, String name, String email) {
		//super();
		this.username=email;
		this.password="123456";
		this.surname = surname;
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPasswordl() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getUsernamel() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	//tautopoihsh 
	
	public boolean checkAuthentication(String username, String password){
		if ((this.username==username)&&(this.password==password))
			return true;
		else 
			return false;
	}
	
}