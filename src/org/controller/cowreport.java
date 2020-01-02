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
@WebServlet("/cowreport")
public class cowreport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
response.setContentType("text/html;charset=UTF-8");
		
		Database db = new Database();
		int id = 10;
		int typecowreport = 0;
		JasperPrint jasperPrint = null;

		try {

			  

			cowreportDAO cowrDAO = new cowreportDAO(db);
			typecowreport = Integer.parseInt(request.getParameter("cow"));
			id = Integer.parseInt(request.getParameter("cow_id"));

			
			ArrayList<HashMap<String, Object>> cowrList = cowrDAO.FindAllbyID(id);
			ArrayList<HashMap<String, Object>> cowrList2 = cowrDAO.FindCowBreeding(id);
			ArrayList<HashMap<String, Object>> cowbirth = cowrDAO.FindCowBirth(id);
			ArrayList<HashMap<String, Object>> cowVaccien = cowrDAO.FindCowVaccien(id);
			ArrayList<HashMap<String, Object>> cowMedicine = cowrDAO.FindCowMedicine(id);
			
			for (int i = 0; i < cowbirth.size(); i++) {
				System.out.println(cowbirth.get(i).get("cow_name")+" "+cowbirth.get(i).get("cow_sex"));
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
			
			// path
			String reportFileName = "cowreport_main.jrxml"; //test reportFileName
			String reportPath = "E:\\SpringWebMVC_JasperReport\\src\\" + reportFileName;
			String targetFileName = reportFileName.replace(".jrxml", ".pdf");
			
			// sup 		
			String supcowreport_breedingtFileName = "supcowreport_breeding.jrxml"; 
			String supcowreport_breedingPath = "E:\\SpringWebMVC_JasperReport\\src\\" + supcowreport_breedingtFileName;
			
			String supcowreport_cow_vaccienFileName = "supcowreport_cow_vaccien.jrxml"; 
			String supcowreport_cow_vaccienPath = "E:\\SpringWebMVC_JasperReport\\src\\" + supcowreport_cow_vaccienFileName;

			String supcowreport_cowbirthFileName = "supcowreport_birth.jrxml";
			String supcowreport_cowbirthPath = "E:\\SpringWebMVC_JasperReport\\src\\" + supcowreport_cowbirthFileName;

			String supcowreport_cowMedicineFileName = "supcowreport_cow_medicine.jrxml";
			String supcowreport_cowMedicinePath = "E:\\SpringWebMVC_JasperReport\\src\\" + supcowreport_cowMedicineFileName;
			
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			JasperReport supcowreport_breedingParameter  = JasperCompileManager.compileReport(supcowreport_breedingPath);
			JasperReport supcowreport_cowbirthParameter = JasperCompileManager.compileReport(supcowreport_cowbirthPath);
			JasperReport supcowreport_cow_vaccienParameter = JasperCompileManager.compileReport(supcowreport_cow_vaccienPath);
			JasperReport supcowreport_cowMedicineParameter = JasperCompileManager.compileReport(supcowreport_cowMedicinePath);
			
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cowrList);	
			JRBeanCollectionDataSource supcowreport_breedingDataSource = new JRBeanCollectionDataSource(cowrList2);
			JRBeanCollectionDataSource supcowreport_cowbirthDataSource = new JRBeanCollectionDataSource(cowbirth);
			JRBeanCollectionDataSource supcowreport_cow_vaccienDataSource = new JRBeanCollectionDataSource(cowVaccien);
			JRBeanCollectionDataSource supcowreport_cowMedicineDataSource = new JRBeanCollectionDataSource(cowMedicine);
			
			 Map<String, Object> parameters = new HashMap<>();
		        parameters.put("ID", id);
		        parameters.put("Subreport_cowbreeding_Parameter", supcowreport_breedingParameter);
		        parameters.put("SUBREPORT_cowbreeding_DATA_SOURCE", supcowreport_breedingDataSource);
		        parameters.put("Subreport_cowbirth_Parameter", supcowreport_cowbirthParameter);
		        parameters.put("SUBREPORT_cowbirth_DATA_SOURCE", supcowreport_cowbirthDataSource);
		        parameters.put("Subreport_ cowVaccien_Parameter", supcowreport_cow_vaccienParameter);
		        parameters.put("SUBREPORT_cowVaccien_DATA_SOURCE", supcowreport_cow_vaccienDataSource);
		        parameters.put("Subreport_cowMedicine_Parameter", supcowreport_cowMedicineParameter);
		        parameters.put("SUBREPORT_cowMedicine_DATA_SOURCE", supcowreport_cowMedicineDataSource);
		        
		        
//		  
		  
		        

//			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, JDBCConnection.getJDBCConnection());  //test jaspre
			
			 
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource); 
//			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource2); 
			
			ServletOutputStream outputstream = response.getOutputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
			response.setContentType("application/pdf");
			outputstream.write(byteArrayOutputStream.toByteArray());
			response.setHeader("Cache-Control", "max-age=0");
			response.setHeader("Content-Disposition", "attachment; filename=" + targetFileName);

			
			// exportReportToPdfFile
			String path = "C:\\Users\\topko\\desktop";
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
    public cowreport() {
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
