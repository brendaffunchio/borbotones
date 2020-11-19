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
<title>Organizar Torneo</title>
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

	<div class="contenedor-organizar">
		<div class="titulo-org">
			<h2 class="titulo-organizar">ORGANIZÁ TU TORNEO</h2>
		</div>
		<div class="formulario-torneo">
			<form:form action="crear-torneo" method="POST"
				enctype="multipart/form-data" modelAttribute="torneo">

				<input type="hidden" value="1" name="creadorId">

				<label class="or">Nombre del torneo</label>
				<br>
				<form:input class="f" path="nombre" type="text" required="required" />
				<br>
				<label class="or">Juego</label>
				<br>
				<form:input class="f" path="juego" type="text" required="required" />
				<br>
				<label class="or">Categoria</label>
				<br>

				<form:select path="categoria">
					<form:option value="Deporte">Deporte</form:option>
					<form:option value="Aventura">Aventura</form:option>
					<form:option value="Terror">Terror</form:option>
					<form:option value="Accion">Accion</form:option>
					<form:option value="Fantasia">Fantasia</form:option>
					<form:option value="Otra">Otra</form:option>

				</form:select>

				<br>
				<label class="or">Fecha</label>
				<br>
				<form:input class="f" path="fecha" type="date" required="required" />
				<br>

				<label class="or">Horario</label>
				<br>
				<form:input class="f" path="horario" type="time" required="required" />

				</br>

				<label class="or" for="inmuebleId">Inmueble</label>
				<br>

				<select name="inmuebleId">

					<c:forEach items="${inmuebles}" var="I">

						<option value="${I.id}">${I.nombre}</option>

					</c:forEach>

				</select>
				<br>
				<label class="or">Cupo</label>
				<br>
				<form:input class="f" path="cupo" type="number" required="required" />
				<br>
				<label class="or">Precio</label>
				<br>
				<form:input class="f" path="precio" type="number"
					required="required" />

				</br>

				<label class="or">Imagen del inmueble</label>
				<input type="file" name="file">

				</br>

				<div class="boton-organizar">
					<button class="btn-org" Type="Submit">CREAR TORNEO</button>

					<br>

					<p class="avisoUsuario">
						Necesitas <a class="organizaPropioTorneo"> iniciar sesión </a>para
						crear un torneo.
					</p>

				</div>
			</form:form>

		</div>
	</div>
</body>
</html>