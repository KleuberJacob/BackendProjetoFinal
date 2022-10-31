package br.com.xmarket.Controller;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.ProdutoDao;
import br.com.xmarket.Model.Produto;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
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
	
	@GetMapping("/retornoProdutos")
	@Operation(summary= "Retorna todos os produtos do estoque")
	public ResponseEntity<ArrayList<Produto>> retornoPodutos(){
		ArrayList<Produto> produtos= (ArrayList<Produto>) produtoDao.findAll();
		return ResponseEntity.ok(produtos);
	}

}
