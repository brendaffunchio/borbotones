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
<title>Participar</title>
</head>
<body>
	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>


		<%@include file="nav.jsp"%>

	</header>
	
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">	<c:if test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
				</c:if></h2> 
			<br>
			<h6> <a href="ver-formulario-inmueble"> Volvé </a> a publicar tú inmueble ¡Esta vez con foto!</h6>
			<img src="img/fileNotFound.png" width="300px" height="300px">
			
		</div>
	</div>

</body>
</html>