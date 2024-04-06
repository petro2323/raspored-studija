package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.Associate;

@Dependent
public class AssociateService {

	@Inject
	private EntityManager em;
	
	@Transactional
	public Associate createAssociate(Associate a) {
		return em.merge(a);
	}
}
