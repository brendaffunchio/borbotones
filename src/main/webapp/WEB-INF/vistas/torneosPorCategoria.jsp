<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/estilo-inicio.css" />
<link rel="stylesheet" type="text/css" href="css/estilos.css">

<title>Torneos</title>
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


	<div class="opcionesUsuario">

<div>
           <a class="boton-part" href="ver-torneos" role="button">
			TODOS LOS TORNEOS </a>
 </div>

			<div> <p class="pp">Organizá tu 
			<a class="organizaPropioTorneo" href="ver-formulario-torneo" role="button">
				propio torneo </a></p></div>
		</div>
	
	<div class="torneos">
		<c:forEach items="${torneosCategoria}" var="TC">


			<div class="tor">
				<h2 class="nombre-tor">${TC.nombre}</h2>
				</br>
				<p>
					<span>Juego:</span> ${TC.juego}
				</p>
				</br>
				<p>
					<span>Fecha:</span> ${TC.fecha}
				</p>
				</br>
				<p>
					<span>Horario:</span> ${TC.horario}
				</p>
				</br>
				<p>
					<span>Provincia:</span> ${TC.provincia}
				</p>
				</br>
				<p>
					<span>Ciudad:</span> ${TC.ciudad}
				</p>
				</br>
				<p>
					<span>Direccion:</span> ${TC.direccion}
				</p>
				</br>
				<p>
					<span>Precio:</span> ${TC.precio}
				</p>
				</br> <a class="boton-part" href="ver-formulario-participar"
					role="button"> PARTICIPAR </a>
			</div>

		</c:forEach>


	</div>



</body>
</html>