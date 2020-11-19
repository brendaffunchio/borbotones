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

			<form:form action="crear-inmueble" method="POST" enctype="multipart/form-data"
				modelAttribute="inmueble">
				
				<p class="datos">DATOS DEL INMUEBLE</p>
				</br>
				<label class="or">Nombre del inmueble</label>
				</br>
				<form:input class="f" path="nombre" type="text" required="required"/>
				</br>
				

				<label class="or">Provincia</label>
				<br>
				<select>
				
				<c:forEach items="${provincias}" var="P">
				
               <option value="provincia">${P.nombre}</option>
              
              </c:forEach>
         	  </select>
         	  
				</br>
				
				<label for="nombreCiudad" class="or" for="ciudades">Ciudad</label>
				<br>
				<select>
				<c:forEach items="${ciudades}" var="C">

				<option name="nombreCiudad" value="ciudad"> ${C.nombre} </option>
				
				</c:forEach>
				</select>

				</br>
				<label class="or">Direccion</label>
				</br>
				<input name="calle" class="f"  type="text" placeholder="calle" required/>
				</br>
				<input name="numero" class="f" type="number" placeholder="numero" required/>
				<br>
				<label for="precio"class="or">Valor del inmueble</label>
				</br>
				<form:input name="precio" class="f" path="precio" type="number" required="required"/>
				</br>

				<label class="or">Imagen del inmueble</label>
				<input type="file" name="file"/> 
				
				<br>
				<button class="btn-pub" Type="Submit" />PUBLICAR</button>

			</form:form>
		
		</div>
	</div>

</body>
</html>