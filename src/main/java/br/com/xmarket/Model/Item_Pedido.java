package br.com.xmarket.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "item_pedido")
public class Item_Pedido {
	
	@Id	
	private int id_item;
	
	@Column(name="codigo_pedido")
	private String codigo_pedido;
	
	@Column(name="id_usuario")
	private String id_usuario;
	
	@Column(name="codigo_produto")
	private String codigo_produto;
	
	@Column(name="quantidade_item")
	private String quant_item;
	
	public Item_Pedido(){
		super();
	}

	public Item_Pedido(String codigo_pedido, String id_usuario, String codigo_produto, String quant_item) {
		super();
		this.codigo_pedido = codigo_pedido;
		this.id_usuario = id_usuario;
		this.codigo_produto = codigo_produto;
		this.quant_item = quant_item;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getCodigo_pedido() {
		return codigo_pedido;
	}

	public void setCodigo_pedido(String codigo_pedido) {
		this.codigo_pedido = codigo_pedido;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCodigo_produto() {
		return codigo_produto;
	}

	public void setCodigo_produto(String codigo_produto) {
		this.codigo_produto = codigo_produto;
	}

	public String getQuant_item() {
		return quant_item;
	}

	public void setQuant_item(String quant_item) {
		this.quant_item = quant_item;
	}
		
	

}
