package br.ufba.fpc.desafio01.services;

import java.util.List;

import br.ufba.fpc.desafio01.dao.MensagemDAO;
import br.ufba.fpc.desafio01.dao.UsuarioDAO;
import br.ufba.fpc.desafio01.models.Mensagem;
import br.ufba.fpc.desafio01.models.Usuario;
import br.ufba.fpc.desafio01.utils.ServiceInterface;

public class UsuarioService implements ServiceInterface<Usuario>{

	private static UsuarioService instance;
	
	private UsuarioService() {}
	
	public static synchronized UsuarioService getInstance () {
		if(instance == null)
			instance = new UsuarioService();
		return instance;
	}	
	
	public Usuario getById(Long id) {
		return UsuarioDAO.getInstance().getById(id);
	}
	
	public List<Usuario> getAll() {
		return UsuarioDAO.getInstance().getAll();
	}
		
	public void save(Usuario t) {
		UsuarioDAO.getInstance().save(t);
	}
	
	public void update(Usuario t) {
		UsuarioDAO.getInstance().update(t);
	}
	
	public void remove(Usuario t) {
		UsuarioDAO.getInstance().remove(t);
	}	
	
}
