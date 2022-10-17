package br.com.xmarket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.UsuarioDao;
import br.com.xmarket.Model.Usuario;

@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

	@Autowired
	private UsuarioDao dao;
	
	@CrossOrigin
	@PostMapping
	public @ResponseBody Usuario novoUsuario(@RequestBody Usuario usuario) {
		
		System.out.println(usuario.getSenha_usuario());
		dao.save(usuario);
		System.out.println(usuario.toString()); 
		return usuario;
	}
	
	
	
	
}
