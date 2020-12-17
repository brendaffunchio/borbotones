
		<%@include file="header.jsp"%>
		
	<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

	
<main class="container">
	<div class="row mt-5">
		
		<div class="col-4 mr-5">
		<p>
			
			<img src="<c:url value="/torneos/${torneoParticipoDetalle.foto}"/>" width="415px" height="350px"/>
				
				</p>
		
		</div>
		<div class="col-2 ml-4">
		<h6>Torneo:</h6>
				<h6>${torneoParticipoDetalle.nombre}</h6>
				<br>
				<h6>Juego:</h6>
				<h6>${torneoParticipoDetalle.juego}</h6>
				<br>
				<h6>Precio:</h6>
				<h6>$ ${torneoParticipoDetalle.precio}</h6>
				<br>
				<h6>Fecha:</h6>
				<h6>${torneoParticipoDetalle.fecha}</h6>
				<br>
				<h6>Horario:</h6>
				<h6>${torneoParticipoDetalle.horario}</h6>
				<br>
				</div>
				
				<div class="col-2">
				<h6>Dirección:</h6>
				<h6>${torneoParticipoDetalle.inmuebleDelTorneo.direccion.calle} ${torneoParticipoDetalle.inmuebleDelTorneo.direccion.numero}</h6>
				<br>
				<h6>Ciudad:</h6>
				<h6>${torneoParticipoDetalle.inmuebleDelTorneo.direccion.ciudad.nombre}</h6>
				<br>
				<h6>Provincia:</h6>
				<h6>${torneoParticipoDetalle.inmuebleDelTorneo.direccion.ciudad.provincia.nombre}</h6>
				
				<br>
			
			<c:if test="${(torneoParticipoDetalle.ganador.id == null)}">
			
			<form:form action="desubscribirse" method="POST">
			
			<input type="hidden" name="torneoId" value="${torneoParticipoDetalle.id}">
			<input type="hidden" name="usuarioId" value ="${sessionScope.id}">
			
			
			
			<button type="submit" class="boton-part btn-outline-success">DESUSCRIBIRSE</button>
			

			</form:form>
			
					</c:if>
					
							<c:if
				test="${(torneoParticipoDetalle.ganador.id != null)}">
				<p> ¡Ganador del torneo! ${torneoParticipoDetalle.ganador.nombre} </p>
			</c:if>
			</div>	
				
			</div>

</main>
</body>

<style> 

.boton-part{

width: 200px;
}

</style>
</html>