package br.com.xmarket.Controller;
//import java.io.IOException;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.PedidoDao;
import br.com.xmarket.Services.JasperService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JasperController {

	@Autowired
	private PedidoDao pedido;
	
	@Autowired
	private JasperService service;

	@GetMapping("/report/{code}")
	public void exibirRelatorio01(@PathVariable("code") String code, HttpServletResponse response) throws IOException {
		byte[] bytes = service.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition","inline;filename=relatorio-"+code+".pdf");
        response.getOutputStream().write(bytes);
	}

	@GetMapping("/reportFilter/{code}/{data_inicio}/{data_final}")
    public void exibirRelatorio02(@PathVariable("code") String code, 
    							  @PathVariable("data_inicio") Date data_inicio, 
    							  @PathVariable("data_final") Date data_final, 
    							  HttpServletResponse response) throws IOException {
        service.addParams("dataInicial", data_inicio);
        service.addParams("dataFinal", data_final);
        byte[] bytes = service.exportarPDF(code);
        response.setHeader("Content-disposition","inline;filename=relatorio-"+code+".pdf"); 
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.getOutputStream().write(bytes);
    }

    @ModelAttribute("data_inicio")
    public List <String> getDataInicio(){
        return pedido.findDataInicio();
    }

    @ModelAttribute("data_final")
    public List <String> getDataFinal(){
        return pedido.findDataFinal();
    }
}