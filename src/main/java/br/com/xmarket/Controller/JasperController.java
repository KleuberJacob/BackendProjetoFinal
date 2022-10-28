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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xmarket.DAO.PedidoDao;
import br.com.xmarket.Services.JasperService;

@RestController
public class JasperController {

	@Autowired
	private PedidoDao pedido;
	
	@Autowired
	private JasperService service;
	
	@CrossOrigin
	@GetMapping("/report/{code}/{acao}")
	public void exibirRelatorio01(@PathVariable("code") String code, @PathVariable("acao") String acao,
			HttpServletResponse response) throws IOException { // resposta em relaçao a nossa requisição, não retornando
																// nada

		byte[] bytes = service.exportarPDF(code); // meu relatorio sera transformado em uma array e sera retornado para
													// bytes

		response.setContentType(MediaType.APPLICATION_PDF_VALUE); // vai receber o tipo de midia, nesse caso o PDF
		if (acao.equals("v")) {
			// inline:fala com navegador que o pdf será aberto por ele,senão o navegador não
			// for compativel será feito download

			response.setHeader("Content-disposition", "inline;filename=relatorio-" + code + ".pdf");
		} else {
			// attachment: fala para o navegador que não deseja abrir o relatorio, mas quer
			// salvar em algum diretorio
			response.setHeader("Content-disposition", "attachment;filename=relatorio-" + code + ".pdf");
		}
		response.getOutputStream().write(bytes);
	}
	
	@CrossOrigin
	@GetMapping("/reportFiltered/{code}/{data_inicio}/{data_final}")
    public void exibirRelatorio02(@PathVariable("code") String code,     //pathvariable recebe o parametro a partir da url
    		@PathVariable("data_inicio") Date data_inicio,    //coloco required false, porque não é obrigatório para fazer busca no 09
    		@PathVariable("data_final") Date data_final,
            HttpServletResponse response) throws IOException { //resposta em relaçao a nossa requisição, não retornando nada

        service.addParams("dataInicial", data_inicio);  //como é string deve mandar essa condição para a string não chegar vazio
        service.addParams("dataFinal", data_final);
        byte[] bytes = service.exportarPDF(code);  // meu relatorio sera transformado em uma array e sera retornado para bytes
        response.setHeader("Content-disposition","inline;filename=relatorio-"+code+".pdf"); 
        response.setContentType(MediaType.APPLICATION_PDF_VALUE); //vai receber o tipo de midia, nesse caso o PDF
        response.getOutputStream().write(bytes);
            }


    //MEtodos responsaveis por preencher a lista 
    @ModelAttribute("data_inicio")
    public List <String> getDataInicio(){
        return pedido.findDataInicio();
    }

    @ModelAttribute("data_final")
    public List <String> getDataFinal(){
        return pedido.findDataFinal();
    }
}