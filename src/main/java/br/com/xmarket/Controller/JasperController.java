package br.com.xmarket.Controller;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.xmarket.Services.JasperService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
 
@RestController
public class JasperController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
 
	@GetMapping("/generate-pdf")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		try { 
			// Instaciar a classe que possui os métodos de geração de relatório
			JasperService service = new JasperService();
 
			// Chama o método que gera um array de bytes com o
			// conteúdo do arquivo PDF
			byte[] pdf = service.gerarPDF(service.pdf());
 
			OutputStream outStream = response.getOutputStream();
			response.setHeader("Content-Disposition", "inline, filename=RelatorioPedidos.pdf");
			response.setContentType("application/pdf");
			response.setContentLength(pdf.length);
			outStream.write(pdf, 0, pdf.length);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}