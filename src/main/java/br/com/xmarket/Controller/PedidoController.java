package br.com.xmarket.Controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.PedidoDao;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PedidoController {

	@Autowired
	private PedidoDao pedidoDao;

	@GetMapping("/dadospedido/{id_usuario}")
	@Operation(summary = "Pega todos os pedidos do usuário.")
	public ResponseEntity<String[][]> puxarPedidos(@PathVariable int id_usuario) throws ParseException {

		String[][] pedidos = pedidoDao.querybuscapedido(id_usuario);
		if (pedidos != null) {			
			return ResponseEntity.ok(pedidos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
	
