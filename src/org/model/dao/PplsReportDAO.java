package org.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.model.db.Database;

public class PplsReportDAO  {
	
	Database db = new Database();

	public PplsReportDAO(Database db) {
		super();
		this.db = db;
	}

	public ArrayList<HashMap<String, Object>> FindPplsID(int farmID){
		String sql =  "select farm_name,farm_id as farmid,farm_id_card,farm_address,member_id as memberid ,\n" +
				"IFNULL((select member_lat from zyanwoadev.tb_member where member_id = memberid ),\"-\") as member_lat,\n" +
				"IFNULL((select member_long from zyanwoadev.tb_member where member_id = memberid ) ,\"-\")as member_long,\n" +
				"IFNULL((select member_name from zyanwoadev.tb_member where member_id = memberid),\" \") as member_name,\n" +
				"IFNULL((select member_surname from zyanwoadev.tb_member where member_id = memberid),\" \") as member_surname,\n" +
				"IFNULL((select coop_id from zyanwoadev.tbd_farm_coop where farm_id = farmid),\"\") as coopid , \n" +
				"IFNULL((select coop_name from zyanwoadev.tb_cooperative where tb_cooperative.coop_id = coopid),\"\") as coop_name \n" +
				"from zyanwoadev.tbd_farm where farm_id = "+farmID+";";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}
}
