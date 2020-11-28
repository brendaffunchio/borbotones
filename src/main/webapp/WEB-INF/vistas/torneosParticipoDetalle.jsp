<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="css/estilos.css" />
<link rel="stylesheet" href="css/estilo-inicio.css" />
<meta charset="ISO-8859-1">
<title>Torneos Detalles</title>
</head>
<body>
	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>

<%@include file="nav.jsp"%>

	</header>

	<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

	
<main class="container">
	<div class="row mt-5">
		
		<div class="col-4 mr-5">
		<p>
			
			<img src="<c:url value="/torneos/${torneoParticipoDetalle.foto}"/>" width="415px" height="350px"/>
				
				</p>
		
		</div>
		<div class="col-2 ml-4">
		<h6>Torneo:</h6>
				<h6>${torneoParticipoDetalle.nombre}</h6>
				<br>
				<h6>Juego:</h6>
				<h6>${torneoParticipoDetalle.juego}</h6>
				<br>
				<h6>Precio:</h6>
				<h6>$ ${torneoParticipoDetalle.precio}</h6>
				<br>
				<h6>Fecha:</h6>
				<h6>${torneoParticipoDetalle.fecha}</h6>
				<br>
				<h6>Horario:</h6>
				<h6>${torneoParticipoDetalle.horario}</h6>
				<br>
				</div>
				
				<div class="col-2">
				<h6>Dirección:</h6>
				<h6>${torneoParticipoDetalle.inmuebleDelTorneo.direccion.calle} ${torneoParticipoDetalle.inmuebleDelTorneo.direccion.numero}</h6>
				<br>
				<h6>Ciudad:</h6>
				<h6>${torneoParticipoDetalle.inmuebleDelTorneo.direccion.ciudad.nombre}</h6>
				<br>
				<h6>Provincia:</h6>
				<h6>${torneoParticipoDetalle.inmuebleDelTorneo.direccion.ciudad.provincia.nombre}</h6>
				
				<br>
			
			<form:form action="desubscribirse" method="POST">
			
			<input type="hidden" name="torneoId" value="${torneoParticipoDetalle.id}">
			<input type="hidden" name="usuarioId" value ="${sessionScope.id}">
			
			<button type="submit">DESUSCRIBIRSE</button>
			</form:form>
					
			</div>	
				
			</div>

</main>
</body>
</html>