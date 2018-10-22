package br.ufba.fpc.desafio01.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.ufba.fpc.desafio01.utils.ModelBase;

@Entity(name="usuarios")
public class Usuario extends ModelBase{
	@Column(length = 100, nullable = false)
	private String nome;
	@Column(length = 255, nullable = false)
	private String email;
	@Column(length = 100, nullable = false)
	private String senha;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
