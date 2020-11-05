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

				<label class="ors">Buscar inmueble</label>
				</br>
				<input name="busqueda" class="busc" type="search">
				<button class="btn-org" type="submit" id="buscarInmueble">Buscar</button>


			</form:form>



		</div>
		
	

		<div class="organizar-tor">
		<div>
			<a class="boton-pub" href="ver-formulario-inmueble" role="button">
				PUBLICAR </a>
				
				</div>
				
				<br> <br>
				<div>
				
				<a class="boton-pub" href="ver-inmuebles" role="button">
				TODOS LOS INMUEBLES </a></div>

		</div>
			</div>


		<div class="inmuebles">
			<c:forEach items="${inmueblesBusqueda}" var="IB">
			
				<div class="inm">
				
				<p> 
				
					<img src="img/${IB.foto}" alt="imgText" width="180px" height="180px">
					
					</p> 
					
					</br>
				
				
					<h2 class="nombre-inmueble">${IB.nombre}</h2>
					</br>

					<p>
						<span>Precio:</span> ${IB.precio}
					</p>
					</br>


					<p>
						<span>Provincia:</span> ${IB.provincia}

					</p>
					</br>
					<p>
						<span>Localidad:</span> ${IB.localidad}

					</p>
					</br> 
					
					
					    <a class="detalles"
						href="/proyecto-practica/ver-inmueble-detalles/${IB.id}" role="button">ver
						detalles</a> <br>
						
						 <a class="boton-alqui"
						href="/proyecto-practica/ver-inmueble-detalles/${IB.id}" role="button">ALQUILAR</a>
					<br>

				</div>
			</c:forEach>
		</div>
</body>
</html>