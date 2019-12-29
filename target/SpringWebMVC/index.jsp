<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.model.beans.testModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<thead>
		<tr>
			<th>U_ID</th>
			<th>NAME</th>
			<th>SURNAME</th>
		</tr>
	</thead>
	<tbody>
		<%
			ArrayList<testModel> testList = (ArrayList<testModel>) request.getAttribute("testList");
			for (Iterator<testModel> iterator = testList.iterator(); iterator.hasNext();) {
				testModel next = iterator.next();
				int id = next.getId();
				String name = next.getName();
				String surname = next.getSurname();
		%>
		<tr>
			<td><%=id%></td>
			<td><%=name%></td>
			<td><%=surname%></td>

		</tr>
		<%
			}
		%>
	</tbody>
</body>
</html>