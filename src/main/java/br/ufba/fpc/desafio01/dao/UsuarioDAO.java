package br.ufba.fpc.desafio01.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.ufba.fpc.desafio01.models.Usuario;
import br.ufba.fpc.desafio01.utils.DaoAbstract;

public class UsuarioDAO extends DaoAbstract<Usuario> {

	private static UsuarioDAO instance;

	public static synchronized UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();			
		}
		return instance;
	}

	@Override
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DesafioPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
			setClazz(Usuario.class);
		}
		return entityManager;
	}

}
