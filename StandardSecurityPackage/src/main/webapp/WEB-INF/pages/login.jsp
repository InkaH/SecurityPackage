<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
   <head>
      <title>Sisäänkirjautuminen</title>
   </head>
      <p>Anna käyttäjänimi ja salasana:</p>
      <p>Ilman tietokantaa: user1 12345 (käyttäjällä on ROLE_USER) TAI admin1 12345 (käyttäjällä on ROLE_USER ja ROLE_ADMIN)</p>
      <p>Tietokantayhteydellä: jukka 123457 (ROLE_USER) TAI inka 123456 (ROLE_USER ja ROLE_ADMIN) </p>
   <body onload='document.loginForm.username.focus();'>
      <div>
         <h3>Anna sähköpostiosoite ja salasana:</h3>
         <c:if test="${not empty logout}">
			<p style="color: green">${logout}</p>
		 </c:if>
		 <c:if test="${not empty error}">
			<p style="color: red">${error}</p>
		 </c:if>
		 <c:if test="${not empty success}">
			<p style="color: green">${success}</p>
		 </c:if>
         <form name='loginForm'
            action="login" method='POST'>
            <table>
               <tr>
                  <td>Email:</td>
                  <td><input type='text' name='username'></td>
               </tr>
               <tr>
                  <td>Salasana:</td>
                  <td><input type='password' name='password' /></td>
               </tr>
               <tr>
                  <td colspan='2'><input name="submit" type="submit"
                     value="Kirjaudu" /></td>
               </tr>
            </table>
            <!-- Kun ei käytetä Spring formia eli <form:form></form:form>, CSRF-suojaus on lisättävä manuaalisesti.
             Ao. asetus on lyhyt muoto tästä: <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
            <sec:csrfInput />
         </form>
      </div>
   </body>
</html>