
		<%@include file="header.jsp"%>


	<h2 class="titulo-inmuebles">DETALLES DEL INMUEBLE</h2>
	
<main class="container">
	
	<div class="row mt-5">

<div class="col-4  ml-4">

<img src="<c:url value="/inmuebles/${detalleInmueble.foto}"/>" width="380px" height="265px"/>

</div>

<div class="col-4  ml-3">
<form:form action="alquilar" method="POST">
		<h5>${detalleInmueble.nombre}</h5>
		<br>

		<p>$ ${detalleInmueble.precio}</p>
		<br>		

		<p>${detalleInmueble.direccion.calle} ${detalleInmueble.direccion.numero}</p>
		<br>
		<p>${detalleInmueble.direccion.ciudad.nombre}</p> 
		<br>
		<p>${detalleInmueble.direccion.ciudad.provincia.nombre}</p>
			
		<br>
		<input type="hidden" name ="usuarioId" value="${sessionScope.id}">
		
		<input type="hidden" name ="inmuebleId" value="${detalleInmueble.id}">
		<c:if test="${sessionScope.id != null}">
		<button class="boton-alqui" type="submit">ALQUILAR</button>
		</c:if>
		</form:form>
		
		
</div>
	</div>

</main>

</body>
</html>