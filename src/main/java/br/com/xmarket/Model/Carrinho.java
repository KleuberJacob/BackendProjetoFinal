package br.com.xmarket.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carrinho")
public class Carrinho {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrinho;
	
	@Column(name="id_usuario")
	private String id_usuario;
	
	@Column(name="codigo_produto")
	private int codigo_produto;
	
	@Column(name="quantidade")
	private String quantidade;

	public Carrinho(){
		super();
	}

	public Carrinho(String id_usuario, int codigo_produto, String quantidade) {
		super();
		this.id_usuario = id_usuario;
		this.codigo_produto = codigo_produto;
		this.quantidade = quantidade;
	}

	public int getId_carrinho() {
		return id_carrinho;
	}

	public void setId_carrinho(int id_carrinho) {
		this.id_carrinho = id_carrinho;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getCodigo_produto() {
		return codigo_produto;
	}

	public void setCodigo_produto(int codigo_produto) {
		this.codigo_produto = codigo_produto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}



	
}
