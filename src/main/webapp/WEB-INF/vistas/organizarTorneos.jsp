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
<div class="contenedor-organizar">
<div class="titulo-org">
<h1 class="organizar">ORGANIZÁ TU TORNEO</h1>
</div>
<div class="formulario-torneo">
<form:form action="crear-torneo" method="POST" modelAttribute="torneo">
			    
			    <label class="or">Nombre del torneo</label>
			    <form:input path="nombre" type="text"/>
			    
			   <label class="or">Juego</label>
			   <form:input path="juego" type="text"/>
			      
			    <label class="or">Fecha</label>
			      <form:input path="fecha" type="date"/>
			      
			       <label class="or">Horario</label>
					  <form:input path="horario" type="time" />
					  
					   <label class="or">Provincia</label>
					  <form:input path="provincia" type="text" />
					  
					   <label class="or">Ciudad</label>
					  <form:input path="ciudad" type="text" />
					  
					   <label class="or">Direccion</label>
					  <form:input path="direccion" type="text" />
					  
					   <label class="or">Cupo</label>
					  <form:input path="cupo" type="number" />
					  
					   <label class="or">Precio</label>
					    <form:input path="precio" type="number"/>
					    
					<button class="boton-organizar" Type="Submit"/>CREAR TORNEO</button>
					
				</form:form>

</div>
</div>
</body>
</html>