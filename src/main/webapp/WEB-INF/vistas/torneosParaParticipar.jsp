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

<%@include file="nav.jsp"%>


	</header>

	<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

	<div class="row">
	
		<div class="col 6">
		
			<form:form action="buscar-torneo" method="GET">


	<div class="form-group col-sm-6">
	
	
	
				<label class="ors">Buscar torneo</label>
				</br>
			</div>

			<div class="form-group col-sm-5">
				<label > Elegir por categor�a </label>
				<select class="form-control" name="categoria" multiple>
					<option selected value="">Categor�a</option>
					<option value="Deporte">Deporte</option>
					<option value="Aventura">Aventura</option>
					<option value="Terror">Terror</option>
					<option value="Accion">Accion</option>
					<option value="Fantasia">Fantasia</option>
					<option value="Otra">Otra</option>
				  </select>

			 
				  <label >Ingresar juego </label>
				<input class="busc" type="text" name="juego">

				</div>
				
			<div class="col 6">
			
				<button class="btn btn-outline-success" type="submit">Buscar</button>
				
				</div>

			</form:form>
			
		</div>
		
		<div class="col-2">
		
			<div class="organizar-tor col">
			
			</br> <a class="btn btn-outline-success" href="ver-formulario-torneo?usuarioId=1" role="button">
				ORGANIZ� TU TORNEO </a>


		</div>
		
		
		 </div>

		
	</div>
	
	<div class="table-responsive col-sm-12"> 
	

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
      <td><a class="btn btn-outline-success btn-sm"  href="/proyecto-practica/ver-torneo-detalles?torneoId=${T.id}&usuarioId=${sessionScope.id}" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  
  </c:forEach>
</table>

</div>



</body>
</html>