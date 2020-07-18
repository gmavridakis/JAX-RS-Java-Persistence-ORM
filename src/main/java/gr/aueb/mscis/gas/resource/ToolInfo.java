package gr.aueb.mscis.gas.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import gr.aueb.mscis.gas.model.Tool;

@XmlRootElement
public class ToolInfo {
	
		private Integer id;
		private String name;


		public ToolInfo() {

		}
		public ToolInfo(Tool t) {
			this.name=t.getName();
		}
		
		public ToolInfo(String name) {
			this.name = name;

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public static ToolInfo wrap(Tool t) {
			return new ToolInfo(t);
		}

		public static List<ToolInfo> wrap(List<Tool> tools) {

			List<ToolInfo> toolInfoList = new ArrayList<>();

			for (Tool t : tools) {
				toolInfoList.add(new ToolInfo(t));
			}
			
			return toolInfoList;
		}
		public Tool getTool(EntityManager em) {

			Tool tool = null;
			if (id != null) {
				tool = em.find(Tool.class, id);
			} else {
				tool = new Tool();
			}
			
			tool.setName(name);
			
			return tool;
		}
}
