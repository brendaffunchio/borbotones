
<%@include file="header.jsp"%>

<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>


<main class="container">
	<div class="row mt-5">

		<div class="col-1"></div>

		<div class="col-4 mr-5">


			<img src="<c:url value="/torneos/${miTorneoDetalle.foto}"/>"
				width="415px" height="350px" />





		</div>
		<div class="col-2 ml-4">
			<h6>Torneo:</h6>
			<h6>${miTorneoDetalle.nombre}</h6>
			<br>
			<h6>Juego:</h6>
			<h6>${miTorneoDetalle.juego}</h6>
			<br>
			<h6>Precio:</h6>
			<h6>$ ${miTorneoDetalle.precio}</h6>
			<br>
			<h6>Fecha:</h6>
			<h6>${miTorneoDetalle.fecha}</h6>
			<br>

		</div>



		<div class="col-3">

			<h6>Horario:</h6>
			<h6>${miTorneoDetalle.horario}</h6>
			<br>

			<h6>Dirección:</h6>
			<h6>${miTorneoDetalle.inmuebleDelTorneo.direccion.calle}
				${torneoDetalle.inmuebleDelTorneo.direccion.numero}</h6>
			<br>
			<h6>Ciudad:</h6>
			<h6>${miTorneoDetalle.inmuebleDelTorneo.direccion.ciudad.nombre}</h6>
			<br>
			<h6>Provincia:</h6>
			<h6>${miTorneoDetalle.inmuebleDelTorneo.direccion.ciudad.provincia.nombre}</h6>
			<br>
			<h6>Inscriptos:</h6>
			<h6>${miTorneoDetalle.inscriptos}</h6>


			<c:if test="${(miTorneoDetalle.ganador.id != null)}">
				<p>¡Ganador del torneo! ${miTorneoDetalle.ganador.nombre}</p>
			</c:if>


		</div>

		<div class="col-7"></div>

		<div class="col-4">

			<div class="col">
				<a class="boton-part  btn-outline-success ml-5"
					href="ver-lista-de-participantes?torneoId=${miTorneoDetalle.id}"
					role="button"> PARTICIPANTES </a>
			</div>

			<div class="col">

				<br>

				<c:if test="${(miTorneoDetalle.ganador.id == null)}">
					<a class="boton-part btn-outline-success ml-5"
						href="ver-lista-de-participantes-para-elegir-ganador?torneoId=${miTorneoDetalle.id}"
						role="button"> ELEGIR GANADOR </a>
				</c:if>


			</div>

		</div>

	</div>

</main>
</body>
</html>