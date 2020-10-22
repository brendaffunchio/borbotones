<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/estilo-inicio.css" />
<link rel="stylesheet" type="text/css" href="css/estilos.css">

<title>Inmuebles</title>
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


	<h2 class="titulo-inmuebles">INMUEBLES DISPONIBLES</h2>


	<div class="inmuebles">
		<c:forEach items="${inmueblesLocalidad}" var="IL">

			<div class="inm">
				<h2 class="nombre-inmueble">${IL.nombre}</h2>
				</br>
				
				<p>
					<span>Precio:</span> ${IL.precio}
				</p>
				</br>
				
				
					<p>
					<span>Provincia:</span> ${IL.provincia}
					
				</p>
	</br>				
					<p>
					<span>Localidad:</span> ${IL.localidad}
					
				</p>
				</br>


				<button class="boton-alqui" Type="Submit" />
				ALQUILAR
				</button>

			</div>
		</c:forEach>
	</div>

</body>
</html>