<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
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
         <c:if test="${not empty error}">
            <p style="color:red">${error}</p>
         </c:if>
         <c:if test="${not empty msg}">
            <p style="color:red">${msg}</p>
         </c:if>
         <form name='loginForm'
            action="<c:url value='/login' />" method='POST'>
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
            <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
         </form>
      </div>
   </body>
</html>