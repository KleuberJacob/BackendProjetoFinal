package br.com.xmarket.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo_pedido;
	
	@Column(name="numero_pedido")
	private String numero_pedido;
	
	@Column(name="id_usuario")
	private String id_usuario;
	
	@Column(name="quant_itens_pedido")
	private String quantidade;
	
	@Column(name="endereco_pedido")
	private String endereco_pedido;
	
	@Column(name="valor_pedido")
	private String valor_pedido;
	
	@Column(name="data_pedido")
	private LocalDate data_pedido;

	public Pedido(){
		super();
	}

	public Pedido(String numero_pedido, String id_usuario, String quantidade, String endereco_pedido,
			String valor_pedido, LocalDate data_pedido) {
		super();
		this.numero_pedido = numero_pedido;
		this.id_usuario = id_usuario;
		this.quantidade = quantidade;
		this.endereco_pedido = endereco_pedido;
		this.valor_pedido = valor_pedido;
		this.data_pedido = data_pedido;
	}

	public LocalDate getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}

	public String getNumero_pedido() {
		return numero_pedido;
	}

	public void setNumero_pedido(String numero_pedido) {
		this.numero_pedido = numero_pedido;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getEndereco_pedido() {
		return endereco_pedido;
	}

	public void setEndereco_pedido(String endereco_pedido) {
		this.endereco_pedido = endereco_pedido;
	}

	public String getValor_pedido() {
		return valor_pedido;
	}

	public void setValor_pedido(String valor_pedido) {
		this.valor_pedido = valor_pedido;
	}
	
	public String toString() {
		return "{"
				+ "'numero_pedido': '"+ getNumero_pedido() +"',"
				+ "'quant_itens': '"+ getQuantidade()+"',"
				+ "'endereco': '"+ getEndereco_pedido()+"',"
				+ "'valor_pedido': '"+ getValor_pedido()+"'"
				+ "}";
	}

}
