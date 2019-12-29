package org.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.model.beans.cowreportModel;
import org.model.db.Database;
import org.model.impl.DAO;

public class cowreportDAO implements DAO<cowreportModel> {
	Database db = new Database();

	public cowreportDAO(Database db) {
		super();
		this.db = db;
	}

	@Override
	public int Add(cowreportModel bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Delete(cowreportModel bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Update(cowreportModel bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<cowreportModel> FindAll() {
		String sql = "";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		ArrayList<cowreportModel> cowrList = new ArrayList<cowreportModel>();
		System.out.println(quertList);
		for (Iterator<HashMap<String, Object>> iterator = quertList.iterator(); iterator.hasNext();) {
			HashMap<String, Object> next = iterator.next();
			cowreportModel cowr = MappingBeans(next);
			cowrList.add(cowr);
		}
		return cowrList;
	}

	public ArrayList<HashMap<String, Object>> FindAllbyID(int cowid) {
		String sql = "select \n" +
				"IFNULL(tbd_cow.cow_id,0) as cow_ids,\n" +
				"IFNULL(tbd_cow.cow_name,\"-\") as cow_name,\n" +
				"IFNULL(tbd_cow.farm_id,\"-\") as farm_id,\n" +
				"IFNULL(tbd_cow.zyan_code,\"-\") as zyan_code,\n" +
				"IFNULL(tbd_cow.cow_birth,\"-\") as cow_birth,\n" +
				"IFNULL(IF(tbd_cow.dpo_code = \" \",null,tbd_cow.dpo_code),tbd_cow.cow_ear_code) as cow_ear_code,\n" +
				"IFNULL(tbd_cow.cow_date_event,\"-\") as cow_date_event,\n" +
				"IFNULL(tbd_cow.cow_img,\"-\") as cow_img,\n" +
				"	IFNULL(tbd_breed.breed_ID,\"-\") as breed_ID,\n" +
				"	IFNULL(tbd_breed.breed_code,\" \") as breed_code,\n" +
				"	IFNULL(tbd_breed.breed_name,\" \") as breed_name,\n" +
				"-- \n" +
				" IFNULL(tb_member.member_id,\"-\") as member_ids,\n" +
				"	IFNULL(tb_member.member_name,\"-\") as member_name,\n" +
				"	IFNULL(tb_member.member_surname,\"-\") as member_surname,\n" +
				"	IFNULL(tb_member.member_addr,\"-\") as member_addr,\n" +
				"	\n" +
				"	IFNULL((select tb_member.member_lat from tb_member where member_lat != \"null\" and member_id = member_ids ),\"-\") as member_lat,\n" +
				"	IFNULL((select tb_member.member_long from tb_member where member_long != \"null\" and member_id = member_ids  ) ,\"-\") as member_long,\n" +
				"	IFNULL( tbd_cow.cow_fa_zyan_code, 0 ) AS cow_fa_zyancode,\n" +
				"	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_fa_zyancode ), \"-\" ) AS cow_fa_ID,\n" +
				"	IFNULL(IF(( SELECT dpo_code FROM tbd_cow WHERE cow_id = cow_fa_ID ) = \" \",null,( SELECT dpo_code FROM tbd_cow WHERE cow_id = cow_fa_ID )), IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_fa_ID ),\"-\") ) AS cow_fa_ear_code,\n" +
				"	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_fa_ID ), \"-\" ) AS cow_fa_breed_id,	\n" +
				"	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_fa_breed_id ), \" \" ) AS cow_fa_breed_code,\n" +
				"	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_fa_breed_id ), \" \" ) AS cow_fa_breed_name,\n" +
				"	\n" +
				"	IFNULL( tbd_cow.cow_ma_zyan_code, 0 ) AS cow_ma_zyancode,\n" +
				"	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_ma_zyancode ), \"-\" ) AS cow_ma_ID,\n" +
				"	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_ma_ID ), \"-\" ) AS cow_ma_ear_code,\n" +
				"	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_ma_ID ), \"-\" ) AS cow_ma_breed_id,	\n" +
				"	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_ma_breed_id ), \" \" ) AS cow_ma_breed_code,\n" +
				"	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_ma_breed_id ), \" \" ) AS cow_ma_breed_name,		\n" +
				"	\n" +
				"	\n" +
				"	IFNULL(( SELECT cow_fa_zyan_code FROM tbd_cow WHERE zyan_code = cow_ma_zyancode ), \"-\" ) AS  cow_grandfa_ma_zyan_code,\n" +
				"	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandfa_ma_zyan_code ), \"-\" ) AS  cow_grandfa_ma_ID,\n" +
				"	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandfa_ma_ID ), \"-\" ) AS cow_grandfa_ma_ear_code,\n" +
				"	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandfa_ma_ear_code ), \"-\" ) AS cow_grandfa_ma_breed_id,	\n" +
				"	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandfa_ma_breed_id ), \" \" ) AS cow_grandfa_ma_breed_code,\n" +
				"	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandfa_ma_breed_id ), \" \" ) AS cow_grandfa_ma_breed_name,			\n" +
				"		\n" +
				"		\n" +
				"	IFNULL(( SELECT cow_ma_zyan_code FROM tbd_cow WHERE zyan_code = cow_ma_zyancode ), \"-\" ) AS  cow_grandma_ma_zyan_code,\n" +
				"	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandma_ma_zyan_code ), \"-\" ) AS  cow_grandma_ma_ID,\n" +
				"	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandma_ma_ID ), \"-\" ) AS cow_grandma_ma_ear_code,\n" +
				"	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandma_ma_ear_code ), \"-\" ) AS cow_grandma_ma_breed_id,	\n" +
				"	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandma_ma_breed_id ), \" \" ) AS cow_grandma_ma_breed_code,\n" +
				"	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandma_ma_breed_id ), \" \" ) AS cow_grandma_ma_breed_name	,\n" +
				"	\n" +
				"	\n" +
				"		\n" +
				"	IFNULL(( SELECT cow_fa_zyan_code FROM tbd_cow WHERE zyan_code = cow_fa_zyancode ), \"-\" ) AS  cow_grandfa_fa_zyan_code,\n" +
				"	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandfa_fa_zyan_code ), \"-\" ) AS  cow_grandfa_fa_ID,\n" +
				"	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandfa_fa_ID ), \"-\" ) AS cow_grandfa_fa_ear_code,\n" +
				"	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandfa_fa_ear_code ), \"-\" ) AS cow_grandfa_fa_breed_id,	\n" +
				"	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandfa_fa_breed_id ), \" \" ) AS cow_grandfa_fa_breed_code,\n" +
				"	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandfa_fa_breed_id ), \" \" ) AS cow_grandfa_fa_breed_name,			\n" +
				"		\n" +
				"		\n" +
				"	IFNULL(( SELECT cow_ma_zyan_code FROM tbd_cow WHERE zyan_code = cow_fa_zyancode ), \"-\" ) AS  cow_grandma_fa_zyan_code,\n" +
				"	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandma_fa_zyan_code ), \"-\" ) AS  cow_grandma_fa_ID,\n" +
				"	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandma_fa_ID ), \"-\" ) AS cow_grandma_fa_ear_code,\n" +
				"	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandma_fa_ear_code ), \"-\" ) AS cow_grandma_fa_breed_id,	\n" +
				"	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandma_fa_breed_id ), \" \" ) AS cow_grandma_fa_breed_code,\n" +
				"	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandma_fa_breed_id ), \" \" ) AS cow_grandma_fa_breed_name	\n" +
				"	\n" +
				"	,\n" +
				"	IFNULL(tbd_farm.farm_name,\"-\") as farm_name,\n" +
				"	IFNULL(tbd_farm.farm_address,\"-\" )as farm_address, \n" +
				"	IFNULL(tbd_farm.farm_id_card,\"-\" ) as farm_id_card\n" +
				"	\n" +
				"FROM  tbd_cow\n" +
				"LEFT JOIN tbd_farm ON tbd_farm.farm_id = tbd_cow.farm_id\n" +
				"LEFT JOIN tbd_breed ON tbd_breed.breed_id = tbd_cow.breed_id\n" +
				"LEFT JOIN tb_member ON tbd_farm.member_id = tb_member.member_id\n" +
				"-- where  tbd_cow.cow_id BETWEEN 3500 AND 4000\n" +
				"where  tbd_cow.cow_id = "+cowid+" ; ";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
//		ArrayList<cowreportModel> cowrList = new ArrayList<cowreportModel>();
//		System.out.println(quertList);
		
		return quertList;
	}
	
	public ArrayList<HashMap<String, Object>> FindCowBreeding(int cowid){
		String sql = "select  *, row_number() over (PARTITION BY cow_report.around ORDER BY cow_report.cow_breeding_log_id ) as 'NO' from  ( \n" +
				"				select (around+1) as around,cow_breeding_log_date,cow_id,cow_breeding_log_mix_result,cow_fa_id as cow_fa_breeding_id ,(select cow_ear_code from  zyanwoadev.tbd_cow where cow_id = cow_fa_breeding_id) as cow_fa_ear_codes,'-' as sperm_code_name ,IFNULL( DATE_ADD( cow_breeding_log_date, INTERVAL 283 DAY ), 0 ) AS DATE_ADD,cow_breeding_log_id from  zyanwoadev.tbd_cow_breeding_log  \n" +
				"				UNION\n" +
				"				select (around+1) as around,cow_sperm_breeding_log_date,cow_id,cow_sperm_breeding_log_mix_result,sperm_id as sperm_ids,\"-\",(select sperm_code from  zyanwoadev.tbd_sperm where sperm_id = sperm_ids ) as sperm_code_name,\n" +
				"					IFNULL( DATE_ADD( cow_sperm_breeding_log_date, INTERVAL 283 DAY ), 0 ) AS DATE_ADD,cow_sperm_breeding_log_id from zyanwoadev.tbd_cow_sperm_breeding_log \n" +
				"				\n" +
				"					) as cow_report\n" +
				"				where cow_report.cow_id = "+ cowid +" ORDER BY cow_report.around ,cow_report.cow_breeding_log_id ;";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}
	
	public ArrayList<HashMap<String, Object>> FindCowBirth(int cowid){
		String sql = "select cow_birth_log_id,cow_birth_log_date,\n" +
				"(select cow_name FROM tbd_cow where cow_id = cow_birth_log_id)  as lok_cow_name,\n" +
				"(SELECT cow_ear_code from tbd_cow where cow_id = cow_birth_log_id) as lok_cow_ear_code   ,\n" +
				"(select cow_sex from tbd_cow where cow_id = cow_birth_log_id) as lok_cow_sex\n" +
				"from tbd_cow_birth_log where cow_id=  "+ cowid +"; " ;
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}
	
	
	@Override
	public cowreportModel FindByID(cowreportModel bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public cowreportModel FindByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public cowreportModel MappingBeans(HashMap<String, Object> map) {
		cowreportModel crModel = new cowreportModel();
		// cow
		crModel.setCow_ids(Integer.parseInt(map.get("cow_ids").toString()));
		crModel.setCow_name(map.get("cow_name").toString());
		crModel.setZyan_code(map.get("zyan_code").toString());
		crModel.setCow_birth(map.get("cow_birth").toString());
		crModel.setCow_ear_code(map.get("cow_ear_code").toString());
		crModel.setCow_date_event(map.get("cow_date_event").toString());
		crModel.setCow_img(map.get("cow_img").toString());
		
		
		crModel.setCow_fa_zyancode(map.get("cow_fa_zyancode").toString());
		crModel.setCow_fa_ID (map.get("cow_fa_ID").toString());
		crModel.setCow_fa_ear_code(map.get("cow_fa_ear_code").toString());
		crModel.setCow_fa_breed_id (map.get("cow_fa_breed_id").toString());
		crModel.setCow_fa_breed_code(map.get("cow_fa_breed_code").toString());
		crModel.setCow_fa_breed_name(map.get("cow_fa_breed_name").toString());
		
		crModel.setCow_ma_zyancode(map.get("cow_ma_zyancode").toString());
		crModel.setCow_ma_ID (map.get("cow_ma_ID").toString());
		crModel.setCow_ma_ear_code(map.get("cow_ma_ear_code").toString());
		crModel.setCow_ma_breed_id (map.get("cow_ma_breed_id").toString());
		crModel.setCow_ma_breed_code(map.get("cow_ma_breed_code").toString());
		crModel.setCow_ma_breed_name(map.get("cow_ma_breed_name").toString());
		

		crModel.setCow_grandfa_ma_zyan_code(map.get("cow_grandfa_ma_zyan_code").toString());
		crModel.setCow_grandfa_ma_ID (map.get("cow_grandfa_ma_ID").toString());
		crModel.setCow_grandfa_ma_ear_code(map.get("cow_grandfa_ma_ear_code").toString());
		crModel.setCow_grandfa_ma_breed_id (map.get("cow_grandfa_ma_breed_id").toString());
		crModel.setCow_grandfa_ma_breed_code(map.get("cow_grandfa_ma_breed_code").toString());
		crModel.setCow_grandfa_ma_breed_name(map.get("cow_grandfa_ma_breed_name").toString());
		
		crModel.setCow_grandma_ma_zyan_code(map.get("cow_grandma_ma_zyan_code").toString());
		crModel.setCow_grandma_ma_ID (map.get("cow_grandma_ma_ID").toString());
		crModel.setCow_grandma_ma_ear_code(map.get("cow_grandma_ma_ear_code").toString());
		crModel.setCow_grandma_ma_breed_id (map.get("cow_grandma_ma_breed_id").toString());
		crModel.setCow_grandma_ma_breed_code(map.get("cow_grandma_ma_breed_code").toString());	
		crModel.setCow_grandma_ma_breed_name(map.get("cow_grandma_ma_breed_name").toString());
		
		crModel.setCow_grandfa_fa_zyan_code(map.get("cow_grandfa_fa_zyan_code").toString());
		crModel.setCow_grandfa_fa_ID (map.get("cow_grandfa_fa_ID").toString());
		crModel.setCow_grandfa_fa_ear_code(map.get("cow_grandfa_fa_ear_code").toString());
		crModel.setCow_grandfa_fa_breed_id (map.get("cow_grandfa_fa_breed_id").toString());
		crModel.setCow_grandfa_fa_breed_code(map.get("cow_grandfa_fa_breed_code").toString());
		crModel.setCow_grandfa_fa_breed_name(map.get("cow_grandfa_fa_breed_name").toString());
		
		crModel.setCow_grandma_fa_zyan_code(map.get("cow_grandma_fa_zyan_code").toString());
		crModel.setCow_grandma_fa_ID (map.get("cow_grandma_fa_ID").toString());
		crModel.setCow_grandma_fa_ear_code(map.get("cow_grandma_fa_ear_code").toString());
		crModel.setCow_grandma_fa_breed_id (map.get("cow_grandma_fa_breed_id").toString());
		crModel.setCow_grandma_fa_breed_code(map.get("cow_grandma_fa_breed_code").toString());	
		crModel.setCow_grandma_fa_breed_name(map.get("cow_grandma_fa_breed_name").toString());

		System.out.println("cow");
	
		// farm
		crModel.setFarm_address(map.get("farm_address").toString());
		crModel.setFarm_name(map.get("farm_name").toString());
		crModel.setFarm_id(Integer.parseInt(map.get("farm_id").toString()));
		crModel.setFarm_id_card(map.get("farm_id_card").toString());
		System.out.println("farm");

		
		// breed
		
		crModel.setBreed_ID(Integer.parseInt(map.get("breed_ID").toString()));
		crModel.setBreed_code(map.get("breed_code").toString());
		crModel.setBreed_name(map.get("breed_name").toString());

		// member

		crModel.setMember_ids(Integer.parseInt(map.get("member_ids").toString()));
		crModel.setMember_name(map.get("member_name").toString());
		crModel.setMember_surname(map.get("member_surname").toString());
		crModel.setMember_addr(map.get("member_addr").toString());
		crModel.setMember_lat(map.get("member_lat").toString());
		crModel.setMember_long(map.get("member_long").toString());

		
		System.out.println("member");
		return crModel;
	}

}
