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
		String sql ="select farm_name,farm_id as farmid,farm_id_card,farm_address,member_id as memberid ,\n" +
				"IFNULL(IF((select member_lat from zyanwoadev.tb_member where member_id = memberid ) = \"null\",\"-\",(select member_lat from zyanwoadev.tb_member where member_id = memberid )),\"-\") as member_lat,\n" +
				"IFNULL(IF((select member_long from zyanwoadev.tb_member where member_id = memberid ) = \"null\",\"-\",(select member_long from zyanwoadev.tb_member where member_id = memberid )),\"-\") as member_long,\n" +
				"IFNULL((select member_name from zyanwoadev.tb_member where member_id = memberid),\" \") as member_name,\n" +
				"IFNULL((select member_surname from zyanwoadev.tb_member where member_id = memberid),\" \") as member_surname,\n" +
				"IFNULL((select coop_id from zyanwoadev.tbd_farm_coop where farm_id = farmid),\"\") as coopid , \n" +
				"IFNULL((select coop_name from zyanwoadev.tb_cooperative where tb_cooperative.coop_id = coopid),\"\") as coop_name ,\n" +
				"IFNULL((select mccsub_member_id from  zyanwoadev.tbc_coop_farm where farm_id = tbd_farm.farm_id ),\"\") as mccsub_member_ids ,\n" +
				"IFNULL((select mccsub_member_canNumber from zyanwoadev.tbc_mccsub_member where mccsub_member_id = mccsub_member_ids ),\"\") as mccsub_member_canNumber,\n" +
				"IFNULL((select mcc_member_id from zyanwoadev.tbc_mccsub_member where mccsub_member_id = mccsub_member_ids ),\"-\") as mcc_member_ids,\n" +
				"IFNULL((select mcc_member_title from zyanwoadev.tbc_mcc_member where mcc_member_id = mcc_member_ids),\"-\") as mcc_member_title\n" +
				"from zyanwoadev.tbd_farm where farm_id = "+farmID+";";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}
}
