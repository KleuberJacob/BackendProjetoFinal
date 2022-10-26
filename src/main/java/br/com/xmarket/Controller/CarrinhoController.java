package br.com.xmarket.Controller;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.CarrinhoDao;
import br.com.xmarket.DAO.ProdutoDao;
import br.com.xmarket.Model.Carrinho;

@RestController
public class CarrinhoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private CarrinhoDao carrinhoDao;
	private int codigoProduto;
	
	@CrossOrigin
	@RequestMapping("/addCarrinho")
	@PostMapping
	public @ResponseBody ResponseEntity<String> addCarrinho(@RequestBody String item) throws ParseException {	
		//item : vai vir um json em forma de string com o id_usuario, nome_produto, quantidade_produto etamanho_produto
		
		JSONObject json = (JSONObject) new JSONParser().parse(item);		
		String usuario= (String) json.get("idUsuario"); 		
		String nome_produto= (String) json.get("name");
		String quantidade_produto= (String) json.get("quantidade");
		String tamanho_produto= (String) json.get("tamanho");	
		
		System.out.println(item);
		System.out.println(json);
		System.out.println(usuario+nome_produto+quantidade_produto+tamanho_produto);
		
		if(conferirProduto(nome_produto,tamanho_produto,quantidade_produto)) {
			System.out.println(codigoProduto);
			Carrinho novoProduto = new Carrinho(usuario, codigoProduto, quantidade_produto);
			carrinhoDao.save(novoProduto);
			return ResponseEntity.ok("PRODUTO ADICIONADO");
		}else {
			return ResponseEntity.badRequest().body("REQUISIÇÃO INVÁLIDA");
		}

	}
	
	@CrossOrigin
	@GetMapping("/verificaQuantidade/{nome}")
	public  ResponseEntity<Integer[][]> verificaQuantidade(@PathVariable String nome){
		Integer[][] quantTamanho = produtoDao.queryQuantTamanho(nome);
		return ResponseEntity.ok(quantTamanho);
		
	}
	
	@CrossOrigin
	@GetMapping("/produtoCarrinho/{id_usuario}")
	public  ResponseEntity<String[][]> verificaQuantidade(@PathVariable Integer id_usuario){
		String [][] produto = carrinhoDao.queryProdutoCarrinho(id_usuario);
		return ResponseEntity.ok(produto);
	}
	
	@CrossOrigin
	@DeleteMapping("/deletar/{id_usuario}/{codigo_produto}")
	public ResponseEntity<Boolean> deletar(@PathVariable int id_usuario, @PathVariable int codigo_produto){
		try {
			carrinhoDao.queryDeletar(id_usuario, codigo_produto);
			return ResponseEntity.ok(true);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok(false);
		}
		
	}
	
	
	//TESTE DAS QUERYS, IGNORE
//	@CrossOrigin
//	@GetMapping("/teste/{nome}/{tamanho}")
//	public  ResponseEntity<Boolean> teste(@PathVariable String nome, @PathVariable String tamanho){
//		 
//		//Integer[][] quantidade = produtoDao.queryQuantidade(nome, tamanho);
//		if (conferirProduto(nome, tamanho, "2") == true) {
//			System.out.println(codigoProduto);
//			return ResponseEntity.ok(true);
//		}else {
//			return ResponseEntity.ok(false);
//		}
//		
//	}
	//FIM DO TESTE DAS QUERYS
	
	
	
	// consultas no banco
	private boolean conferirProduto(String nome_produto, String tamanho,String quantidade) {
		
		Integer[][] validacao = produtoDao.queryQuantidade(nome_produto, tamanho);
		try {
			if(validacao[0][0] != null) {
				codigoProduto= validacao[0][0];
				if(validacao[0][1] >= Integer.parseInt(quantidade)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
		
	}

	

}
