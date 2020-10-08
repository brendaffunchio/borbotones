<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Publicar inmueble</title>
</head>
<body>

<form:form action="inmueblesPublicados" method="POST" modelAttribute="inmueble">
			    
			    <form:input path="nombre" type="text" placeholder="Nombre"/>
			    
			    <form:input path="precio" type="number" placeholder="Precio"/>
					    
				<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>PUBLICAR INMUEBLE</button>
					
				</form:form>
</body>
</html>