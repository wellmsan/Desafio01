package br.ufba.fpc.desafio01.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.ufba.fpc.desafio01.utils.ModelBase;

@Entity(name = "destinatarios")
public class Destinatario extends ModelBase {
	@Column(length = 255, nullable = false)
	private String email;
	@ManyToOne
	@JoinColumn(name = "mensagem_id")
	private Mensagem mensagem;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Mensagem getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
