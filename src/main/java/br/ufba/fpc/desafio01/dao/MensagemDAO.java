package br.ufba.fpc.desafio01.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import br.ufba.fpc.desafio01.models.Mensagem;
import br.ufba.fpc.desafio01.utils.DaoAbstract;

public class MensagemDAO extends DaoAbstract<Mensagem> {
	
	private static MensagemDAO instance;
	
	public static synchronized MensagemDAO getInstance () {
		if(instance == null)
			instance = new MensagemDAO();
		return instance;
	}

	@Override
	protected EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DesafioPU");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
			setClazz(Mensagem.class);
		}
		return entityManager;
	}
	
	@Override
	public List<Mensagem> getAll() {
		Session session = (Session) getEntityManager().getDelegate();
		return session.createCriteria(Mensagem.class).addOrder(Order.desc("dataEnvio")).list();
	}
	
	@Override
	public List<Mensagem> getAllBy(Mensagem t) {
		Session session = (Session) getEntityManager().getDelegate();
		Example example = Example.create(t).enableLike().ignoreCase();		
		return session.createCriteria(Mensagem.class).add(example).addOrder(Order.desc("dataEnvio")).list();
	}

}
