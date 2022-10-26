package br.com.xmarket.Controller;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.UsuarioDao;
import br.com.xmarket.Model.Usuario;

@RestController
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
	private UsuarioDao usuarioDao;

	@CrossOrigin
	@RequestMapping("/cadastro")
	@PostMapping
	public @ResponseBody ResponseEntity<Boolean> novoUsuario(@RequestBody Usuario usuario) {
		if (validarCpf(usuario.getCpf_usuario()) == true && validarRg(usuario.getRg_usuario()) == true) {
			if (validarEmail(usuario.getEmail_usuario()) == false) {
				String hash = getHashMd5(usuario.getSenha_usuario());
				usuario.setSenha_usuario(hash);
				usuarioDao.save(usuario);
				return ResponseEntity.ok(true);
			} else {
				return ResponseEntity.ok(false);
			}
		} else {
			System.out.println("Usuario ja cadastrado (cpf/rg)");
			return ResponseEntity.ok(false);
		}

	}

	@CrossOrigin
	@RequestMapping("/login")
	@PostMapping
	public @ResponseBody ResponseEntity<Integer> validaUsuario(@RequestBody String usuario) throws ParseException {
		Boolean logado = false;
		String senha = null;
		JSONObject json = (JSONObject) new JSONParser().parse(usuario);
		String email = (String) json.get("tLogin");
		if (validarEmail(email) == true) {
			senha = getHashMd5((String) json.get("tPassword"));
			logado = validarSenha(email, senha);

			if (logado == true) {
				int res = buscaId(email);
				System.out.println(res);
				return ResponseEntity.ok(res);
			} else {
				return ResponseEntity.ok(0);
			}
		}
		return null;
	}

	@CrossOrigin
	@GetMapping("/dados/{id_usuario}")
	public ResponseEntity<Usuario> puxarDados(@PathVariable int id_usuario) throws ParseException {

		Usuario usuario = usuarioDao.findById(id_usuario).orElse(null);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@CrossOrigin
	@PutMapping("/alteracao/{id_usuario}")
	public @ResponseBody ResponseEntity<Boolean> alterarDados(@PathVariable int id_usuario,
			@RequestBody Usuario newUsuario) {

		Usuario oldUsuario = usuarioDao.findById(id_usuario).get();
		oldUsuario.setNome_usuario(newUsuario.getNome_usuario());
		oldUsuario.setEmail_usuario(newUsuario.getEmail_usuario());
		oldUsuario.setEndereco_usuario(newUsuario.getEndereco_usuario());
		oldUsuario.setTelefone_usuario(newUsuario.getTelefone_usuario());
		usuarioDao.save(oldUsuario);
		System.out.println(oldUsuario);
		return ResponseEntity.ok(true);
	}

	// teste das querys, ignore
	@CrossOrigin
	@GetMapping("/teste2/{email}")
//	public ResponseEntity<Boolean> teste(@PathVariable String email) {
//
//		Usuario usuario= usuarioDao.queryValidarEmail(email);
//		return ResponseEntity.ok(usuario);
//		if (validarEmail(email) == true) {
//			return ResponseEntity.ok(true);
//		} else {
//			return ResponseEntity.ok(false);
//		}
//	}
	// fim dos testes das querys

	public Integer buscaId(String email) {
		ArrayList<Usuario> lista = (ArrayList<Usuario>) (usuarioDao.findAll());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEmail_usuario().equals(email)) {
				return lista.get(i).getId_usuario();
			}
		}
		return null;
	}

	// VALIDACOES DO BANCO

	public Boolean validarEmail(String email) {

		Usuario usuario = usuarioDao.queryValidarEmail(email);
		if (usuario != null) {
			return true;
		} else {
			return false;
		}

	}

	public Boolean validarSenha(String email, String senha) {
		Usuario usuario = usuarioDao.queryValidarEmail(email);
		if (usuario.getSenha_usuario().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean validarCpf(String cpf) {
		Boolean result = true;
		ArrayList<Usuario> lista = (ArrayList<Usuario>) (usuarioDao.findAll());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCpf_usuario().equals(cpf)) {
				result = false;
			}
		}
		return result;
	}

	public Boolean validarRg(String rg) {
		Boolean result = true;
		ArrayList<Usuario> lista = (ArrayList<Usuario>) (usuarioDao.findAll());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getRg_usuario().equals(rg)) {
				result = false;
			}
		}
		return result;
	}

}
