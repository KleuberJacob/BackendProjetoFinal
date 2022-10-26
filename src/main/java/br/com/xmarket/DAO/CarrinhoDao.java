package br.com.xmarket.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xmarket.Model.Carrinho;

@Repository
public interface CarrinhoDao extends CrudRepository<Carrinho, Integer> {
	
	@Query(value= "select c.codigo_produto, p.nome_produto, p.tamanho_produto, p.preco_produto, c.quantidade, p.imagem_produto "
			+ "from carrinho as c "
			+ "inner join produto as p on (c.codigo_produto = p.codigo_produto) "
			+ "where id_usuario= :id_usuario", nativeQuery= true)
	String [][] queryProdutoCarrinho(int id_usuario);

}
