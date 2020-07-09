package br.com.rh.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/*CLASSE GENERICA PARA QQL RELATORIO*/
@Component
public class ReportUtil implements Serializable{

	private static final long serialVersionUID = 1L;

	/*RETORNA O NOSSO PDF EM BYTE PARA DOWLOAD NO NAVEGADOR*/
	public byte [] gerarRelatorio (List listDados, 
			String relatorio, ServletContext servletContext)
	
	throws Exception{
		
		/*CRIA A LISTA DE DADOS PARA O RELATORIO COM NOSSA LISTA DE OBJETOS PARA IMPRIMIR*/
		JRBeanCollectionDataSource jrdcds = new JRBeanCollectionDataSource(listDados);
				
		/*CARREGA O CAMINHO DO ARQUIVO JASPER COMPILADO*/
		String caminhoJasper = servletContext.getRealPath("relatorios")
				+ File.separator + relatorio + ".jasper";
		
		/*CARREGA O ARQUIBO JASPER PASSANDO OS DADOS*/
		JasperPrint impressoraJasper = JasperFillManager
				.fillReport(caminhoJasper, new HashMap(), jrdcds);
		
		/*EXPORTA PARA BYTE[] PARA FAZER O DOWLOAD DO PDF OU XLS POR EXEMPLO*/	
		return JasperExportManager.exportReportToPdf(impressoraJasper);
		
		
		
	}
	
	
}
