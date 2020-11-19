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
<title>Inmueble Detalle</title>
</head>
<body>
	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>


		<nav class="contenedorNav">

			<a href="inicio" id="btnHome"> HOME </a> <a href="ver-inmuebles"
				id="btnInmueble"> INMUEBLES </a> <a href="ver-torneos"
				id="btnTorneo"> TORNEOS </a> <a href="contacto" id="btnContacto">
				CONTACTO </a>

		</nav>


	</header>
<div class="col-md-12 text-center">
<h1>ELEGÍR GANADOR DEL TORNEO</h1>
<form:form action="elegirGanador" method="get">

	<div class="form-group col-md-12 text-center">
	 
	<input type="hidden" name="torneoGanadoId" value="${torneoId}">
	
	<select name="ganadorId">

	<c:forEach items="${participantes}" var="P">

	<option value="${P.id}">${P.nombre}</option>

	</c:forEach>

	</select>
	
	<button class="btn btn-primary" type="submit">ELEGIR GANADOR</button>
	
	</div>
	
	</div>
</form:form>



</body>
</html>