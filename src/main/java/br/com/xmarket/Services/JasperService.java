package br.com.xmarket.Services;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.JasperRunManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
	
	@Service
	public class JasperService {

	    //caminho para localizar meus relatorios
	    private static final String JASPER_DIRETORIO = "classpath:jasper/";
	    private static final String JASPER_PREFIXO = "RelatorioPedidos_";    //Prefixo será os nomes iniciais do relatorio
	    private static final String JASPER_SUFIXO = ".jasper";  //sera a extensao do arquivo


	    @Autowired
	    private Connection connection;

	    //Metodos para os parametros
	    private Map<String, Object> params = new HashMap<>();


	    public void addParams(String key,Object value) {
	        this.params.put(key, value);
	    }

	    //Metodo para gerar o relatorio
	    public byte [] exportarPDF(String code) {
	        byte[] bytes = null;
	        try {
	            File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
	            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(),params,connection); //acessando o relatorio
	            bytes = JasperExportManager.exportReportToPdf(print);
	        } catch (FileNotFoundException  | JRException e) {
	            e.printStackTrace();
	        }
	        return bytes;
	    }

	}

