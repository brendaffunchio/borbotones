<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/sheetslider.min.css" />
<link rel="stylesheet" href="css/estilo-inicio.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/233d4e0a24.js"
	crossorigin="anonymous"></script>

<title>INMOBILIARIA GAMING HOUSE</title>
</head>
<body>

	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>


		<nav class="contenedorNav">

			<a href="inicioInvitado" id="btnHome"> HOME </a> <a href="ver-inmuebles"
				id="btnInmueble"> INMUEBLES </a> <a href="ver-torneos"
				id="btnTorneo"> TORNEOS </a> 
				
				<a href="contacto" id="btnContacto"> CONTACTO </a>
				
				<a href="ver-perfil-del-usuario?usuarioId=1" id="btnPerfil"> PERFIL </a>
				

		</nav>

	</header>


	<div class="contenedorDescripcion">

	ranking de ganadores: <div> 

	<c:forEach items="${usuarios}" var="U">

	<h3> ${U.nombre} Torneos ganados ${U.torGanados} </h3> 

	</c:forEach>

	</div>
		
	</div>



	<!--Sheet Slider-->
	<div class="sheetSlider sh--default">
		<input id="s1" type="radio" name="slide1" checked /> <input id="s2"
			type="radio" name="slide1" /> <input id="s3" type="radio"
			name="slide1" />
		<div class="sh__content">

			<!-- Slider Item -->
			<div class="sh__item">
				<img src="img/imagen1.jpg" alt="imgText" width="100%" height="100%">
				<!-- Item Info -->
				<div class="sh__meta">
					<h4>Prepará a tu equipo</h4>
					<span>Buscá tu lugar para entrenar <a href="ver-inmuebles"
						id="verInmuebles"> acá </a></span>
				</div>
			</div>

			<!-- Slider Item -->
			<div class="sh__item">
				<img src="img/imagen2.jpg" alt="imgText" width="100%" height="100%">
				<!-- Item Info -->
				<div class="sh__meta">
					<h4>Organizá torneos</h4>
					<span>Organizalo de la manera que quieras <a
						href="ver-formulario-torneo" id="organizarTorneos"> acá </a></span>
				</div>
			</div>

			<!-- Slider Item -->
			<div class="sh__item">
				<img src="img/imagen3.jpg" alt="imgText" width="100%" height="100%">
				<!-- Item Info -->
				<div class="sh__meta">
					<h4>Participá en un torneo profesional</h4>
					<span>Elegí el torneo del juego que más te guste <a
						href="ver-torneos" id="verTorneos"> acá </a></span>
				</div>
			</div>

		</div>
		<!-- .sh__content -->

		<!--botones-->
		<div class="sh__btns">
			<label for="s1"></label> <label for="s2"></label> <label for="s3"></label>
		</div>
		<!-- .sh__btns -->

		<!--flechas-->
		<div class="sh__arrows">
			<label for="s1"></label> <label for="s2"></label> <label for="s3"></label>
		</div>
		<!-- .sh__arrows -->

	</div>
	<!-- .sheetSlider -->


	<footer>
		<div class="contenedorFooter">


			<i class="fab fa-youtube"></i> <i class="fab fa-instagram"> </i> <i
				class="fab fa-whatsapp"></i>
		</div>
	</footer>

</body>

</html>