package jfaerman.flyfloyd.data;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import jfaerman.flyfloyd.model.Cidade;

@Singleton
@Startup
public class GeradorDeDados {
	@Inject EntityManager em;
	
	@PostConstruct
	public void postConstruct(){
		criaEntidades();
	}

	private void criaEntidades() {
		em.persist(new Cidade("Uberlândia"));
		em.persist(new Cidade("Uberaba"));
		em.persist(new Cidade("Araguari"));
		em.persist(new Cidade("Araxá"));
		em.persist(new Cidade("Patos de Minas"));

		


	}
}
