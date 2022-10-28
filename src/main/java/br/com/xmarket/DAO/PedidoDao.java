package br.com.xmarket.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.xmarket.Model.Pedido;

@Repository
public interface PedidoDao extends CrudRepository<Pedido, Integer> {

	@Modifying
	@Transactional
	@Query(value = "select p.data_pedido from pedido p", nativeQuery = true)
	List<String> findDataInicio();

	@Modifying
	@Transactional
	@Query(value = "select p.data_pedido from pedido p", nativeQuery = true)
	List<String> findDataFinal();
	
	@Query(value= "select * from pedido where id_usuario like :id_usuario", nativeQuery= true)
	String [][] querybuscapedido(int id_usuario);
	
}
