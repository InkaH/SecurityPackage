<%@taglib prefix="sec"
   uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
   <head>
      <title>Tervetuloa</title>
   </head>
   <body>
      <h2>Spring Security - autentikointi- ja autorisointidemo</h2>
      
      <!-- N‰kym‰ anonyymille k‰ytt‰j‰lle -->
      <sec:authorize access="isAnonymous()">
         <p>T‰m‰ on n‰kym‰ anonyymille k‰ytt‰j‰lle.</p>
         <h3>
            <a href="<c:url value="/login"/>">Kirjaudu sis‰‰n</a>
         </h3>
         <h3>
            <a href="<c:url value="/registration"/>">Rekisterˆidy</a>
         </h3>
      </sec:authorize>
      
      <!-- N‰kym‰ sis‰‰nkirjautuneelle k‰ytt‰j‰lle, jolla on ROLE_USER-->
      <sec:authorize access="hasRole('ROLE_USER')">
         <p>T‰m‰ on n‰kym‰ sis‰‰nkirjautuneelle k‰ytt‰j‰lle, jolla on ROLE_USER.</p>
         <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h3>
               Tervetuloa, ${pageContext.request.userPrincipal.name} 
            </h3>
         </c:if>
         <c:url var="logoutUrl" value="/logout"/>
         <form action="${logoutUrl}"
            method="post">
            <input type="submit"
               value="Kirjaudu ulos" />
            <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
         </form>
      </sec:authorize>
            
      <h3>
         <a href="<c:url value="/user"/>">Kokeile ROLE_USER -rajattua sivua</a>
      </h3>
      <p>-> Kun anonyymi k‰ytt‰j‰ yritt‰‰ p‰‰st‰ user-sivulle, h‰net ohjataan login-sivulle.</p>
      
      <h3>
         <a href="<c:url value="/admin"/>">Kokeile ROLE_ADMIN -rajattua sivua</a>
      </h3>
      <p>-> Kun anonyymi k‰ytt‰j‰ yritt‰‰ p‰‰st‰ admin-sivulle, h‰net ohjataan login-sivulle.</p>
      <p>-> 403-sivu n‰ytet‰‰n, mik‰li kirjautuneella ei ole admin-oikeuksia.</p>
     
   </body>
</html>