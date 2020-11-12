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


		<nav class="contenedorNav">

			<a href="inicio" id="btnHome"> HOME </a> <a href="ver-inmuebles"
				id="btnInmueble"> INMUEBLES </a> <a href="ver-torneos"
				id="btnTorneo"> TORNEOS </a> <a href="contacto" id="btnContacto">
				CONTACTO </a>

		</nav>


	</header>

	<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

	
<main class="container">
	<div class="row mt-5">
		
		<div class="col-4 ml-4">
		<p>
			
			<img src="<c:url value="/torneos/${torneoDetalle.foto}"/>" width="380px" height="265px"/>
				
				</p>
		
		</div>
		<div class="col-4 ml-3">
				<p>${torneoDetalle.nombre}</p>
				<br>
				<p>${torneoDetalle.juego}</p>
				<br>
				<p>$ ${torneoDetalle.precio}</p>
				<br>
				<p>${torneoDetalle.fecha}</p>
				<br>
				<p>${torneoDetalle.horario}</p>
				<br>
				<p>${torneoDetalle.categoria}</p>
				<br>
				
				<a class="boton-part" href="agregar-participante?torneoId=${torneoDetalle.id}&usuarioId=1"
					role="button"> PARTICIPAR </a>
					<a class="boton-part" href="eliminar-participante?torneoId=${torneoDetalle.id}&usuarioId=1"
					role="button"> DESUSCRIBIRSE </a>
				
			</div>	
				
			</div>

</main>
</body>
</html>