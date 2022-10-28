package br.com.xmarket.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.xmarket.Model.Produto;
@Repository
public interface ProdutoDao extends CrudRepository<Produto, Integer> {

	@Query(value= "select tamanho_produto, quantidade_produto from produto where nome_produto like :nome_produto", nativeQuery= true)
	Integer[][] queryQuantTamanho(String nome_produto);
	
	@Query(value= "select codigo_produto, quantidade_produto from produto where nome_produto like :nome_produto and tamanho_produto= :tamanho_produto", nativeQuery= true)
	Integer[][] queryQuantidade(String nome_produto, String tamanho_produto);
	
	@Modifying
	@Transactional
	@Query(value= "update produto set quantidade_produto= quantidade_produto - :quantidade where codigo_produto= :codigo_produto", nativeQuery=true)
	Integer queryAtualizarQuantidade(String quantidade, String codigo_produto);
}
