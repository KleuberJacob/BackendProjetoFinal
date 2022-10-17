package br.com.xmarket.DAO;

import org.springframework.data.repository.CrudRepository;

import br.com.xmarket.Model.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

}
