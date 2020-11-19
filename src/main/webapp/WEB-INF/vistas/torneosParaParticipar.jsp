<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="css/estilo-inicio.css" />
<link rel="stylesheet" type="text/css" href="css/estilos.css">
<title>Torneos</title>
</head>
<body class="">

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
				<button class="btn btn-outline-success" type="submit">Buscar</button>

			</form:form>
			
		</div>

		<div class="organizar-tor">
			
			</br> <a class="btn btn-outline-success" href="ver-formulario-torneo?usuarioId=1" role="button">
				ORGANIZÁ TU TORNEO </a>


		</div>
	</div>

	<table class="table table-hover table-dark">
  <thead>
    <tr>
      <th scope="col">Imagen</th>
      <th scope="col">Torneo</th>
      <th scope="col">Juego</th>
      <th scope="col">Precio</th>
       <th scope="col">Acciones</th>
    </tr>
  </thead>
  <c:forEach items="${torneos}" var="T">
  <tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/torneos/${T.foto}"/>" width="150px" height="120px"/></th>
      <td>${T.nombre}</td>
      <td>${T.juego}</td>
      <td>$ ${T.precio}</td>
      <td><a class="btn btn-outline-success btn-sm"  href="/proyecto-practica/ver-torneo-detalles?id=${T.id}" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>



</body>
</html>