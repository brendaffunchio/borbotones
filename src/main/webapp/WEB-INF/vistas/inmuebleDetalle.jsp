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


	<h2 class="titulo-inmuebles">DETALLES DEL INMUEBLE</h2>
	
<main class="container">
	
	<div class="row mt-5">

<div class="col-4  ml-4">

<img src="img/${detalleInmueble.foto}" alt="imgText" width="380px" height="265px">

</div>

<div class="col-4  ml-3">

		<p>${detalleInmueble.nombre}</p>
		<br>
		<p>$ ${detalleInmueble.precio}</p>
		<br>
		<button class="boton-alqui" Type="Submit">ALQUILAR</button>


</div>
	</div>

</main>

</body>
</html>