package br.ufba.fpc.desafio01.services;

import java.util.Date;
import java.util.List;

import br.ufba.fpc.desafio01.dao.MensagemDAO;
import br.ufba.fpc.desafio01.models.Mensagem;
import br.ufba.fpc.desafio01.utils.ServiceInterface;

public class MensagemService implements ServiceInterface<Mensagem>{
	
	private static MensagemService instance;
	
	private MensagemService() {}
	
	public static synchronized MensagemService getInstance () {
		if(instance == null)
			instance = new MensagemService();
		return instance;
	}	
			
	public Mensagem getById(Long id) {
		return MensagemDAO.getInstance().getById(id);
	}
	
	public List<Mensagem> getAll() {
		return MensagemDAO.getInstance().getAll();
	}
	
	public List<Mensagem> getAllBy(Mensagem t) {
		return MensagemDAO.getInstance().getAllBy(t);
	} 
	
	public void save(Mensagem t) {
		t.setDataEnvio(new Date());
		t.setPath(Mensagem.SENT);
		MensagemDAO.getInstance().save(t);
	}
	
	public void update(Mensagem t) {
		MensagemDAO.getInstance().update(t);
	}
	
	public void remove(Mensagem t) {
		MensagemDAO.getInstance().remove(t);
	}
	
}
