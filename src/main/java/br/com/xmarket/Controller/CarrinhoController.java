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
import br.com.xmarket.DAO.ItemPedidoDao;
import br.com.xmarket.DAO.PedidoDao;
import br.com.xmarket.DAO.ProdutoDao;
import br.com.xmarket.Model.Carrinho;
import br.com.xmarket.Model.Pedido;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CarrinhoController {

	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private CarrinhoDao carrinhoDao;
	@Autowired
	private ItemPedidoDao itemPedidoDao;
	@Autowired
	private PedidoDao pedidoDao;

	private int codigoProduto;

	@PostMapping("/finalizarPedido") 
	@Operation(summary = "Finalizar Pedido")
	public @ResponseBody ResponseEntity<Integer> finalizarPedido(@RequestBody String item) throws ParseException {
		try {		
			JSONObject json = (JSONObject) new JSONParser().parse(item);
			Long numeroPedido = (Long) json.get("numPedido");
			String usuario = (String) json.get("idUsuario");
			String endereco = (String) json.get("endereco");
			String valor = (String) json.get("total");
			String observacao= (String) json.get("observacao");
	
			String[][] produtos = carrinhoDao.queryProdutoCarrinho(Integer.parseInt(usuario));
			int soma=0;
			
				for (int i = 0; i < produtos.length; i++) {
					if(conferirProduto(produtos[i][1], produtos[i][3], produtos[i][5])) {
						itemPedidoDao.salvarItem(numeroPedido, usuario, produtos[i][0], produtos[i][5]);
						produtoDao.queryAtualizarQuantidade(produtos[i][5], produtos[i][0]);
						soma += Integer.parseInt(produtos[i][5]);
					}else {
						return ResponseEntity.ok(2);
					}						
				}
				carrinhoDao.queryDeletarCompra(usuario);				
				Pedido novoPedido = new Pedido(numeroPedido, usuario, String.valueOf(soma), endereco, valor, observacao);
				pedidoDao.save(novoPedido);			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(1);			
		}
		return ResponseEntity.ok(0);
	}

	@PostMapping("/addExisteCarrinho")
	@Operation(summary = "Valida não duplicidade de produtos no carrinho de compras")
	public @ResponseBody ResponseEntity<Boolean> addExisteCarrinho(@RequestBody String item) throws ParseException {
		
		JSONObject json = (JSONObject) new JSONParser().parse(item);
		String usuario = (String) json.get("idUsuario");
		String nome_produto = (String) json.get("name");
		String quantidade_produto = (String) json.get("quantidade");
		String tamanho_produto = (String) json.get("tamanho");

		if (conferirProduto(nome_produto, tamanho_produto, quantidade_produto)) {
			Carrinho carrinhoExistente = carrinhoDao.queryProdutoExiste(Integer.parseInt(usuario), codigoProduto);
			if (carrinhoExistente == null) {
				return ResponseEntity.ok(false);
			} else {
				int quantidadeFinal = Integer.parseInt(carrinhoExistente.getQuantidade())
						+ Integer.parseInt(quantidade_produto);
				carrinhoExistente.setQuantidade(String.valueOf(quantidadeFinal));
				Integer update = carrinhoDao.queryUpdateQuantidade(Integer.parseInt(carrinhoExistente.getQuantidade()),
						carrinhoExistente.getId_carrinho());
				return ResponseEntity.ok(true);
			}
		} else {
			return ResponseEntity.badRequest().body(false);
		}

	}

	@PostMapping("/addCarrinho")
	@Operation(summary = "Inseri produto no carrinho de compras")
	public @ResponseBody ResponseEntity<String> addCarrinho(@RequestBody String item) throws ParseException {
		
		JSONObject json = (JSONObject) new JSONParser().parse(item);
		String usuario = (String) json.get("idUsuario");
		String nome_produto = (String) json.get("name");
		String quantidade_produto = (String) json.get("quantidade");
		String tamanho_produto = (String) json.get("tamanho");		

		if (conferirProduto(nome_produto, tamanho_produto, quantidade_produto)) {			
			Carrinho novoProduto = new Carrinho(usuario, codigoProduto, quantidade_produto);
			carrinhoDao.save(novoProduto);
			return ResponseEntity.ok("PRODUTO ADICIONADO");
		} else {
			return ResponseEntity.badRequest().body("REQUISIÇÃO INVÁLIDA");
		}
	}

	@GetMapping("/verificaQuantidade/{nome}")
	@Operation(summary = "Verifica tamanho e quantidade de um produto.")
	public ResponseEntity<Integer[][]> verificaQuantidade(@PathVariable String nome) {
		Integer[][] quantTamanho = produtoDao.queryQuantTamanho(nome);
		return ResponseEntity.ok(quantTamanho);
	}

	@GetMapping("/produtoCarrinho/{id_usuario}")
	@Operation(summary = "Pega todos os produtos presentes no carrinho do usuário.")
	public ResponseEntity<String[][]> verificaQuantidade(@PathVariable Integer id_usuario) {
		String[][] produto = carrinhoDao.queryProdutoCarrinho(id_usuario);
		return ResponseEntity.ok(produto);
	}

	@DeleteMapping("/deletar/{id_usuario}/{codigo_produto}")
	@Operation(summary = "Exclui o produto presente no carrinho do usuário.")
	public ResponseEntity<Boolean> deletar(@PathVariable int id_usuario, @PathVariable int codigo_produto) {
		try {
			carrinhoDao.queryDeletar(id_usuario, codigo_produto);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.ok(false);
		}
	}

	private boolean conferirProduto(String nome_produto, String tamanho, String quantidade) {

		Integer[][] validacao = produtoDao.queryQuantidade(nome_produto, tamanho);
		try {
			if (validacao[0][0] != null) {
				codigoProduto = validacao[0][0];
				if (validacao[0][1] >= Integer.parseInt(quantidade)) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

}
