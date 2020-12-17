
<%@include file="header.jsp"%>

<div class="contenedor-organizar">
	<div class="titulo-org">
		<h2 class="titulo-organizar">ORGANIZÁ TU TORNEO</h2>
	</div>

	<div class="container">

		<div class="row">

			<div class="text-center">
				<c:if test="${not empty errorInmueble}">
					<p>
						<span>${errorInmueble}, necesitas alquilar al menos un
							inmueble para organizar un torneo</span>
					</p>
					<br>
				</c:if>
			</div>

			<div class="card" style="width: 33rem;">

				<form:form action="crear-torneo" method="POST"
					enctype="multipart/form-data" modelAttribute="torneo">

					<ul class="list-group list-group-flush">

						<li class="list-group-item"><input type="hidden"
							value="${sessionScope.id}" name="creadorId"> <label
							class="or text-white">Nombre del torneo</label> <form:input
								class="f form-control" path="nombre" type="text"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Juego</label>

							<form:input class="f form-control" path="juego" type="text"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Categoria</label>

							<br> <form:select path="categoria">
								<form:option value="Deporte">Deporte</form:option>
								<form:option value="Aventura">Aventura</form:option>
								<form:option value="Terror">Terror</form:option>
								<form:option value="Accion">Accion</form:option>
								<form:option value="Fantasia">Fantasia</form:option>
								<form:option value="Otra">Otra</form:option>

							</form:select></li>

						<li class="list-group-item"><label class="or text-white">Fecha</label>

							<form:input class="f form-control" path="fecha" type="date"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Horario</label>

							<form:input class="f form-control" path="horario" type="time"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white"
							for="inmuebleId">Inmueble</label> <select name="inmuebleId"
							class="custom-select" multiple>

								<c:forEach items="${inmuebles}" var="I">

									<option value="${I.id}">${I.nombre}</option>

								</c:forEach>

						</select></li>

						<li class="list-group-item"><label class="or text-white">Cupo</label>

							<form:input class="f form-control" path="cupo" type="number"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Precio</label>

							<form:input class="f form-control" path="precio" type="number"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Imagen
								del inmueble</label> <input type="file" name="file"></li>

						<li class="list-group-item">
							<div class="boton-organizar">
								<button class="btn-org btn-outline-success" Type="Submit">CREAR
									TORNEO</button>

							</div>
						</li>



					</ul>

				</form:form>
			</div>

		</div>

	</div>
</div>
</body>

<style>

.card{

background: #00f000;

}

.container {
	margin: 0px auto;
	width: 35%;
}

.list-group-item {
	background-color: black;
}

.btn-org{
	
	width: 200px;
}



</style>
</html>