package br.com.xmarket.Controller;

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

import br.com.xmarket.DAO.CarrinhoDao;
import br.com.xmarket.DAO.ProdutoDao;
import br.com.xmarket.Model.Carrinho;
import br.com.xmarket.Model.Produto;

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
//		String usuario= "2"; 		
//		String nome_produto= "Nike Revolution 8";			
//		String quantidade_produto= "3";
//		String tamanho_produto= "37";	
//		int usuario = Integer.parseInt((usuariostr));
//		int quantidade_produto=  Integer.parseInt((quantidadestr)); 
//		int tamanho_produto=  Integer.parseInt((tamanhostr));
		
		System.out.println(item);
		System.out.println(json);
		System.out.println(usuario+nome_produto+quantidade_produto+tamanho_produto);
		
		if(conferirProduto(nome_produto,tamanho_produto,quantidade_produto)) {
			System.out.println(codigoProduto);
			Carrinho novoProduto = new Carrinho(usuario, codigoProduto, quantidade_produto);
			carrinhoDao.save(novoProduto);
			return ResponseEntity.ok("PRODUTO ADICIONADO");
		}else {
			return ResponseEntity.badRequest().body("QUANTIDADE INVÁLIDA");
		}

	}
	
	@CrossOrigin
	@GetMapping("/verificaQuantidade/{nome}")
	public  ResponseEntity<Integer[][]> verificaQuantidade(@PathVariable String nome){
		ArrayList<Produto> lista= (ArrayList<Produto>)(produtoDao.findAll());
		
		
		Integer [][] quantTamanho = new Integer[98][2];
		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getNome_produto().equals(nome)) {
				quantTamanho[i][0]= lista.get(i).getTamanho_produto();
				quantTamanho[i][1]= lista.get(i).getQuantidade_produto();
			}
		}
		
		return ResponseEntity.ok(quantTamanho);
		
	}

	
	// consultas no banco
	private boolean conferirProduto(String nome_produto, String tamanho,String quantidade) {
		ArrayList<Produto> lista= (ArrayList<Produto>)(produtoDao.findAll());
		
		for(int i=0; i<lista.size();i++) {
			if(lista.get(i).getNome_produto().equals(nome_produto)) {
				if(lista.get(i).getTamanho_produto()== Integer.parseInt(tamanho)) {
					if(lista.get(i).getQuantidade_produto()>= Integer.parseInt(quantidade)) {
						codigoProduto = lista.get(i).getCodigo_produto();
						return true;
					}
				}
			}
		}
		return false;	
	}

	

}
