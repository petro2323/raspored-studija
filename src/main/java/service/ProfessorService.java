package service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import models.Professor;

@Dependent
public class ProfessorService {
	
	@Inject
	private EntityManager em;
	
	@Transactional
	public Professor createProfessor(Professor p) {
		return em.merge(p);
	}
}
