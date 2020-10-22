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

<title>Torneos Por Juego</title>
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
		<c:forEach items="${torneosJuegos}" var="TJ">


			<div class="tor">
				<h2 class="nombre-tor">${TJ.nombre}</h2>
				</br>
				<p>
					<span>Juego:</span> ${TJ.juego}
				</p>
				</br>
				<p>
					<span>Fecha:</span> ${TJ.fecha}
				</p>
				</br>
				<p>
					<span>Horario:</span> ${TJ.horario}
				</p>
				</br>
				<p>
					<span>Provincia:</span> ${TJ.provincia}
				</p>
				</br>
				<p>
					<span>Ciudad:</span> ${TJ.ciudad}
				</p>
				</br>
				<p>
					<span>Direccion:</span> ${TJ.direccion}
				</p>
				</br>
				<p>
					<span>Precio:</span> ${TJ.precio}
				</p>
				</br> <a class="boton-part" href="ver-formulario-participar"
					role="button"> PARTICIPAR </a>
			</div>

		</c:forEach>


	</div>



</body>
</html>