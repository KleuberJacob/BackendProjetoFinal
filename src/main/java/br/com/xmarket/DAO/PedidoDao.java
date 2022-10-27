package br.com.xmarket.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.xmarket.Model.Pedido;

public interface PedidoDao extends CrudRepository<Pedido, Integer> {

}
