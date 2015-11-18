<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	
	<title>Rekisteröityminen</title>
</head>
<body>
	<h3>Rekisteröidy antamalla sähköpostiosoite ja salasana.</h3>
	
	<c:if test="${not empty error}">
            <p style="color:red">${error}</p>
    </c:if>
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
        <td><form:input path="password" id="password" value="" type="password"/><span id='pwMsg'></span></td>
        <form:errors path="password"/>
    </tr>
    <tr>
        <td><label>Toista salasana:</label>
        </td>
        <td><input id="confirm_password" value="" type="password"/><span id='cpwMsg'></span></td>
    </tr>
    
    </table>
        <button type="submit" id="submit" disabled>Rekisteröidy</button>
    </form:form>
    
    <script>
    
    $('#password').on('keyup', function() {
    	if ($(this).val().length < 3 || $(this).val().length > 60){
    		$('#pwMsg').html('Salasanan pituus 3-60 merkkiä').css('color', 'red');
    		document.getElementById('submit').disabled = true;
    	} else {
    		$('#pwMsg').html('Salasanan pituus 3-60 merkkiä').css('color', 'green');
    		document.getElementById('submit').disabled = false;
    	}
    });
    
	$('#confirm_password').on('keyup', function () {
    	if ($(this).val() == $('#password').val()) {
        	$('#cpwMsg').html('Salasanat täsmäävät!').css('color', 'green');
        	document.getElementById('submit').disabled = false;
    	} else {
    		$('#cpwMsg').html('Salasanat eivät täsmää!').css('color', 'red');
    		document.getElementById('submit').disabled = true;
    	}
	});    
	</script> 
</body>
</html>