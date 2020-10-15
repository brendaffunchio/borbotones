<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilos.css"/>
<link rel="stylesheet" href="css/estilo-inicio.css"/>
<meta charset="ISO-8859-1">
<title>Participar Torneo</title>
</head>
<body>

<h1 class="titulo-index"> INMOBILIARIA GAMING HOUSE  </h1>

<header>


<nav class="contenedorNav"> 

<a href="inicio" id="btnHome"> HOME </a> 

<a href="ver-inmuebles" id="btnInmueble"> INMUEBLES </a> 

<a href="ver-torneos" id="btnTorneo"> TORNEOS </a> 

<a href="contacto" id="btnContacto"> CONTACTO </a> 

 </nav>
 

 </header>
 
<div class="contenedor-participar">
<div class="titulo-part">
<h2 class="titulo-inscripcion">INSCRIPCIÓN AL TORNEO</h2>
</div>
<div class="formulario-participar">

<form:form action="" method="POST" modelAttribute="usuario" >
			    <p class="datos">DATOS DEL PARTICIPANTE</p>
			    </br>
			    <label class="or">Nombre</label>
			    </br>
			    <form:input class="f" path="nombre" type="text" />
			    </br>
			   <label class="or">Apellido</label>
			   </br>
			   <form:input class="f" path="apellido" type="text" />
			      </br>
			    <label class="or">Email</label>
			    </br>
			      <form:input class="f" path="email" type="email"/>
			      </br>
					    <div class="boton-participar" >
					<button class="btn-part" Type="Submit"/>INSCRIBIRSE</button>
					</div>
				</form:form>

</div>
</div>

</body>
</html>