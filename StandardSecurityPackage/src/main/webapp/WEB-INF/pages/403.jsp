<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
   <head>
      <title>${title}</title>
   </head>
   <body>
      <h2>${message}</h2>
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