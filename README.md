# SpringWebMVC
JDBC mariadb 2.5.2
Database test 
# jasper-report_zyanwoadev

แปลงวันที่  
```java

"วันที่ "+ ($F{cow_birth}.substring(8,10)) +
(
(($F{cow_birth}.substring(5,7).equals("01")?" มกราคม ":""))
+(($F{cow_birth}.substring(5,7).equals("02")?" กุมภาพันธุ์ ":""))
+(($F{cow_birth}.substring(5,7).equals("03")?" มีนาคม  ":""))
+(($F{cow_birth}.substring(5,7).equals("04")?" เมษายน   ":""))
+(($F{cow_birth}.substring(5,7).equals("05")?" พฤษภาคม   ":""))
+(($F{cow_birth}.substring(5,7).equals("06")?" มิถุนายน   ":""))
+(($F{cow_birth}.substring(5,7).equals("07")?" กรกฎาคม   ":""))
+(($F{cow_birth}.substring(5,7).equals("08")?" สิงหาคม   ":""))
+(($F{cow_birth}.substring(5,7).equals("09")?" กันยายน   ":""))
+(($F{cow_birth}.substring(5,7).equals("10")?" ตุลาคม   ":""))
+(($F{cow_birth}.substring(5,7).equals("11")?" พฤศจิกายน   ":""))
+(($F{cow_birth}.substring(5,7).equals("12")?" ธันวาคม   ":""))
)+ "พ.ศ. " +
(Integer.parseInt(($F{cow_birth}.substring(0,4)))+543
	
)

```
