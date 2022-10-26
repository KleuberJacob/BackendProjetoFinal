package br.com.xmarket.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.xmarket.Model.Carrinho;

@Repository
public interface CarrinhoDao extends CrudRepository<Carrinho, Integer> {
	
	@Query(value= "select c.codigo_produto, p.nome_produto, p.tamanho_produto, p.preco_produto, c.quantidade, p.imagem_produto "
			+ "from carrinho as c "
			+ "inner join produto as p on (c.codigo_produto = p.codigo_produto) "
			+ "where id_usuario= :id_usuario", nativeQuery= true)
	String [][] queryProdutoCarrinho(int id_usuario);
	
	@Query(value= "select id_carrinho, id_usuario, codigo_produto, quantidade from carrinho where id_usuario= :id_usuario "
			+ "and codigo_produto= :codigo_produto", nativeQuery= true)
	Carrinho queryProdutoExiste(int id_usuario, int codigo_produto);
	
	@Modifying
	@Transactional
	@Query(value= "update carrinho set quantidade= :quantidade where id_carrinho= :id_carrinho", nativeQuery=true)
	Integer queryUpdateQuantidade(int quantidade, int id_carrinho);
	
	
	@Modifying
	@Transactional
	@Query(value= "delete from carrinho where id_usuario= :id_usuario "
			+"and codigo_produto= :codigo_produto", nativeQuery= true)
	Integer queryDeletar(int id_usuario, int codigo_produto);

}
