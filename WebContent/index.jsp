<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Add!</h1>
	<hr />
	<form action="<%=request.getContextPath()%>/cowreport" method="get">
		<table border="1">
			<thead>
				<tr>
					<th>cow_id</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="radio" name="cow" value="0">
						cow<br></td>
					<td><input type="radio" name="cow" value="1">
						cow thai<br></td>
					<td><input type="text" name="cow_id" value="" /></td>
					<td><input type="submit" value="ADD" /></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>