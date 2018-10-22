package br.ufba.fpc.desafio01.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufba.fpc.desafio01.utils.ModelBase;

@Entity(name = "mensagens")
public class Mensagem extends ModelBase {
	public static final String INBOX = "1";
	public static final String SENT = "2";
	public static final String DRAFT = "3";
	public static final String TRASH = "4";

	@OneToOne(cascade = CascadeType.MERGE)
	private Usuario remetente;
	@OneToMany(mappedBy = "mensagem", fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private List<Destinatario> destinaratios;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataEnvio;
	@Column(length = 240, nullable = false)
	private String assunto;
	@Column(nullable = false, columnDefinition = "clob")
	private String mensagem;
	@Column(length = 1, nullable = false)
	private String path;
	@OneToOne(cascade = CascadeType.MERGE)
	private Mensagem mensagemPai;

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public List<Destinatario> getDestinaratios() {
		return destinaratios;
	}
	
	public void setDestinaratios(List<Destinatario> destinaratios) {
		this.destinaratios = destinaratios;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public Mensagem getMensagemPai() {
		return mensagemPai;
	}

	public void setMensagemPai(Mensagem mensagemPai) {
		this.mensagemPai = mensagemPai;
	}

}
