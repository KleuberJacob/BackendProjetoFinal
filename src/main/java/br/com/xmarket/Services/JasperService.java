package br.com.xmarket.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

public class JasperService {
		// Método para realizar a conexão com o banco de dados
		public Connection getConexao() throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/xmarket", "root", "mysql");
			return conexao;
		}
	 
		// Método para ser executado em JSE
		public String pdf() {
			System.out.println("Inicio");
			JasperService geraRelatorio = new JasperService();
			String path = "C:\\Users\\00787728\\Desktop\\Araujo\\BackProjetoFinal\\BackendProjetoFinal\\src\\main\\resources\\jasper\\";
			geraRelatorio.gerarPDF(path);
			System.out.println("Fim");
			return path;
		}
	 
		public byte[] gerarPDF(String diretorio) {
			byte[] retorno = null;
			String relatorio = diretorio + "RelatorioPedidos.jrxml";
			try {
				// Faz a compilação do relatório
				JasperReport jasperReport = JasperCompileManager.compileReport(relatorio);
	 
				// Cria o mapa de parâmetros que será enviado ao relatório
				//HashMap<String, Object> paramatros = new HashMap<String, Object>();
	 
				// Faz o apontamento para a imagem que aparece no top do relatório
				//paramatros.put("logo", imagem);
	 
				// Preenche os dados do relatório
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, getConexao());
	 
				// Objeto para a ser retornado
				retorno = JasperRunManager.runReportToPdf(jasperReport, null, getConexao());
	 
				// Gera o arquivo PDF no caminho especificado
				JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\XMarket\\RelatorioPedidos.pdf");
	 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return retorno;
		}
}
