
<%@include file="header.jsp"%>

<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

<div class="row-12">

	<div class="col-6">

		<form:form action="buscar-torneo" method="GET">
			<div class="form-group">
				<label class="ors">Buscar torneo</label> </br>
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
				</select> <label>Ingresar juego </label> 
				<br>
				<input class="busc" style="width:180px" type="text"
					name="juego">
					<br><br>
				<button class="btn btn-outline-success" type="submit">Buscar</button>
</div>
		</form:form>
		</div>
</br> 

		<c:if test="${sessionScope.id != null }">
			
			
	</br> 
	<div class="col-4">
		<h6>FILTRAR TORNEOS</h6>
		<form:form action="filtrar-torneos" method="GET">
			<label for="desde">Desde:</label>
			<input name="desde" type="number" placeholder="km">
			</br> 
			<label for="hasta">Hasta:</label>
			<input name="hasta" type="number" placeholder="km">
			</br> 
			<button class="btn btn-outline-success" type="submit">FILTRAR</button>
		</form:form>
	
</br> 
		

<a class="btn btn-outline-success"
					href="ver-torneos-ordenados-distancia">ORDENAR MAS CERCANOS</a>
			
			<a class="btn btn-outline-success"
				href="ver-formulario-torneo?usuarioId=${sessionScope.id}"
				role="button"> ORGANIZÁ TU TORNEO </a>


				

		</div>
		
	</c:if>

	<c:if test="${sessionScope.rol== null}">

<div class="col-4">
		<h6>FILTRAR TORNEOS</h6>
		<form:form action="filtrar-torneos" method="GET" >
			<label for="desde">Desde:</label>
			<input name="desde" type="number" placeholder="km" disabled="disabled">
			</br> 
			<label for="hasta">Hasta:</label>
			<input name="hasta" type="number" placeholder="km" disabled="disabled">
			</br> 
			<button class="btn btn-outline-success" type="submit" disabled="disabled">FILTRAR</button>
		</form:form>
	</div></br> 
			<button class="btn btn-outline-success"
					disabled="disabled">ORDENAR MAS CERCANOS</button>
				</br> </br> 	
			<button class="btn btn-outline-success"
				disabled="disabled">ORGANIZÁ TU TORNEO</button>

			</br></br> 
			<h6>
				Necesitas <a href="login"> iniciar sesion </a> para ordenar/filtrar/organizar un
				torneo
			</h6>

	</c:if>

</div>

<c:if test="${not empty error}">

 <h6> ${error} </h6>


</c:if>


<div class="table-responsive col-sm-12">


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
					<th scope="row"><img src="<c:url value="/torneos/${T.foto}"/>"
						width="150px" height="120px" /></th>
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


</body>
</html>