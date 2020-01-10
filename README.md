# SpringWebMVC
JDBC mariadb 2.5.2
Database test 
# jasper-report_zyanwoadev

## pom.xml  (ติดตั้ง jasperreports)
```
<!-- https://mvnrepository.com/artifact/net.sf.jasperreports/jasperreports 
<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>6.10.0</version>
</dependency>-->
```
## [font thai](https://github.com/topkoka/jasper-report_zyanwoadev/tree/master/src)

   - ติดตั้ง [font](https://github.com/topkoka/jasper-report_zyanwoadev/tree/master/src/fonts) ภาษาไทย
   - ทำให้ project รู้จัก font  ->> [jasperreports_extension.properties](https://github.com/topkoka/jasper-report_zyanwoadev/blob/master/src/jasperreports_extension.properties)

## แปลงวันที่  
```java 
new java.text.SimpleDateFormat("d MMM yy", new Locale("TH","th")).format(new Date())
```
```java

"วันที่ "+ ($F{cow_birth}.substring(8,10)) +
(
(($F{cow_birth}.substring(5,7).equals("01")?" มกราคม ":""))
+(($F{cow_birth}.substring(5,7).equals("02")?" กุมภาพันธุ์ ":""))
+(($F{cow_birth}.substring(5,7).equals("03")?" มีนาคม ":""))
+(($F{cow_birth}.substring(5,7).equals("04")?" เมษายน ":""))
+(($F{cow_birth}.substring(5,7).equals("05")?" พฤษภาคม ":""))
+(($F{cow_birth}.substring(5,7).equals("06")?" มิถุนายน ":""))
+(($F{cow_birth}.substring(5,7).equals("07")?" กรกฎาคม ":""))
+(($F{cow_birth}.substring(5,7).equals("08")?" สิงหาคม ":""))
+(($F{cow_birth}.substring(5,7).equals("09")?" กันยายน ":""))
+(($F{cow_birth}.substring(5,7).equals("10")?" ตุลาคม ":""))
+(($F{cow_birth}.substring(5,7).equals("11")?" พฤศจิกายน ":""))
+(($F{cow_birth}.substring(5,7).equals("12")?" ธันวาคม ":""))
)+ "พ.ศ. " +
(Integer.parseInt(($F{cow_birth}.substring(0,4)))+543
	
)

```
``` java
($F{cow_breeding_log_date}.substring(8,10)) +
(
(($F{cow_breeding_log_date}.substring(5,7).equals("01")?" ม.ค. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("02")?" ก.พ. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("03")?" มี.ค. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("04")?" เม.ย. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("05")?" พ.ค. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("06")?" มิ.ย. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("07")?" ก.ค. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("08")?" ส.ค. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("09")?" ก.ย. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("10")?" ต.ค. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("11")?" พ.ย. ":""))
+(($F{cow_breeding_log_date}.substring(5,7).equals("12")?" ธ.ค. ":""))
) +
(Integer.parseInt(($F{cow_breeding_log_date}.substring(0,4)))+543
	
)
```
## Subreport parameter
``` xml
	<parameter name="Subreport_cowbirth_Parameter" class="net.sf.jasperreports.engine.JasperReport"/>
	<parameter name="SUBREPORT_cowbirth_DATA_SOURCE" class="net.sf.jasperreports.engine.JRDataSource"/>
	
	<------------------------------------------------------------------------------------------------->
	<subreport>
	<reportElement x="0" y="2" width="802" height="11" uuid="666c9f60-835a-4742-ba9b-db26fc42d422">
		<property name="com.jaspersoft.studio.unit.width" value="px"/>
	</reportElement>
	<dataSourceExpression><![CDATA[$P{SUBREPORT_cowbreeding_DATA_SOURCE}]]></dataSourceExpression>
	<subreportExpression><![CDATA[$P{Subreport_cowbreeding_Parameter}]]></subreportExpression>
	</subreport>

```
```java
	try {
	
	JasperPrint jasperPrint = null;
	cowreportDAO cowrDAO = new cowreportDAO(db);
	ArrayList<HashMap<String, Object>> cowrList = cowrDAO.FindAllbyID(cow_id);
	
	
	// path
	String Pathfile = "C:\\Users\\aditep\\git\\zcoop\\src\\JasperReport\\Cowreport\\";
	String reportFileName = "cowreport_main.jrxml"; // test reportFileName
	String reportPath = Pathfile + reportFileName;
	String targetFileName = reportFileName.replace(".jrxml", ".pdf");
	
	// add path
	JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
	
	// main report
	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cowrList);
	
	// subreport
	supcowreport_breedingtFileName = "supcowreport_breeding.jrxml";
	supcowreport_breedingPath = Pathfile + supcowreport_breedingtFileName;
	supcowreport_breedingParameter = JasperCompileManager.compileReport(supcowreport_breedingPath);
	supcowreport_breedingDataSource = new JRBeanCollectionDataSource(cowrList);
	
	
	Map<String, Object> parameters = new HashMap<>();
				parameters.put("ID", cow_id);
				parameters.put("Subreport_cowbreeding_Parameter", supcowreport_breedingParameter);
				parameters.put("SUBREPORT_cowbreeding_DATA_SOURCE", supcowreport_breedingDataSource);
	
	
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
	JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
	
	
	outputstream.flush();
	utputstream.close()
	} catch (JRException e) {
			e.printStackTrace();
		}
```


