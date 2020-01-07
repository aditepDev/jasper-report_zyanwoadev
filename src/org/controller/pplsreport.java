package org.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.model.beans.cowreportModel;
import org.model.dao.PplsReportDAO;
import org.model.dao.cowreportDAO;
import org.model.db.Database;
import org.model.db.JDBCConnection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**

/**
 * Servlet implementation class cowreport
 */
@WebServlet("/pplsreport")
public class pplsreport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
response.setContentType("text/html;charset=UTF-8");
		
		Database db = new Database();
		int id = 10;
		JasperPrint jasperPrint = null;

		try {

			  

			PplsReportDAO pplsrDAO = new PplsReportDAO(db);
			id = Integer.parseInt(request.getParameter("farm_id"));

			
			ArrayList<HashMap<String, Object>> pplsrList = pplsrDAO.FindPplsID(id);
			
			for (int i = 0; i < pplsrList.size(); i++) {
				System.out.println(pplsrList.get(i).get("member_lat")+" "+pplsrList.get(i).get("member_long"));
			}
//			System.out.println(cowrList.get("cow_ids"));
//			ArrayList<cowreportModel> cowrList =  cowrDAO.FindAll();
				
//			System.out.println("id = "+id);
//
//			ArrayList<Object> model = new ArrayList<Object>();
//			String reportFileName = null;
//			if(typecowreport == 0) {
////				model.addAll(cowrList);
//				reportFileName = "cowreport.jrxml";
//			}else {
////				model.addAll(cowrthaiList);
//				reportFileName = "cowreport_thailand.jrxml";
//				}
//			System.out.println(model);
//			
			db.close();
			String Pathfile = "C:\\Users\\aditep\\git\\jasper-report_zyanwoadev\\src\\JasperReport\\PPLSReport\\";
			// path
			String reportFileName = "PPLS_report.jrxml"; //test reportFileName
			String reportPath = Pathfile + reportFileName;
			String targetFileName = reportFileName.replace(".jrxml", ".pdf");
			
			// sup 		
			String PPLS_subreportFileName = "PPLS_subreport.jrxml"; 
			String PPLS_subreportPath = Pathfile + PPLS_subreportFileName;
		
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			JasperReport PPLS_subreportParameter  = JasperCompileManager.compileReport(PPLS_subreportPath);
	
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pplsrList);	
//			JRBeanCollectionDataSource supcowreport_breedingDataSource = new JRBeanCollectionDataSource(cowrList2);
	
			 Map<String, Object> parameters = new HashMap<>();
		        parameters.put("farmID", id);
		        parameters.put("PPLS_subreportParameter", PPLS_subreportParameter);
		        parameters.put("PPLS_subreportDATA_SOURCE", dataSource);
		
		        
//		  
		  
		        

//			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCConnection.getJDBCConnection());  //test jaspre
			
			 
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource); 

			
			ServletOutputStream outputstream = response.getOutputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
			response.setContentType("application/pdf");
			outputstream.write(byteArrayOutputStream.toByteArray());
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName);

			
			// exportReportToPdfFile
			String path = "C:\\Users\\aditep\\desktop";
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");

	
			
			
			outputstream.flush();
			outputstream.close();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pplsreport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
