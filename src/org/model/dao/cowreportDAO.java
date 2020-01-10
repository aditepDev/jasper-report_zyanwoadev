package org.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.model.beans.cowreportModel;
import org.model.db.Database;
import org.model.impl.DAO;

import java.util.ArrayList;
import java.util.HashMap;

public class cowreportDAO {
	Database db = new Database();

	public cowreportDAO(Database db) {
		super();
		this.db = db;
	}

	public ArrayList<HashMap<String, Object>> FindAllbyID(int cowid) {
		String sql = "select \n" + "IFNULL(tbd_cow.cow_id,0) as cow_ids,\n"
				+ "IFNULL(tbd_cow.cow_name,\"-\") as cow_name,\n" + "IFNULL(tbd_cow.farm_id,\"-\") as farm_id,\n"
				+ "IFNULL(tbd_cow.zyan_code,\"-\") as zyan_code,\n" + "IFNULL(tbd_cow.cow_birth,\"-\") as cow_birth,\n"
				+ "IFNULL(IF(tbd_cow.dpo_code = \" \",null,tbd_cow.dpo_code),tbd_cow.cow_ear_code) as cow_ear_code,\n"
				+ "IFNULL(tbd_cow.cow_date_event,\"-\") as cow_date_event,\n"
				+ "IFNULL(tbd_cow.cow_img,\"-\") as cow_img,\n" + "	IFNULL(tbd_breed.breed_ID,\"-\") as breed_ID,\n"
				+ "	IFNULL(tbd_breed.breed_code,\" \") as breed_code,\n"
				+ "	IFNULL(tbd_breed.breed_name,\" \") as breed_name,\n" + "-- \n"
				+ " IFNULL(tb_member.member_id,\"-\") as member_ids,\n"
				+ "	IFNULL(tb_member.member_name,\"-\") as member_name,\n"
				+ "	IFNULL(tb_member.member_surname,\"-\") as member_surname,\n"
				+ "	IFNULL(tb_member.member_addr,\"-\") as member_addr,\n" + "	\n"
				+ "	IFNULL((select tb_member.member_lat from tb_member where member_lat != \"null\" and member_id = member_ids ),\"-\") as member_lat,\n"
				+ "	IFNULL((select tb_member.member_long from tb_member where member_long != \"null\" and member_id = member_ids  ) ,\"-\") as member_long,\n"
				+ "	IFNULL( tbd_cow.cow_fa_zyan_code, 0 ) AS cow_fa_zyancode,\n"
				+ "	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_fa_zyancode ), \"-\" ) AS cow_fa_ID,\n"
				+ "	IFNULL(IF(( SELECT dpo_code FROM tbd_cow WHERE cow_id = cow_fa_ID ) = \" \",null,( SELECT dpo_code FROM tbd_cow WHERE cow_id = cow_fa_ID )), IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_fa_ID ),\"-\") ) AS cow_fa_ear_code,\n"
				+ "	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_fa_ID ), \"-\" ) AS cow_fa_breed_id,	\n"
				+ "	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_fa_breed_id ), \" \" ) AS cow_fa_breed_code,\n"
				+ "	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_fa_breed_id ), \" \" ) AS cow_fa_breed_name,\n"
				+ "	\n" + "	IFNULL( tbd_cow.cow_ma_zyan_code, 0 ) AS cow_ma_zyancode,\n"
				+ "	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_ma_zyancode ), \"-\" ) AS cow_ma_ID,\n"
				+ "	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_ma_ID ), \"-\" ) AS cow_ma_ear_code,\n"
				+ "	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_ma_ID ), \"-\" ) AS cow_ma_breed_id,	\n"
				+ "	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_ma_breed_id ), \" \" ) AS cow_ma_breed_code,\n"
				+ "	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_ma_breed_id ), \" \" ) AS cow_ma_breed_name,		\n"
				+ "	\n" + "	\n"
				+ "	IFNULL(( SELECT cow_fa_zyan_code FROM tbd_cow WHERE zyan_code = cow_ma_zyancode ), \"-\" ) AS  cow_grandfa_ma_zyan_code,\n"
				+ "	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandfa_ma_zyan_code ), \"-\" ) AS  cow_grandfa_ma_ID,\n"
				+ "	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandfa_ma_ID ), \"-\" ) AS cow_grandfa_ma_ear_code,\n"
				+ "	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandfa_ma_ear_code ), \"-\" ) AS cow_grandfa_ma_breed_id,	\n"
				+ "	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandfa_ma_breed_id ), \" \" ) AS cow_grandfa_ma_breed_code,\n"
				+ "	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandfa_ma_breed_id ), \" \" ) AS cow_grandfa_ma_breed_name,			\n"
				+ "		\n" + "		\n"
				+ "	IFNULL(( SELECT cow_ma_zyan_code FROM tbd_cow WHERE zyan_code = cow_ma_zyancode ), \"-\" ) AS  cow_grandma_ma_zyan_code,\n"
				+ "	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandma_ma_zyan_code ), \"-\" ) AS  cow_grandma_ma_ID,\n"
				+ "	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandma_ma_ID ), \"-\" ) AS cow_grandma_ma_ear_code,\n"
				+ "	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandma_ma_ear_code ), \"-\" ) AS cow_grandma_ma_breed_id,	\n"
				+ "	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandma_ma_breed_id ), \" \" ) AS cow_grandma_ma_breed_code,\n"
				+ "	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandma_ma_breed_id ), \" \" ) AS cow_grandma_ma_breed_name	,\n"
				+ "	\n" + "	\n" + "		\n"
				+ "	IFNULL(( SELECT cow_fa_zyan_code FROM tbd_cow WHERE zyan_code = cow_fa_zyancode ), \"-\" ) AS  cow_grandfa_fa_zyan_code,\n"
				+ "	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandfa_fa_zyan_code ), \"-\" ) AS  cow_grandfa_fa_ID,\n"
				+ "	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandfa_fa_ID ), \"-\" ) AS cow_grandfa_fa_ear_code,\n"
				+ "	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandfa_fa_ear_code ), \"-\" ) AS cow_grandfa_fa_breed_id,	\n"
				+ "	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandfa_fa_breed_id ), \" \" ) AS cow_grandfa_fa_breed_code,\n"
				+ "	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandfa_fa_breed_id ), \" \" ) AS cow_grandfa_fa_breed_name,			\n"
				+ "		\n" + "		\n"
				+ "	IFNULL(( SELECT cow_ma_zyan_code FROM tbd_cow WHERE zyan_code = cow_fa_zyancode ), \"-\" ) AS  cow_grandma_fa_zyan_code,\n"
				+ "	IFNULL(( SELECT cow_id FROM tbd_cow WHERE zyan_code = cow_grandma_fa_zyan_code ), \"-\" ) AS  cow_grandma_fa_ID,\n"
				+ "	IFNULL(( SELECT cow_ear_code FROM tbd_cow WHERE cow_id = cow_grandma_fa_ID ), \"-\" ) AS cow_grandma_fa_ear_code,\n"
				+ "	IFNULL(( SELECT breed_id FROM tbd_cow WHERE cow_id = cow_grandma_fa_ear_code ), \"-\" ) AS cow_grandma_fa_breed_id,	\n"
				+ "	IFNULL(( SELECT breed_code FROM tbd_breed WHERE breed_id = cow_grandma_fa_breed_id ), \" \" ) AS cow_grandma_fa_breed_code,\n"
				+ "	IFNULL(( SELECT breed_name FROM tbd_breed WHERE breed_id = cow_grandma_fa_breed_id ), \" \" ) AS cow_grandma_fa_breed_name	\n"
				+ "	\n" + "	,\n" + "	IFNULL(tbd_farm.farm_name,\"-\") as farm_name,\n"
				+ "	IFNULL(tbd_farm.farm_address,\"-\" )as farm_address, \n"
				+ "	IFNULL(tbd_farm.farm_id_card,\"-\" ) as farm_id_card\n" + "	\n" + "FROM  tbd_cow\n"
				+ "LEFT JOIN tbd_farm ON tbd_farm.farm_id = tbd_cow.farm_id\n"
				+ "LEFT JOIN tbd_breed ON tbd_breed.breed_id = tbd_cow.breed_id\n"
				+ "LEFT JOIN tb_member ON tbd_farm.member_id = tb_member.member_id\n"
				+ "-- where  tbd_cow.cow_id BETWEEN 3500 AND 4000\n" + "where  tbd_cow.cow_id = " + cowid + " ; ";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
//		ArrayList<cowreportModel> cowrList = new ArrayList<cowreportModel>();
//		System.out.println(quertList);

		return quertList;
	}

	public ArrayList<HashMap<String, Object>> FindCowBreeding(int cowid) {
		String sql = "select *, row_number() over (PARTITION BY cow_report.around ORDER BY cow_report.cow_breeding_log_date ) as \"NO\" from (\n"
				+ "select (around+1) as around ,cow_breeding_log_date,cow_id,cow_breeding_log_mix_result,cow_fa_id as cow_fa_breeding_id ,(select cow_ear_code from zyanwoadev.tbd_cow where cow_id = cow_fa_breeding_id) as cow_fa_ear_codes,\"-\" as sperm_code_name ,IFNULL( DATE_ADD( cow_breeding_log_date, INTERVAL 283 DAY ), 0 ) AS DATE_ADD,cow_breeding_log_id from zyanwoadev.tbd_cow_breeding_log\n"
				+ "UNION\n"
				+ "select (around+1) as around,cow_sperm_breeding_log_date,cow_id,cow_sperm_breeding_log_mix_result,sperm_id as sperm_ids,\"-\",(select sperm_code from zyanwoadev.tbd_sperm where sperm_id = sperm_ids ) as sperm_code_name,\n"
				+ "IFNULL( DATE_ADD( cow_sperm_breeding_log_date, INTERVAL 283 DAY ), 0 ) AS DATE_ADD,cow_sperm_breeding_log_id from zyanwoadev.tbd_cow_sperm_breeding_log\n"
				+ ") as cow_report\n" + "where cow_report.cow_id = " + cowid
				+ " ORDER BY cow_report.cow_breeding_log_date,cow_report.around ";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}

	public ArrayList<HashMap<String, Object>> FindCowBirth(int cowid) {
		String sql = "SELECT\n" + "	*,\n" + "IF\n"
				+ "	( DATE_ADD( l_1.breedingLogDate, INTERVAL 283 DAY ) != 'Null', DATE_ADD( l_1.breedingLogDate, INTERVAL 283 DAY ), '-' ) AS calve,\n"
				+ "IFNULL(ABS( DATEDIFF( breedingLogDate, breedingLogDate1 )),0)AS farCalver  ,\n" + "	a.*\n"
				+ "FROM\n" + "	(\n" + "	SELECT\n" + "	IF\n"
				+ "		( cow_id != 'Null', cow_id, 'Null' ) AS cow_id,\n" + "	IF\n"
				+ "		( cow_id != 'Null', cowIdMom, NULL ) AS cowIdMom,\n" + "		cow_birth,\n"
				+ "		cow_sex,\n" + "		cow_name,\n" + "		cow_ear_code,\n" + "	IF\n"
				+ "		( cow_id != 'Null', ( @row_number := @row_number + 1 ), NULL ) AS aroundBirth,\n" + "	IF\n"
				+ "		( cow_id != 'Null', ( @row_number1 := @row_number1 + 1 ), NULL ) AS checkAround \n" + "	FROM\n"
				+ "		(\n" + "		SELECT\n" + "			cow1.cow_birth,\n" + "			cow1.cow_sex,\n"
				+ "			cow1.cow_name,\n" + "			cow1.cow_ear_code,\n" + "			cow1.cow_id,\n"
				+ "			cow.cow_id AS cowIdMom \n" + "		FROM\n" + "			zyanwoadev.tbd_cow AS cow\n"
				+ "			LEFT JOIN zyanwoadev.tbd_cow AS cow1 ON ( cow.zyan_code = cow1.cow_ma_zyan_code ) \n"
				+ "		WHERE\n" + "			cow.cow_id = " + cowid + "\n" + "		ORDER BY\n"
				+ "			cow1.cow_birth \n" + "		) AS query_1,\n"
				+ "		( SELECT @row_number := - 1 ) AS num_1,\n" + "		( SELECT @row_number1 := - 2 ) AS num_2 \n"
				+ "	) AS a\n" + "	LEFT JOIN (\n" + "	SELECT\n" + "		* \n" + "	FROM\n" + "		(\n"
				+ "		SELECT\n" + "			cow_id AS breedingCowIdMom,\n" + "			around AS breedingAround,\n"
				+ "			cow_breeding_log_date AS breedingLogDate,\n" + "			cow_breeding_log_mix_result \n"
				+ "		FROM\n" + "			zyanwoadev.tbd_cow_breeding_log UNION\n" + "		SELECT\n"
				+ "			cow_id,\n" + "			around,\n" + "			cow_sperm_breeding_log_date,\n"
				+ "			cow_sperm_breeding_log_mix_result \n" + "		FROM\n"
				+ "			zyanwoadev.tbd_cow_sperm_breeding_log \n" + "		) AS s \n" + "	WHERE\n"
				+ "		s.cow_breeding_log_mix_result = 'ท้อง' \n" + "	ORDER BY\n" + "		s.breedingAround,\n"
				+ "		s.breedingLogDate \n"
				+ "	) AS l_1 ON ( a.cowIdMom = l_1.breedingCowIdMom AND a.aroundBirth = l_1.breedingAround )\n"
				+ "	LEFT JOIN ( SELECT cow_id AS breedingCowIdMom, around AS breedingAround, cow_breeding_log_date AS breedingLogDate1 FROM zyanwoadev.tbd_cow_breeding_log UNION SELECT cow_id, around, cow_sperm_breeding_log_date FROM zyanwoadev.tbd_cow_sperm_breeding_log ) AS ss ON ( a.cowIdMom = ss.breedingCowIdMom AND a.checkAround = ss.breedingAround ) \n"
				+ "GROUP BY\n" + "	a.cow_id";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}

	public ArrayList<HashMap<String, Object>> FindCowVaccien(int cowid) {
		String sql = "select row_number() over (ORDER BY cow_vaccien_date_event) as \"NO\",cow_id,cow_vaccien_date_event,cow_vaccien_name FROM tbd_cow_vaccien where cow_id = "
				+ cowid + "; ";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}

	public ArrayList<HashMap<String, Object>> FindCowMedicine(int cowid) {
		String sql = "select *,row_number() over (ORDER BY cow_medicine_date_event) as \"NO\" from zyanwoadev.tbd_cow_medicine  where  cow_id = "
				+ cowid + "; ";
		ArrayList<HashMap<String, Object>> quertList = db.queryList(sql);
		return quertList;
	}

}
