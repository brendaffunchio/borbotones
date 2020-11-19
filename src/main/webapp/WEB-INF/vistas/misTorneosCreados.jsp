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

	<div class="cont-acciones">
	
		<div class="buscador">
			<form:form action="buscar-torneo" method="GET">

				<label class="ors">Buscar torneo deseado</label>
				</br>
				<input name="busqueda" class="busc" type="search">
				<button class="btn-org" type="submit">Buscar</button>

			</form:form>
			
		</div>

	</div>

	<div class="torneos">
		<c:forEach items="${misTorneosCreados}" var="TC">

			<div class="tor">
			
			<p>
			
				<img src="<c:url value="/torneos/${TC.foto}"/>" width="170px" height="150px"/>
				

				</p>
						
				<h2 class="nombre-tor">${TC.nombre}</h2>
				</br>
				<p>
					<span>Juego:</span> ${TC.juego}
				</p>
				
				</br>
				<p>
					<span>Precio:</span>$ ${TC.precio}
				</p>
				</br> 
				<a class="boton-part" href="/proyecto-practica/ver-mis-torneos-detalles?id=${TC.id}" role="button">ver
					detalles</a> 
					
				
			</div>

		</c:forEach>


	</div>



</body>
</html>