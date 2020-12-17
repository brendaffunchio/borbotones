
		<%@include file="header.jsp"%>


	<h2 class="titulo-inmuebles">DETALLES DEL INMUEBLE</h2>
	
<div class="container">
	
	<div class="row mt-5">

<div class="col">

<img src="<c:url value="/inmuebles/${detalleInmueble.foto}"/>" width="380px" height="265px"/>

</div>

<div class="col">
<form:form action="alquilar" method="POST">

		<div class="form-group">
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
		</div>
		
		
		<div class="form-group col">
		<c:if test="${sessionScope.id != null}">
		<button class="btn btn-outline-warning" type="submit">ALQUILAR</button>
		
		</c:if>
		</div>
	
		</form:form>
		
			</div>
		
		
</div>
	</div>

</div>

</body>
</html>