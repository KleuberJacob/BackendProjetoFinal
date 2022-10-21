package br.com.xmarket.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xmarket.Model.Produto;
@Repository
public interface ProdutoDao extends CrudRepository<Produto, Integer> {

}
