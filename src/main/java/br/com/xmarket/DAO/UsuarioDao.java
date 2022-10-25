package br.com.xmarket.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xmarket.Model.Usuario;
@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
	
	@Query(value= "select * from usuario where email_usuario like :email_usuario", nativeQuery= true)
	Usuario queryValidarEmail(String email_usuario);
	
}
