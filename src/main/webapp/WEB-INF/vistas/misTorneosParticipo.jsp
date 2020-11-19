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

	<h2 class="titulo-torn">TORNEOS QUE PARTICIPO</h2>
     <c:if test="${not empty error}">
				<h2 class="text-center">
					${error}
				</h2>
				<br>
			</c:if>
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
		<c:forEach items="${misTorneosParticipo}" var="TP">

			<div class="tor">
			
			<p>
			
				<img src="<c:url value="/torneos/${TP.foto}"/>" width="170px" height="150px"/>
				

				</p>
						
				<h2 class="nombre-tor">${TP.nombre}</h2>
				</br>
				<p>
					<span>Juego:</span> ${TP.juego}
				</p>
				
				</br>
				<p>
					<span>Precio:</span>$ ${TP.precio}
				</p>
				</br> 
				<a class="boton-part" href="/proyecto-practica/ver-torneos-participo-detalles?id=${TP.id}" role="button">ver
					detalles</a> 
					
				
			</div>

		</c:forEach>


	</div>



</body>
</html>