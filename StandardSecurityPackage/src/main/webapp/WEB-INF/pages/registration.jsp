<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
	<title>Rekisteröityminen</title>
</head>
<body>
	<h3>Rekisteröidy antamalla sähköpostiosoite ja salasana.</h3>
	<form:form modelAttribute="user" action="registration" method="POST" enctype="utf8">
        <br>
        <table>
        <tr>
        <td><label>Email:</label>
        </td>
        <td><form:input path="username" value="" /></td>
        <form:errors path="username"/>
    </tr>
    <tr>
        <td><label>Salasana:</label>
        </td>
        <td><form:input path="password" value=""  type="password"/></td>
        <form:errors path="password"/>
    </tr>
    </table>
        <button type="submit">Rekisteröidy</button>
    </form:form>
    
</body>
</html>