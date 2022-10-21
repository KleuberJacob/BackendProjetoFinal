package br.com.xmarket.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xmarket.Model.Usuario;
@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
	
}
