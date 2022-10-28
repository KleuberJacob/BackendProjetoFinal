package br.com.xmarket.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.xmarket.Model.Item_Pedido;

@Repository
public interface ItemPedidoDao extends CrudRepository<Item_Pedido, Integer> {
	
	@Modifying
	@Transactional
	@Query (value= "insert into item_pedido \r\n"
			+ "(codigo_pedido, id_usuario, codigo_produto, quantidade_item)\r\n"
			+ "values\r\n"
			+ "(:numeroPedido , :id_usuario , :codigo_produto , :quantidade)" , nativeQuery= true)
	Integer salvarItem(Long numeroPedido, String id_usuario, String codigo_produto, String quantidade);
	
	

}
