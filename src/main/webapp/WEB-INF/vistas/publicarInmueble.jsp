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
<title>Publicar Inmueble</title>
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
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">PUBLICÁ TU INMUEBLE</h2>
		</div>
		<div class="formulario-publicar">
			<form:form action="crear-inmueble" method="POST"
				modelAttribute="inmueble">
				<p class="datos">DATOS DEL INMUEBLE</p>
				</br>
				<label class="or">Nombre del inmueble</label>
				</br>
				<form:input class="f" path="nombre" type="text" />
				</br>
				<label class="or">Provincia</label>
				</br>
				<form:input class="f" path="provincia" type="text" />
				</br>
				<label class="or">Localidad</label>
				</br>
				<form:input class="f" path="localidad" type="text" />
				</br>
				<label class="or">Precio</label>
				</br>
				<form:input class="f" path="precio" type="number" />
				</br>
				<button class="btn-pub" Type="Submit" />PUBLICAR</button>

			</form:form>

		</div>
	</div>

</body>
</html>