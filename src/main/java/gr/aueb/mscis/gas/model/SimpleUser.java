package gr.aueb.mscis.gas.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("N")
public class SimpleUser extends User {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private int userid;


		
	public SimpleUser() {}
		
	public SimpleUser(String surname, String name, String email) {

			super(surname, name, email);

		}
		
	//public void setId(int id) {
	//	this.userid = id;
	//}
	
	//public int getId() {
	//	return this.userid;
	//}
	
}


		
