package br.com.xmarket.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xmarket.Model.Carrinho;

@Repository
public interface CarrinhoDao extends CrudRepository<Carrinho, Integer> {

}
