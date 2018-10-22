package br.ufba.fpc.desafio01.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.ufba.fpc.desafio01.models.Destinatario;
import br.ufba.fpc.desafio01.models.Mensagem;
import br.ufba.fpc.desafio01.services.MensagemService;

@RequestScoped
@ManagedBean
public class MensagemBean {

	private Mensagem mensagem = new Mensagem();
	private Mensagem mensagemPesquisa;
	private Mensagem mensagemSelecionda;
	private String destinatario;
	private String pesquisaTitulo;
	private String pesquisaPor;

	public Mensagem getBean() {
		return mensagem;
	}

	public List<Mensagem> getAll() {
		
		List<Mensagem> lista = null;
		
		if(mensagemPesquisa == null) {
			lista = MensagemService.getInstance().getAll();
		} else {
			lista = MensagemService.getInstance().getAllBy(mensagemPesquisa);
		}
		
		return lista;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	public String getPesquisaTitulo() {
		return pesquisaTitulo;
	}
	
	public void setPesquisaTitulo(String pesquisaTitulo) {
		this.pesquisaTitulo = pesquisaTitulo;
	}
	
	public String getPesquisaPor() {
		return pesquisaPor;
	}
	
	public void setPesquisaPor(String pesquisaPor) {
		this.pesquisaPor = pesquisaPor;
	}
	
	public Mensagem getMensagemSelecionda() {
		return mensagemSelecionda;
	}
	
	public void setMensagemSelecionda(Mensagem mensagemSelecionda) {
		this.mensagemSelecionda = mensagemSelecionda;
	}
	
	public String enviarEmail() {
		Pattern pattern;
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(emailPattern);

		String[] emails = destinatario.replaceAll(" ", "").split(";");

		for (String email : emails) {
			if (!pattern.matcher(email.toString()).matches()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Email inválido!", "Verifique os emails dos destinátrios"));
				return null;
			}
		}

		List<Destinatario> listDestinatario = new ArrayList<>();
		for (String email : emails) {
			Destinatario destinatario = new Destinatario();
			destinatario.setEmail(email);

			listDestinatario.add(destinatario);
		}

		try {
			mensagem.setDestinaratios(listDestinatario);
			MensagemService.getInstance().save(mensagem);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Sua mensagem foi enviada!"));
			limparCampos();
			return "index?faces-redirect=true";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ops!", "Ocorreu um erro ao salvar: " + ex.getMessage()));
		}

		return null;
		
	}
	
	public String pesquisaEmail() {

		mensagemPesquisa = new Mensagem();
		
		if("destinatario".equals(pesquisaPor)) {
			Destinatario dest = new Destinatario();
			dest.setEmail("%".concat(pesquisaTitulo).concat("%"));
			dest.setMensagem(mensagemPesquisa);
			
			List<Destinatario> listaDestinatario = new ArrayList<>();
			listaDestinatario.add(dest);
			
			mensagemPesquisa.setDestinaratios(listaDestinatario);
		} 
		
		if("assunto".equals(pesquisaPor)) {
			mensagemPesquisa.setAssunto("%".concat(pesquisaTitulo).concat("%"));
		} 
		
		return "";
	}

	public void limparCampos() {
		mensagem = new Mensagem();
		this.destinatario = "";
	}
	

}
