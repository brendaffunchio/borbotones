
<%@include file="header.jsp"%>

<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>


<main class="container">
	<div class="row mt-5">

		<div class="col-4 mr-5">
			<p>

				<img src="<c:url value="/torneos/${torneoDetalle.foto}"/>"
					width="415px" height="350px" />

			</p>

		</div>
		<div class="col-2 ml-4">
			<h6>Torneo:</h6>
			<h6>${torneoDetalle.nombre}</h6>
			<br>
			<h6>Juego:</h6>
			<h6>${torneoDetalle.juego}</h6>
			<br>
			<h6>Precio:</h6>
			<h6>$ ${torneoDetalle.precio}</h6>
			<br>
			<h6>Fecha:</h6>
			<h6>${torneoDetalle.fecha}</h6>
			<br>
			<h6>Horario:</h6>
			<h6>${torneoDetalle.horario}</h6>
			<br>
		</div>

		<div class="col-4">
			<h6>Dirección:</h6>
			<h6>${torneoDetalle.inmuebleDelTorneo.direccion.calle}
				${torneoDetalle.inmuebleDelTorneo.direccion.numero}</h6>
			<br>
			<h6>Ciudad:</h6>
			<h6>${torneoDetalle.inmuebleDelTorneo.direccion.ciudad.nombre}</h6>
			<br>
			<h6>Provincia:</h6>
			<h6>${torneoDetalle.inmuebleDelTorneo.direccion.ciudad.provincia.nombre}</h6>

			<br>
			<c:if
				test="${(sessionScope.rol=='admin')or(sessionScope.rol=='invitado')}">
				<h6>Distancia:</h6>
				<h6>${torneoDetalle.distanciaConUsuario} km</h6>
			</c:if>
			<br>
			<form:form action="participar" method="POST">

				<input type="hidden" name="torneoId" value="${torneoDetalle.id}">
				<input type="hidden" name="usuarioId" value="${sessionScope.id}">
				
				<c:if
				test="${(torneoDetalle.estadoCompleto == false) and (sessionScope.id != null) and (torneoDetalle.ganador.id == null)}">
					<button class="boton-part btn-outline-success" type="submit" >PARTICIPAR</button>
			</c:if>
	<c:if
				test="${(torneoDetalle.estadoCompleto == false) and (sessionScope.id == null) and (torneoDetalle.ganador.id == null)}">
					<div class="col-2">
					<button class="boton-part btn-outline-success" type="submit" disabled="disabled">PARTICIPAR</button>
					<p> Necesitas <a href="login"> iniciar sesion </a>, para participar en un torneo </p>
					</div>
			</c:if>


					<c:if
				test="${(torneoDetalle.estadoCompleto == true) and (sessionScope.rol != null) and (torneoDetalle.ganador.id == null)}">
				<button class="boton-part btn-outline-success" type="submit" disabled="disabled">Cupo Lleno</button>
			</c:if>
			
				<c:if
				test="${(torneoDetalle.ganador.id != null)}">
				
				<p class="text-success"> Ganador del torneo: ${torneoDetalle.ganador.nombre}</p>
				
			</c:if>
			
			
				
			</form:form>

		</div>

	</div>

</main>
</body>

<style>

.text-success{
	
	color: #00f000;
}

</style>
</html>