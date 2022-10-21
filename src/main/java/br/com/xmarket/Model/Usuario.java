package br.com.xmarket.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id	
	private int id_usuario;
	
	@Column(name="nome_usuario")
	private String nome_usuario;
	
	@Column(name="rg_usuario")
	private String rg_usuario;
	
	@Column(name="cpf_usuario")
	private String cpf_usuario;	
	
	@Column(name="email_usuario")
	private String email_usuario;	
	
	@Column(name="telefone_usuario")
	private String telefone_usuario;	
	
	@Column(name="endereco_usuario")
	private String endereco_usuario;		
	
	@Column(name="senha_usuario")
	private String senha_usuario;	
	
	public Usuario(){
		super();
	}

	public Usuario(String nome_usuario, String rg_usuario, String cpf_usuario, String email_usuario,
			String telefone_usuario, String endereco_usuario, String senha_usuario) {
		super();
		this.nome_usuario = nome_usuario;
		this.rg_usuario = rg_usuario;
		this.cpf_usuario = cpf_usuario;
		this.email_usuario = email_usuario;
		this.telefone_usuario = telefone_usuario;
		this.endereco_usuario = endereco_usuario;
		this.senha_usuario = senha_usuario;
	}
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome_usuario() { 
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getRg_usuario() {
		return rg_usuario;
	}

	public void setRg_usuario(String rg_usuario) {
		this.rg_usuario = rg_usuario;
	}

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public String getTelefone_usuario() {
		return telefone_usuario;
	}

	public void setTelefone_usuario(String telefone_usuario) {
		this.telefone_usuario = telefone_usuario;
	}

	public String getEndereco_usuario() {
		return endereco_usuario;
	}

	public void setEndereco_usuario(String endereco_usuario) {
		this.endereco_usuario = endereco_usuario;
	}

	public String getSenha_usuario() {
		return senha_usuario;
	}

	public void setSenha_usuario(String senha_usuario) {
		this.senha_usuario = senha_usuario;
	}
	
	public String toString() {
		return "{"
				+ "'nome_usuario': '"+ getNome_usuario() +"',"
				+ "'rg_usuario': '"+ getRg_usuario()+"',"
				+ "'cpf_usuario': '"+ getCpf_usuario()+"',"
				+ "'email_usuario': '"+ getEmail_usuario()+"',"
				+ "'telefone_usuario': '"+ getTelefone_usuario()+"',"
				+ "}";
	}
	

}
