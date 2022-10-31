package br.com.xmarket.Controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.AdministradorDao;
import br.com.xmarket.DAO.ProdutoDao;
import br.com.xmarket.Model.Administrador;
import br.com.xmarket.Model.Produto;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AdministradorController {

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
	private AdministradorDao administradorDao;
	@Autowired
	private ProdutoDao produtoDao;

	@PostMapping("/cadastroadm")
	@Operation(summary = "Cadastro Administrador")
	public @ResponseBody ResponseEntity<Boolean> novoAdministrador(@RequestBody Administrador administrador) {
		if (validarCpfadm(administrador.getCpf_adm()) == true && validarRgadm(administrador.getRg_adm()) == true) {
			if (validarEmailadm(administrador.getEmail_adm()) == false) {
				String hash = getHashMd5(administrador.getSenha_adm());
				administrador.setSenha_adm(hash);
				administradorDao.save(administrador);
				return ResponseEntity.ok(true);
			} else {
				return ResponseEntity.ok(false);
			}
		} else {
			return ResponseEntity.ok(false);
		}
	}

	@PostMapping("/loginadm")
	@Operation(summary = "Login Administrador")
	public @ResponseBody ResponseEntity<Integer> validaAdministrador(@RequestBody String administrador)
			throws ParseException {
		Boolean logado = false;
		String senha = null;
		JSONObject json = (JSONObject) new JSONParser().parse(administrador);
		String email = (String) json.get("tLogin");
		if (validarEmailadm(email) == true) {
			senha = getHashMd5((String) json.get("tPassword"));
			logado = validarSenhaadm(email, senha);

			if (logado == true) {
				int res = buscaIdadm(email);
				return ResponseEntity.ok(res);
			} else {
				return ResponseEntity.ok(0);
			}
		}
		return null;
	}

	@GetMapping("/dadosadm/{id_adm}")
	@Operation(summary = "Busca dados Administrador")
	public ResponseEntity<Administrador> puxarDados(@PathVariable int id_adm) throws ParseException {
		Administrador administrador = administradorDao.findById(id_adm).orElse(null);
		if (administrador != null) {
			return ResponseEntity.ok(administrador);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/cadastroProduto")
	@Operation(summary = "Cadastra produtos no estoque")
	public ResponseEntity<Boolean> cadastrarProduto(@RequestBody String novoProduto) throws ParseException {
		JSONObject json = (JSONObject) new JSONParser().parse(novoProduto);
		String nome = (String) json.get("nome");
		String tipo = (String) json.get("tipo");
		String descricao = (String) json.get("descricao");
		String preco = (String) json.get("preco");
		String tamanho = (String) json.get("tamanho");
		String quantidade = (String) json.get("quantidade");
		String imagem = (String) json.get("imagem");

		try {
			String idProdutoBanco = produtoDao.queryAcharProduto(nome, tamanho);

			if (idProdutoBanco == null) {
				Produto produto = new Produto(nome, tipo, descricao,  preco, tamanho, quantidade, imagem);
				produtoDao.save(produto);
				return ResponseEntity.ok(true);

			} else {
				return ResponseEntity.ok(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(false);
		}

	}

	public Integer buscaIdadm(String email) {
		ArrayList<Administrador> lista = (ArrayList<Administrador>) (administradorDao.findAll());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getEmail_adm().equals(email)) {
				return lista.get(i).getId_adm();
			}
		}
		return null;
	}

	// VALIDACOES DO BANCO
	public Boolean validarEmailadm(String email) {

		Administrador administrador = administradorDao.queryValidarEmail(email);
		if (administrador != null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean validarSenhaadm(String email, String senha) {
		Administrador administrador = administradorDao.queryValidarEmail(email);
		if (administrador.getSenha_adm().equals(senha)) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean validarCpfadm(String cpf) {
		Boolean result = true;
		ArrayList<Administrador> lista = (ArrayList<Administrador>) (administradorDao.findAll());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCpf_adm().equals(cpf)) {
				result = false;
			}
		}
		return result;
	}

	public Boolean validarRgadm(String rg) {
		Boolean result = true;
		ArrayList<Administrador> lista = (ArrayList<Administrador>) (administradorDao.findAll());

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getRg_adm().equals(rg)) {
				result = false;
			}
		}
		return result;
	}
}
