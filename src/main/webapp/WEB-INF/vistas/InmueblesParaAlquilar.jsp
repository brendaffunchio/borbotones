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


	<div class="cont-acciones">
		<div class="buscador">

			<form:form action="buscar-inmueble" method="GET">

				<label class="ors">Buscar inmueble por Provincia</label>
				</br>
				<input name="busqueda" class="busc" type="search">
				<button class="btn-org" type="submit" id="buscarInmueble">Buscar</button>


			</form:form>



		</div>

		<div class="organizar-tor">
			<a class="boton-pub" href="formulario-inmueble" role="button">
				PUBLICAR </a>

		</div>

	</div>
	<div class="inmuebles">
		<c:forEach items="${inmuebles}" var="I">

			<div class="inm">
			
				<p>
			
			<img src="<c:url value="/inmuebles/${I.foto}"/>" width="170px" height="150px"/>
				
				</p>
								
				<br>
			
				<h2 class="nombre-inmueble">${I.nombre}</h2>
				<br>

				<p>
					<span>Precio:</span>$ ${I.precio}

				</p>
				
				<br>

				</p>

				<br>

				<a class="boton-alqui"
					href="/proyecto-practica/ver-inmueble-detalle?id=${I.id}" role="button">ver detalles</a> <br>

			</div>
		</c:forEach>
	</div>

</body>
</html>