package br.com.xmarket.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xmarket.Model.Administrador;

@Repository
public interface AdministradorDao extends CrudRepository<Administrador, Integer> {
	
	@Query(value= "select * from administrador where email_adm like :email_adm", nativeQuery= true)
	Administrador queryValidarEmail(String email_adm);
	
}
