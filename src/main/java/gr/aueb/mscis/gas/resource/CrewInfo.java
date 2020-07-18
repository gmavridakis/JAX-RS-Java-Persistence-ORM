package gr.aueb.mscis.gas.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.gas.model.Address;
import gr.aueb.mscis.gas.model.Crew;
import gr.aueb.mscis.gas.model.Tool;

@XmlRootElement
public class CrewInfo {
	
		private Integer id;
		private String name;
		private Address address;


		public CrewInfo() {

		}
		public CrewInfo(Crew crew) {
			this.name=crew.getCrewName();
			this.address=crew.getAddress();
		}
		
		public CrewInfo(String name,Address address) {
			this.name = name;
			this.address=address;

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public static CrewInfo wrap(Crew crew) {
			return new CrewInfo(crew);
		}

		public static List<CrewInfo> wrap(List<Crew> crew) {

			List<CrewInfo> crewInfoList = new ArrayList<>();

			for (Crew c : crew) {
				crewInfoList.add(new CrewInfo(c));
			}
			
			return crewInfoList;
		}
		public Crew getCrew(EntityManager em) {

			Crew crew = null;
			if (id != null) {
				crew = em.find(Crew.class, id);
			} else {
				crew = new Crew();
			}
			
			crew.setCrewName(name);
			
			return crew;
		}	
}
