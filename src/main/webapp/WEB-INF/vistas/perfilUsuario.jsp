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

<%@include file="nav.jsp"%>

	</header>

	<div class="container">
	
	<div class="row">
	
	<div class="col-12 text-center">
	
	
	<a class="boton-part" href="/proyecto-practica/ver-inmuebles-alquilados?usuarioId=${sessionScope.id}" role="button">MIS INMUEBLES</a>
					<br>
					<br>
					<a class="boton-part" href="/proyecto-practica/ver-torneos-que-participo?usuarioId=${sessionScope.id}"
					role="button">TORNEOS QUE PARTICIPO </a>
					<br>
					<br>
					<a class="boton-part" href="/proyecto-practica/ver-torneos-que-cree?usuarioId=${sessionScope.id}"
					role="button">MIS TORNEOS</a>
					
					
	
	
	</div>
	
	
	
	</div>
	
	

	</div>



</body>
</html>>