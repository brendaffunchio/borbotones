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

<title>Contacto</title>
</head>
<body>

	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>


		<%@include file="nav.jsp"%>



	</header>

	<div class="contenedor-contacto">


		<p class="datos2">
			<span>NUESTROS DATOS</span>
		</p>
		</br>
		<div class="contacto">
			<p>
				<span>Teléfono:</span> 011-35762940
			</p>
			<br> </br>
			<p>
				<span>Correo electrónico:</span>
				inmobiliaria_gamehouse@borbotones.com
			</p>
			<br> </br>
			<p>
				<span>Dirección: </span>Avenida Cordoba 4266, Palermo
			</p>
			<br> </br>
			<article class="mapa">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3284.306427296497!2d-58.429012785052535!3d-34.59641216461801!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95bcca7a3bd0286f%3A0x438096b2b1844d54!2sAv.%20C%C3%B3rdoba%204266%2C%20C1414%20BAN%2C%20Buenos%20Aires!5e0!3m2!1ses!2sar!4v1602722204316!5m2!1ses!2sar"
					allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
			</article>

		</div>
	</div>
	
	
</body>
</html>

