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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.UsuarioDao;
import br.com.xmarket.Model.Usuario;

@RestController
public class UsuarioController {
	
	//String email, senha;
	
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
	public @ResponseBody ResponseEntity<Boolean> novoUsuario(@RequestBody Usuario usuario) {		
		if(validarCpf(usuario.getCpf_usuario())==true && validarRg(usuario.getRg_usuario())==true) {
			if(validarEmail(usuario.getEmail_usuario())==false) {
				String hash = getHashMd5(usuario.getSenha_usuario());
				usuario.setSenha_usuario(hash);		
				dao.save(usuario);
				return ResponseEntity.ok(true);		
			}else {
				return ResponseEntity.ok(false);	
			}	
		}else {
			System.out.println("Usuario ja cadastrado (cpf/rg)");
			return ResponseEntity.ok(false);
		}
		
	}
	
	@CrossOrigin
	@RequestMapping("/login")
	@PostMapping
	public @ResponseBody ResponseEntity<Boolean> validaUsuario(@RequestBody String usuario) throws ParseException {	
		Boolean logado=false;
		String senha=null;
		JSONObject json = (JSONObject) new JSONParser().parse(usuario);
		String email= (String) json.get("tLogin");
		if(validarEmail(email)==true) {
			senha= getHashMd5((String) json.get("tPassword"));
			logado = validarSenha(email, senha);	
			System.out.println(senha);
			System.out.println(email);
			System.out.println(validarEmail(email));
			System.out.println(logado);
			if(logado == true) {
				return ResponseEntity.ok(true);				
			}else {
				return ResponseEntity.ok(false);
			}
		}
		return null;
	}
	
	
	
	// VALIDACOES DO BANCO
	
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
	
	public Boolean validarSenha(String email, String senha) {
		Boolean result=false;
		ArrayList<Usuario> lista= (ArrayList<Usuario>)(dao.findAll());

		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getEmail_usuario().equals(email)) {
				if (lista.get(i).getSenha_usuario().equals(senha)){
					result=true;
				}else {
					result=false;
				}
			}
		}
		return result;
	}
	
	public Boolean validarCpf(String cpf) {
		Boolean result=true;
		ArrayList<Usuario> lista= (ArrayList<Usuario>)(dao.findAll());

		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getCpf_usuario().equals(cpf)) {
				result=false;
			}
		}
		return result;
	}
	public Boolean validarRg(String rg) {
		Boolean result=true;
		ArrayList<Usuario> lista= (ArrayList<Usuario>)(dao.findAll());

		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getRg_usuario().equals(rg)) {
				result=false;
			}
		}
		return result;
	}
	
}
