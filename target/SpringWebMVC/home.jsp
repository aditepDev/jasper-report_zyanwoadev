
<%@page import="org.model.beans.TestModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
	<thead>
		<tr>
			<th>U_ID</th>
			<th>NAME</th>
			<th>SURNAME</th>>
		</tr>
	</thead>
	<tbody>
		<%
			ArrayList<TestModel> testList = (ArrayList<TestModel>) request.getAttribute("testList");
			for (Iterator<TestModel> iterator = testList.iterator(); iterator.hasNext();) {
				TestModel next = iterator.next();
				int id = next.getId();
				String name = next.getName();
				String surname = next.getSurname();
		%>
		<tr>
			<td><%=id%>></td>
			<td><%=name%>></td>
			<td><%=surname%>></td>

		</tr>
		<%
			}
		%>
	</tbody>
	</table>
</body>
</html>