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
		
		<div class="col-4 mr-5">
		<p>
			
			<img src="<c:url value="/torneos/${miTorneoDetalle.foto}"/>" width="415px" height="350px"/>
				
				</p>
		
		</div>
		<div class="col-2 ml-4">
		<h6>Torneo:</h6>
				<h6>${miTorneoDetalle.nombre}</h6>
				<br>
				<h6>Juego:</h6>
				<h6>${miTorneoDetalle.juego}</h6>
				<br>
				<h6>Precio:</h6>
				<h6>$ ${miTorneoDetalle.precio}</h6>
				<br>
				<h6>Fecha:</h6>
				<h6>${miTorneoDetalle.fecha}</h6>
				<br>
				<h6>Horario:</h6>
				<h6>${miTorneoDetalle.horario}</h6>
				<br>
				</div>
				
				<div class="col-2">
				<h6>Dirección:</h6>
				<h6>${miTorneoDetalle.inmuebleDelTorneo.direccion.calle} ${torneoDetalle.inmuebleDelTorneo.direccion.numero}</h6>
				<br>
				<h6>Ciudad:</h6>
				<h6>${miTorneoDetalle.inmuebleDelTorneo.direccion.ciudad.nombre}</h6>
				<br>
				<h6>Provincia:</h6>
				<h6>${miTorneoDetalle.inmuebleDelTorneo.direccion.ciudad.provincia.nombre}</h6>
				<br>
				<h6>Inscriptos:</h6>
				<h6>${miTorneoDetalle.inscriptos}</h6>
				
					<a class="boton-part" href="ver-lista-de-participantes"
					role="button"> ELEGIR GANADOR </a>
				
			</div>	
				
			</div>

</main>
</body>
</html>