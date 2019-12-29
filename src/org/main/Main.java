package org.main;


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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;

public class Main {
	public static void main(String[] args) throws JRException {
		Main m = new Main();
		m.Select();
	}

	public void Select() {
		System.out.println("Select");
		
		try {
			Database db = new Database();
			cowreportDAO cowrDAO = new cowreportDAO(db);

			ArrayList<HashMap<String, Object>> cowrList = cowrDAO.FindAllbyID(3542);
			ArrayList<HashMap<String, Object>> cowrList2 = cowrDAO.FindCowBreeding(3542);
			for (int i = 0; i < cowrList2.size(); i++) {
				System.out.println(cowrList2.get(i).get("cow_breeding_log_mix_result")+" "+cowrList2.get(i).get("around"));
			}
			

			db.close();

		} catch (Exception e) {

		
			// TODO: handle exception
		}

	}
}
