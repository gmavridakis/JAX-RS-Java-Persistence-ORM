package gr.aueb.mscis.gas.model;
import javax.persistence.*;

@Entity
@Table(name="tools")

public class Tool {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//mono onoma ergaleioy theloyme
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	public Tool() {}
	
	public Tool(String name){
		this.name=name;
		
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public Integer getId() {
		return this.id;
	}
}