<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rekisteröinti onnistui</title>
</head>
<body>
 	${success}
 	<h3>
 	 <a href="<c:url value="/login"/>">Kirjaudu sisään</a>
 	</h3>
</body>
</html>