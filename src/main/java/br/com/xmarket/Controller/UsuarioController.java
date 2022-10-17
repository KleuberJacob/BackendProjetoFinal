package br.com.xmarket.Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
	
	public static String getHashMd5(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
    }

	@Autowired
	private UsuarioDao dao;
	
	@CrossOrigin
	@PostMapping
	public @ResponseBody Usuario novoUsuario(@RequestBody Usuario usuario) {
		
		System.out.println(usuario.getSenha_usuario());
		String hash = getHashMd5(usuario.getSenha_usuario());
		
		usuario.setSenha_usuario(hash);		
		
		dao.save(usuario);
//		System.out.println(usuario.toString()); 
		return usuario;
	}
	
	
	
	
}
