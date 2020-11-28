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
<title>Registrar Usuario</title>
</head>
<body>
	<h1 class="titulo-index">INMOBILIARIA GAMING HOUSE</h1>

	<header>

<%@include file="nav.jsp"%>

	</header>
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">REGISTRATE</h2>
		</div>
		<div class="formulario-publicar">
			<form:form action="crear-usuario" method="POST"
				modelAttribute="usuario">
				<p class="datos">DATOS DEL USUARIO</p>
				</br>
				<label class="or">Nombre</label>
				</br>
				<form:input class="f" path="nombre" type="text" required="required"/>
				</br>
				<label class="or">Apellido</label>
				</br>
				<form:input class="f" path="apellido" type="text" required="required"/>
				</br>
				
				<label class="or">Provincia</label>
				
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
				<label class="or">Email</label>
				</br>
				<form:input class="f" path="email" type="email" required="required" />
				</br>
				<label class="or">Password</label>
				</br>
				<form:input class="f" path="password" type="password" required="required"/>
				</br>
				
				<button class="btn-pub" Type="Submit" />REGISTRARSE</button>

			</form:form>

            <c:if test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
			</c:if>
			
		</div>
	</div>

</body>
</html>