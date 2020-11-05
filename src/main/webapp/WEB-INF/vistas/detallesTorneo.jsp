<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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

	

	<div class="torneos">
		
				${torneoDetalle.nombre}
				<br>
				${torneoDetalle.juego}
				<br>
				$ ${torneoDetalle.precio}
				<br>
				${torneoDetalle.fecha}
				<br>
				${torneoDetalle.horario}
				<br>
				${torneoDetalle.categoria}
				<br>
				
				
				<a class="boton-part" href="ver-formulario-participar"
					role="button"> PARTICIPAR </a>
			</div>

	

	</div>



</body>
</html>