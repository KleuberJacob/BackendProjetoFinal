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

	    private static final String JASPER_DIRETORIO = "classpath:jasper/";
	    private static final String JASPER_PREFIXO = "RelatorioPedidos_";
	    private static final String JASPER_SUFIXO = ".jasper";


	    @Autowired
	    private Connection connection;

	    private Map<String, Object> params = new HashMap<>();


	    public void addParams(String key,Object value) {
	        this.params.put(key, value);
	    }

	    public byte [] exportarPDF(String code) {
	    	System.out.println("exportar pdf");
	        byte[] bytes = null;
	        try {
	            File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
	            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(),params,connection);
	            bytes = JasperExportManager.exportReportToPdf(print);
	        } catch (FileNotFoundException  | JRException e) {
	            e.printStackTrace();
	        }
	        return bytes;
	    }

	}

