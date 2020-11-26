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
		
		<c:forEach items="${misTorneosParticipo}" var="TP">

			<tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/torneos/${TP.foto}"/>" width="150px" height="120px"/></th>
      <td>${TP.nombre}</td>
      <td>${TP.juego}</td>
      <td>$ ${TP.precio}</td>
      <td><a class="btn btn-outline-success btn-sm"  href="/proyecto-practica/ver-torneo-detalles?torneoId=${TP.id}&usuarioId=3" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>


</body>
</html>