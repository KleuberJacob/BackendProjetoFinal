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
	
	@Column(name="preco_produto")
	private float preco_produto;	
	
	@Column(name="tamanho_produto")
	private int tamanho_produto;	
	
	@Column(name="quantidade_produto")
	private int quantidade_produto;

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

	public float getPreco_produto() {
		return preco_produto;
	}

	public void setPreco_produto(float preco_produto) {
		this.preco_produto = preco_produto;
	}

	public int getTamanho_produto() {
		return tamanho_produto;
	}

	public void setTamanho_produto(int tamanho_produto) {
		this.tamanho_produto = tamanho_produto;
	}

	public int getQuantidade_produto() {
		return quantidade_produto;
	}

	public void setQuantidade_produto(int quantidade_produto) {
		this.quantidade_produto = quantidade_produto;
	}	
	
}
