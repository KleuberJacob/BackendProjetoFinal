package br.com.xmarket.Controller;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

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
	public @ResponseBody String validaUsuario(@RequestBody String usuario) throws ParseException {	
		Object ob = new JSONParser().parse(usuario);
		
		JSONObject json = (JSONObject) ob;
		String email= (String) json.get("tLogin");
		String senha= getHashMd5((String) json.get("tPassword"));
		
		System.out.println(senha);
		System.out.println(email);
		System.out.println(validarEmail(email));
		

		return email;
	}
	
	public Boolean validarEmail(String email) {
		Boolean result=false;
		ArrayList<Usuario> lista= (ArrayList<Usuario>)(dao.findAll());

		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getEmail_usuario().equals(email)) {
				result=true;
			}
		}
		return result;
		
	}
	
	
	
	
}
