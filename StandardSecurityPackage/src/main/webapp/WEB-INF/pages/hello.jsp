<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
   <head>
      <title>Tervetuloa</title>
   </head>
   <body>
      <h2>Spring Security - autentikointi- ja autorisointidemo</h2>
      
      <!-- Näkymä anonyymille käyttäjälle -->
      <sec:authorize access="isAnonymous()">
         <p>Tämä on näkymä anonyymille käyttäjälle.</p>
         <h3>
            <a href="login">Kirjaudu sisään</a>
         </h3>
         <h3>
            <a href="registration">Rekisteröidy</a>
         </h3>
      </sec:authorize>
      
      <!-- Näkymä sisäänkirjautuneelle käyttäjälle, jolla on ROLE_USER-->
      <sec:authorize access="hasRole('ROLE_USER')">
         <p>Tämä on näkymä sisäänkirjautuneelle käyttäjälle, jolla on ROLE_USER.</p>
         <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h3>
               Tervetuloa, ${pageContext.request.userPrincipal.name} 
            </h3>
         </c:if>
         <form action="logout" method="post">
         	<input type="submit" value="Kirjaudu ulos" />
         	<sec:csrfInput />
     	 </form>
      </sec:authorize>
            
      <h3>
         <a href="<c:url value="/user"/>">Kokeile ROLE_USER -rajattua sivua</a>
      </h3>
      <p>-> Kun anonyymi käyttäjä yrittää päästä user-sivulle, hänet ohjataan login-sivulle.</p>
      
      <h3>
         <a href="<c:url value="/admin"/>">Kokeile ROLE_ADMIN -rajattua sivua</a>
      </h3>
      <p>-> Kun anonyymi käyttäjä yrittää päästä admin-sivulle, hänet ohjataan login-sivulle.</p>
      <p>-> 403-sivu näytetään, mikäli kirjautuneella ei ole admin-oikeuksia.</p>
     
   </body>
</html>