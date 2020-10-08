<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Organizar Torneo</title>
</head>
<body>



<form:form action="torneosOrganizados" method="POST" modelAttribute="torneo">
			    
			    <form:input path="nombre" type="text" placeholder="Nombre"/>
			    
			      <form:input path="fecha" type="date" placeholder="Fecha"/>
			      
					  <form:input path="horario" type="time" placeholder="Horario"/>
					  
					    <form:input path="precio" type="number" placeholder="Precio"/>
					    
					<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>ORGANIZAR TORNEO</button>
					
				</form:form>



</body>
</html>