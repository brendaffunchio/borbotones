
<%@include file="header.jsp"%>

<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>


<div class="container">

	<div class="row">

		<div class="col-7">

			<form:form action="buscar-torneo" method="GET">
				<div class="form-group col-sm-5">
					<label class="ors">Buscar torneo</label>
				</div>
				<div class="form-group col-sm-5">
					<label> Elegir por categoría </label> <select class="form-control"
						name="categoria" multiple>
						<option selected value="">Categoría</option>
						<option value="Deporte">Deporte</option>
						<option value="Aventura">Aventura</option>
						<option value="Terror">Terror</option>
						<option value="Accion">Accion</option>
						<option value="Fantasia">Fantasia</option>
						<option value="Otra">Otra</option>
					</select>

				</div>

				<div class="form-group col-sm-5">

					<label>Ingresar juego </label> <br> 
					<input class="busc form-control col-12"
					 type="text" name="juego">

				</div>

				<div class="form-group col-sm-5">

					<button class="btn btn-outline-success" type="submit">BUSCAR</button>
				</div>


			</form:form>



			<div class="form-group col-sm-8">

				<c:if test="${sessionScope.id != null }">

					<a class="btn btn-outline-success"
						href="ver-formulario-torneo?usuarioId=${sessionScope.id}"
						role="button"> ORGANIZÁ TU TORNEO </a>

				</c:if>


	<c:if test="${sessionScope.id == null }">
				<button class="btn btn-outline-success" disabled="disabled">ORGANIZÁ
					TU TORNEO</button>
	</c:if>
			</div>
			
			
			<div class="form-group col-sm-12">
			<c:if test="${sessionScope.id == null }">
				
					<h6>
				Necesitas <a href="login"> iniciar sesion </a> para
				ordenar/filtrar/organizar un torneo
			</h6>
			
			</c:if>
				
				</div>

		</div>


		<c:if test="${sessionScope.id != null }">

			<div class="col-4">

				<form:form action="filtrar-torneos" method="GET">

					<div class="form-group col-sm-8">
						<label class="ors">Filtrar Torneos</label> </br>
					</div>

					<div class="form-group col-sm-8">
						<label for="desde">Desde:</label> <input name="desde"
							type="number" class="form-control" placeholder="km" step="0.01">
					</div>

					<div class="form-group col-sm-8">
						<label for="hasta">Hasta:</label> <input name="hasta"
							type="number" class="form-control" placeholder="km" step="0.01">
					</div>

					<div class="form-group col-sm-8">
						<button class="btn btn-outline-success" type="submit">FILTRAR</button>

					</div>


					<div class="form-group col-sm-12">
						<a class="btn btn-outline-success"
							href="ver-torneos-ordenados-distancia">ORDENAR MAS CERCANOS</a>

					</div>
					
		
				</form:form>
			</div>

		</c:if>

		<c:if test="${sessionScope.rol== null}">

			<div class="col-4">

				<form:form action="filtrar-torneos" method="GET">

					<div class="form-group col-sm-8">
						<label class="ors">Filtrar Torneos</label> </br>
					</div>

					<div class="form-group col-sm-8">

						<label for="desde">Desde:</label> <input name="desde"
							class="form-control" type="number" placeholder="km"
							disabled="disabled">
					</div>

					<div class="form-group col-sm-8">

						<label for="hasta">Hasta:</label> <input name="hasta"
							type="number" class="form-control" placeholder="km"
							disabled="disabled">
					</div>

					<div class="form-group col-sm-8">
						<button class="btn btn-outline-success" type="submit"
							disabled="disabled">FILTRAR</button>

					</div>
				</form:form>

				<div class="form-group col-sm-10">



					<button class="btn btn-outline-success" disabled="disabled">ORDENAR
						MAS CERCANOS</button>

				</div>
				
				
				
			</div>

	
		</c:if>

	</div>

</div>



<div class="container">


	<div class="table-responsive col-sm-12">
	<c:if test="${not empty error}">

	<h6>${error}</h6>


</c:if>
	
		<table class="table table-hover table-dark">
			<thead>
				<tr>
					<th scope="col">Imagen</th>
					<th scope="col">Torneo</th>
					<th scope="col">Juego</th>
					<th scope="col">Precio</th>
					<c:if
						test="${(sessionScope.rol=='admin')or(sessionScope.rol=='invitado')}">
						<th>Distancia</th>

					</c:if>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<c:forEach items="${torneos}" var="T">
				<tbody>
					<tr>
						<th scope="row"><img
							src="<c:url value="/torneos/${T.foto}"/>" width="150px"
							height="120px" /></th>
						<td>${T.nombre}</td>
						<td>${T.juego}</td>
						<td>$ ${T.precio}</td>
						<c:if
							test="${(sessionScope.rol=='admin')or(sessionScope.rol=='invitado')}">

							<td>${T.distanciaConUsuario}km</td>
						</c:if>
						<td><a class="btn btn-outline-success btn-sm"
							href="/proyecto-practica/ver-torneo-detalles?torneoId=${T.id}"
							role="button">ver detalles</a></td>
					</tr>

				</tbody>


			</c:forEach>
		</table>
	</div>

</div>
</body>

</html>