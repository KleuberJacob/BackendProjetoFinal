package br.com.xmarket.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrador")
public class Administrador {
	
	@Id	
	private int id_adm;
	
	@Column(name="nome_adm")
	private String nome_adm;
	
	@Column(name="rg_adm")
	private String rg_adm;
	
	@Column(name="cpf_adm")
	private String cpf_adm;	
	
	@Column(name="email_adm")
	private String email_adm;	
	
	@Column(name="telefone_adm")
	private String telefone_adm;	
	
	@Column(name="endereco_adm")
	private String endereco_adm;		
	
	@Column(name="senha_adm")
	private String senha_adm;	
	
	public Administrador(){
		super();
	}

	public Administrador(String nome_adm, String rg_adm, String cpf_adm, String email_adm,
			String telefone_adm, String endereco_adm, String senha_adm) {
		super();
		this.nome_adm = nome_adm;
		this.rg_adm = rg_adm;
		this.cpf_adm = cpf_adm;
		this.email_adm = email_adm;
		this.telefone_adm = telefone_adm;
		this.endereco_adm = endereco_adm;
		this.senha_adm = senha_adm;
	}
	
	public int getId_adm() {
		return id_adm;
	}

	public void setId_adm(int id_adm) {
		this.id_adm = id_adm;
	}

	public String getNome_adm() {
		return nome_adm;
	}

	public void setNome_adm(String nome_adm) {
		this.nome_adm = nome_adm;
	}

	public String getRg_adm() {
		return rg_adm;
	}

	public void setRg_adm(String rg_adm) {
		this.rg_adm = rg_adm;
	}

	public String getCpf_adm() {
		return cpf_adm;
	}

	public void setCpf_adm(String cpf_adm) {
		this.cpf_adm = cpf_adm;
	}

	public String getEmail_adm() {
		return email_adm;
	}

	public void setEmail_adm(String email_adm) {
		this.email_adm = email_adm;
	}

	public String getTelefone_adm() {
		return telefone_adm;
	}

	public void setTelefone_adm(String telefone_adm) {
		this.telefone_adm = telefone_adm;
	}

	public String getEndereco_adm() {
		return endereco_adm;
	}

	public void setEndereco_adm(String endereco_adm) {
		this.endereco_adm = endereco_adm;
	}

	public String getSenha_adm() {
		return senha_adm;
	}

	public void setSenha_adm(String senha_adm) {
		this.senha_adm = senha_adm;
	}

	public String toString() {
		return "{"
				+ "'nome_adm': '"+ getNome_adm() +"',"
				+ "'rg_adm': '"+ getRg_adm()+"',"
				+ "'cpf_adm': '"+ getCpf_adm()+"',"
				+ "'email_adm': '"+ getEmail_adm()+"',"
				+ "'telefone_adm': '"+ getTelefone_adm()+"',"
				+ "}";
	}

}
