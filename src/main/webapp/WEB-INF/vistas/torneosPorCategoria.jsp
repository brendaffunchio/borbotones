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
			<form:form action="buscar-torneo-por-juego" method="GET">

				<label class="ors">Buscar torneo por juego</label>
				</br>
				<input name="juego" class="busc" type="search">
				<button class="btn-org" type="submit">Buscar</button>

			</form:form>
			<form:form action="buscar-torneo-por-categoria" method="GET">

				<label class="ors">Buscar torneo por categoria</label>
				</br>
				<input name="categoria" class="busc" type="search">
				<button class="btn-org" type="submit">Buscar</button>

			</form:form>
			
          </br> <a class="boton-part" href="ver-torneos" role="button">
			TODOS LOS TORNEOS </a>
		</div>

		<div class="organizar-tor">
			<p class="pp">Para organizar tu propio torneo, hac� clic en el
				bot�n:</p>
			</br> <a class="boton-part" href="ver-formulario-torneo" role="button">
				ORGANIZAR </a>


		</div>
	</div>

	<div class="torneos">
		<c:forEach items="${torneosCategoria}" var="TC">


			<div class="tor">
				<h2 class="nombre-tor">${TC.nombre}</h2>
				</br>
				<p>
					<span>Juego:</span> ${TC.juego}
				</p>
				</br>
				<p>
					<span>Fecha:</span> ${TC.fecha}
				</p>
				</br>
				<p>
					<span>Horario:</span> ${TC.horario}
				</p>
				</br>
				<p>
					<span>Provincia:</span> ${TC.provincia}
				</p>
				</br>
				<p>
					<span>Ciudad:</span> ${TC.ciudad}
				</p>
				</br>
				<p>
					<span>Direccion:</span> ${TC.direccion}
				</p>
				</br>
				<p>
					<span>Precio:</span> ${TC.precio}
				</p>
				</br> <a class="boton-part" href="ver-formulario-participar"
					role="button"> PARTICIPAR </a>
			</div>

		</c:forEach>


	</div>



</body>
</html>