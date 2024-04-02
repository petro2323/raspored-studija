package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.Saradnik;

@Dependent
public class SaradnikService {

	@Inject
	private EntityManager em;
	
	@Transactional
	public Saradnik createAssociate(Saradnik s) {
		return em.merge(s);
	}
}
