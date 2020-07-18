package gr.aueb.mscis.gas.resource;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Application;

import gr.aueb.mscis.gas.persistence.JPAUtil;

public class AbstractResource {

	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}

}