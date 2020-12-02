<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/233d4e0a24.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/sheetslider.min.css" />
<link rel="stylesheet" href="css/estilo-inicio.css" />
<link rel="stylesheet" href="css/estilos.css" />

<title>INMOBILIARIA GAMING HOUSE</title>
</head>
<body>

	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>

		<nav class="contenedorNav">

			<a class="botonNav" href="inicio" id="btnHome"> HOME </a> 
			<a class="botonNav" href="ver-inmuebles"	id="btnInmueble"> INMUEBLES </a>
			
				 <a class="botonNav" href="ver-torneos?usuarioId=${sessionScope.id}" id="btnTorneo"> TORNEOS </a>
				 
				
				<a class="botonNav" href="contacto" id="btnInmueble">CONTACTO </a>

			<c:if
				test="${(sessionScope.rol=='admin')or(sessionScope.rol=='invitado')}">
				<a class="botonNav" href="ver-perfil-del-usuario?usuarioId=${sessionScope.id}" id="btnTorneo">
					PERFIL </a>
				<a class="botonNav" href="logout" id="btnContacto"> CERRAR SESIÓN </a>
			</c:if>


		</nav>
		
		
	</header>
