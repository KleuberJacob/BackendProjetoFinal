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
public class UsuarioController {
	
	String email, senha;
	
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
	@RequestMapping("/cadastro")
	@PostMapping
	public @ResponseBody Usuario novoUsuario(@RequestBody Usuario usuario) {		
		
		String hash = getHashMd5(usuario.getSenha_usuario());
		
		usuario.setSenha_usuario(hash);		
		
		dao.save(usuario);
		return usuario;
	}
	
	@CrossOrigin
	@RequestMapping("/login")
	@PostMapping
	public @ResponseBody String validaUsuario(@RequestBody String emailSenha) {	
		
		String [] vetor = emailSenha.split(",");

//		String hash = getHashMd5(usuario.getSenha_usuario());
		System.out.println(vetor[0]);
		System.out.println(vetor[1]);
//		usuario.setSenha_usuario(hash);		
		
//		dao.save(usuario);
		return email;
	}
	
	
	
	
}
