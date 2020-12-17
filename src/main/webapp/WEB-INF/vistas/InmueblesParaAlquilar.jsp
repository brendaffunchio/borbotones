
<%@include file="header.jsp"%>


<h2 class="titulo-inmuebles">INMUEBLES DISPONIBLES</h2>

<div class="container">

	<div class="row">
		<div class="col-8">
			<form:form action="buscar-inmueble" method="GET">


				<div class="form-group col-sm-6">


					<label class="ors">Buscar Inmueble</label>

				</div>

				<div class="form-group col-sm-5">
					<label> Elegir por provincia </label> <select class="form-control"
						name="provinciaId" multiple>
						<option selected value="0">Seleccionar provincia</option>
						<c:forEach items="${provincias}" var="P">
							<option value="${P.id}">${P.nombre}</option>
						</c:forEach>
					</select>

				</div>


				<div class="form-group col-sm-5">
					<label>Ingresar ciudad </label> <input name="ciudad"
						class="busc form-control" type="search">

				</div>

				<div class="form-group col-sm-5">

					<button class="btn btn-outline-warning" type="submit">BUSCAR</button>

				</div>
			</form:form>

			<div class="form-group col-sm-5">

				<c:if test="${sessionScope.rol=='admin'}">



					<a class="btn btn-outline-warning" href="ver-formulario-inmueble"
						role="button"> PUBLICÁ TU INMUEBLE </a>
				</c:if>


			</div>

		</div>



		<div class="organizar-tor col-4 ">

			<form:form action="filtrar-inmuebles" method="GET">
				<label class="ors">FIltrar Inmuebles</label>

				<div class="form-group mt-2">

					<label for="desde">Desde:</label> <input class="form-control col-8"
						name="desde" type="number" placeholder="precio" step="0.01">

				</div>
				<div class="form-group">
					<label for="hasta">Hasta: </label> <input
						class="form-control col-8" name="hasta" type="number"
						placeholder="precio" step="0.01">
				</div>


				<div class="form-group">
					<button class="btn btn-outline-warning" type="submit">FILTRAR</button>
				</div>
			</form:form>




		</div>


	</div>

</div>

<div class="container">

	

		<c:if test="${not empty error}">
			<h6>${error}</h6>
			<br>
		</c:if>
		<br>
		<div class="table-responsive col-sm-12">

		<table class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">Imagen</th>
					<th scope="col">Inmueble</th>
					<th scope="col">Dirección</th>
					<th scope="col">Precio</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<c:forEach items="${inmuebles}" var="I">
				<tbody>
					<tr>
						<th scope="row"><img
							src="<c:url value="/inmuebles/${I.foto}"/>" width="150px"
							height="120px" /></th>
						<td>${I.nombre}</td>
						<td>${I.direccion.calle} ${I.direccion.numero}</td>
						<td>$ ${I.precio}</td>
						<td><a class="btn btn-outline-warning btn-sm"
							href="/proyecto-practica/ver-inmueble-detalle?inmuebleId=${I.id}"
							role="button">ver detalles</a></td>
					</tr>

				</tbody>

			</c:forEach>
		</table>

	</div>
	
	</div>


</body>


</html>