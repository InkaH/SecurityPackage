<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
   <head>
      <title>ROLE_USER -sivu</title>
   </head>
   <body>
      <h2>ROLE_USER -sivu</h2>
      <h3>T‰lle sivulle p‰‰see vain ROLE_USER -roolilla.</h3>
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
   </body>
</html>