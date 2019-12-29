<%@page import="org.model.beans.TestModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<%
@SuppressWarnings("unchecked")
ArrayList<TestModel> testList = (ArrayList<TestModel>)request.getAttribute("testList");
%>
<body>
<%
for(int i = 0; i < testList.size(); i++) {
	TestModel testModel = testList.get(i);
	out.print(testModel.getId() + ", " + testModel.getName() + " " + testModel.getSurname() + "<br />");
}
%>
</body>
</html>