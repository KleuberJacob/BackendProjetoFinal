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

import br.com.xmarket.DAO.PedidoDao;
import br.com.xmarket.DAO.UsuarioDao;
import br.com.xmarket.Model.Pedido;
import br.com.xmarket.Model.Usuario;

@RestController
public class PedidoController {

	@Autowired
	private PedidoDao pedidoDao;

	@CrossOrigin
	@GetMapping("/dadospedido/{id_usuario}")
	public ResponseEntity<String[][]> puxarPedidos(@PathVariable int id_usuario) throws ParseException {

		String[][] pedidos = pedidoDao.querybuscapedido(id_usuario);
		if (pedidos != null) {
			System.out.println("teste");
			return ResponseEntity.ok(pedidos);
		} else {
			System.out.println(pedidos);
			return ResponseEntity.notFound().build();
		}
	}
}
	
