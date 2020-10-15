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
<title>Organizar Torneo</title>
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
 
<div class="contenedor-organizar">
<div class="titulo-org">
<h2 class="titulo-organizar">ORGANIZÁ TU TORNEO</h2>
</div>
<div class="formulario-torneo">
<form:form action="crear-torneo" method="POST" modelAttribute="torneo">
			    
			    <label class="or">Nombre del torneo</label>
			    </br>
			    <form:input class="f" path="nombre" type="text"/>
			    </br>
			   <label class="or">Juego</label>
			   </br>
			   <form:input class="f" path="juego" type="text" />
			      </br>
			    <label class="or">Fecha</label>
			    </br>
			      <form:input class="f" path="fecha" type="date" />
			      </br>
			      
			       <label class="or">Horario</label>
			       </br>
					  <form:input class="f" path="horario" type="time"  />
					  </br>
					   <label class="or">Provincia</label>
					   </br>
					  <form:input class="f" path="provincia" type="text" />
					  </br>
					   <label class="or">Ciudad</label>
					   </br>
					  <form:input class="f" path="ciudad" type="text" />
					  </br>
					   <label class="or">Direccion</label>
					   </br>
					  <form:input class="f" path="direccion" type="text" />
					  </br>
					   <label class="or">Cupo</label>
					   </br>
					  <form:input class="f" path="cupo" type="number"/>
					  </br>
					   <label class="or">Precio</label>
					   </br>
					    <form:input class="f" path="precio" type="number" />
					    </br>
					    <div class="boton-organizar" >
					<button class="btn-org" Type="Submit"/>CREAR TORNEO</button>
					</div>
				</form:form>

</div>
</div>
</body>
</html>