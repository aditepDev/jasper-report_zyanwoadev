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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
		JasperPrint jasperPrint = null;

		try {

			  

			cowreportDAO cowrDAO = new cowreportDAO(db);
			id = Integer.parseInt(request.getParameter("cow_id"));

			
			ArrayList<HashMap<String, Object>> cowrList = cowrDAO.FindAllbyID(id);
			ArrayList<HashMap<String, Object>> CowBreeding = cowrDAO.FindCowBreeding(id);
			ArrayList<HashMap<String, Object>> cowbirth = cowrDAO.FindCowBirth(id);
			ArrayList<HashMap<String, Object>> cowVaccien = cowrDAO.FindCowVaccien(id);
			ArrayList<HashMap<String, Object>> cowMedicine = cowrDAO.FindCowMedicine(id);
			

			for (int i = 0; i < cowrList.size(); i++) {
				System.out.println("ชื่อวัว :  "+cowrList.get(i).get("cow_name") + "   ชื่อเจ้าของ :  " + cowrList.get(i).get("member_name") + "   "
						+(cowrList.get(i).get("member_surname")));   
			}
			
		
			db.close();
			String Pathfile = "C:\\Users\\aditep\\git\\jasper-report_zyanwoadev\\src\\JasperReport\\Cowreport\\";
			// path
			String reportFileName = "cowreport_main.jrxml"; //test reportFileName
			String reportPath = Pathfile + reportFileName;
			String targetFileName = reportFileName.replace(".jrxml", ".pdf");
			
			// sup 		
			String supcowreport_breedingtFileName = "supcowreport_breeding.jrxml"; 
			String supcowreport_breedingPath = Pathfile + supcowreport_breedingtFileName;
			
			String supcowreport_cow_vaccienFileName = "supcowreport_cow_vaccien.jrxml"; 
			String supcowreport_cow_vaccienPath = Pathfile + supcowreport_cow_vaccienFileName;
			
			String supcowreport_cowbirthFileName = "supcowreport_birth.jrxml";
			String supcowreport_cowbirthPath = Pathfile + supcowreport_cowbirthFileName;

			String supcowreport_cowMedicineFileName = "supcowreport_cow_medicine.jrxml";
			String supcowreport_cowMedicinePath = Pathfile + supcowreport_cowMedicineFileName;
			
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			JasperReport supcowreport_breedingParameter  = JasperCompileManager.compileReport(supcowreport_breedingPath);
			JasperReport supcowreport_cowbirthParameter = JasperCompileManager.compileReport(supcowreport_cowbirthPath);
			JasperReport supcowreport_cow_vaccienParameter = JasperCompileManager.compileReport(supcowreport_cow_vaccienPath);
			JasperReport supcowreport_cowMedicineParameter = JasperCompileManager.compileReport(supcowreport_cowMedicinePath);
			
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cowrList);	
			JRBeanCollectionDataSource supcowreport_breedingDataSource = new JRBeanCollectionDataSource(CowBreeding);
			JRBeanCollectionDataSource supcowreport_cowbirthDataSource = new JRBeanCollectionDataSource(cowbirth);
			JRBeanCollectionDataSource supcowreport_cow_vaccienDataSource = new JRBeanCollectionDataSource(cowVaccien);
			JRBeanCollectionDataSource supcowreport_cowMedicineDataSource = new JRBeanCollectionDataSource(cowMedicine);
			

			System.out.println("CowBreeding : " + CowBreeding.size());
			if (CowBreeding.size() == 0) {
				supcowreport_breedingtFileName = "supcowreport_Nobreeding.jrxml";
				supcowreport_breedingPath = Pathfile + supcowreport_breedingtFileName;
				supcowreport_breedingParameter = JasperCompileManager.compileReport(supcowreport_breedingPath);
				supcowreport_breedingDataSource = new JRBeanCollectionDataSource(cowrList);
			}
			System.out.println("cowbirth : " + cowbirth.size());
			if (cowbirth.size() == 0 || cowbirth.size() == 1) {
				for (int i = 0; i < cowbirth.size(); i++) {
					if (cowbirth.get(i).get("cow_name") == null && cowbirth.get(i).get("cow_sex") == null
							&& cowbirth.get(i).get("cow_birth") == null) {
						supcowreport_cowbirthFileName = "supcowreport_Nobirth.jrxml";
						supcowreport_cowbirthPath = Pathfile + supcowreport_cowbirthFileName;
						supcowreport_cowbirthParameter = JasperCompileManager
								.compileReport(supcowreport_cowbirthPath);
						supcowreport_cowbirthDataSource = new JRBeanCollectionDataSource(cowrList);
					}
				}

			}
			
				
			System.out.println("cowVaccien : " + cowVaccien.size());
			if (cowVaccien.size() == 0) {
				supcowreport_cow_vaccienFileName = "supcowreport_Nocow_vaccien.jrxml";
				supcowreport_cow_vaccienPath = Pathfile + supcowreport_cow_vaccienFileName;
				supcowreport_cow_vaccienParameter = JasperCompileManager
						.compileReport(supcowreport_cow_vaccienPath);
				supcowreport_cow_vaccienDataSource = new JRBeanCollectionDataSource(cowrList);
			}
		
			System.out.println("cowMedicine : " + cowMedicine.size());
			if (cowMedicine.size() == 0) {
				supcowreport_cowMedicineFileName = "supcowreport_Nocow_medicine.jrxml";
				supcowreport_cowMedicinePath = Pathfile + supcowreport_cowMedicineFileName;
				supcowreport_cowMedicineParameter = JasperCompileManager
						.compileReport(supcowreport_cowMedicinePath);
				supcowreport_cowMedicineDataSource = new JRBeanCollectionDataSource(cowrList);
			}
			
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
