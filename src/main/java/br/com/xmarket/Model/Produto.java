package br.com.xmarket.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "produto")
public class Produto {
	@Id	
	private int codigo_produto;
	
	@Column(name="nome_produto")
	private String nome_produto;
	
	@Column(name="tipo_produto")
	private String tipo_produto;
	
	@Column(name="descricao_produto")
	private String descricao_produto;
	
	@Column(name="preco_produto")
	private String preco_produto;	
	
	@Column(name="tamanho_produto")
	private String tamanho_produto;	
	
	@Column(name="quantidade_produto")
	private String quantidade_produto;
	
	@Column(name="imagem_produto")
	private String imagem_produto;
	
	@Column(name="modelo_unico")
	private String modelo_unico;

	public Produto(){
		super();
	}

	public Produto(int codigo_produto, String nome_produto, String tipo_produto, String descricao_produto,
			String preco_produto, String tamanho_produto, String quantidade_produto, String imagem_produto,
			String modelo_unico) {
		super();
		this.codigo_produto = codigo_produto;
		this.nome_produto = nome_produto;
		this.tipo_produto = tipo_produto;
		this.descricao_produto = descricao_produto;
		this.preco_produto = preco_produto;
		this.tamanho_produto = tamanho_produto;
		this.quantidade_produto = quantidade_produto;
		this.imagem_produto = imagem_produto;
		this.modelo_unico = modelo_unico;
	}

	public Produto(String nome_produto, String tipo_produto, String descricao_produto, String preco_produto, String tamanho_produto,
			String quantidade_produto, String imagem_produto, String modelo_unico) {
		super();
		this.nome_produto = nome_produto;
		this.tipo_produto = tipo_produto;
		this.descricao_produto= descricao_produto;
		this.preco_produto = preco_produto;
		this.tamanho_produto = tamanho_produto;
		this.quantidade_produto = quantidade_produto;
		this.imagem_produto = imagem_produto;
		this.modelo_unico = modelo_unico;
	}

	public int getCodigo_produto() {
		return codigo_produto;
	}

	public void setCodigo_produto(int codigo_produto) {
		this.codigo_produto = codigo_produto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public String getTipo_produto() {
		return tipo_produto;
	}

	public void setTipo_produto(String tipo_produto) {
		this.tipo_produto = tipo_produto;
	}
	
	public String getModelo_unico() {
		return modelo_unico;
	}

	public void setModelo_unico(String modelo_unico) {
		this.modelo_unico = modelo_unico;
	}

	public String getDescricao_produto() {
		return descricao_produto;
	}

	public void setDescricao_produto(String descricao_produto) {
		this.descricao_produto = descricao_produto;
	}

	public String getImagem_produto() {
		return imagem_produto;
	}

	public void setImagem_produto(String imagem_produto) {
		this.imagem_produto = imagem_produto;
	}

	public String getPreco_produto() {
		return preco_produto;
	}

	public void setPreco_produto(String preco_produto) {
		this.preco_produto = preco_produto;
	}

	public String getTamanho_produto() {
		return tamanho_produto;
	}

	public void setTamanho_produto(String tamanho_produto) {
		this.tamanho_produto = tamanho_produto;
	}

	public String getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(String quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}	
	
}
