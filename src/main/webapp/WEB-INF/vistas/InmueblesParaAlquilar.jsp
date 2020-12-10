
		<%@include file="header.jsp"%>
		
	
	<h2 class="titulo-inmuebles">INMUEBLES DISPONIBLES ${ruta}</h2>
	
	<div class="row-12">
			<div class="col 4">
			<form:form action="buscar-inmueble" method="GET">


			<div class="form-group col-sm-6">
	

				<label class="ors">Buscar Inmueble</label>
				</br>
			</div>
			
			<div class="form-group col-sm-5">
				<label > Elegir por provincia </label>
				<select class="form-control" name="provinciaId" multiple>
					<option selected value="0">Seleccionar provincia</option>
						<c:forEach items="${provincias}" var="P">
				<option value="${P.id}">${P.nombre}</option>
				</c:forEach>
				  </select>

				  <label >Ingresar ciudad </label>
			
				<input name="ciudad" class="busc" type="search">

				</div>
				
		
			
				<button class="btn btn-outline-warning" type="submit">Buscar</button>
				</form:form>
				</div>

			
			<div class="col-6">
		<h6>FILTRAR INMUEBLES</h6>
		<form:form action="filtrar-inmuebles" method="GET">
			<label for="desde">Desde:</label>
			<input name="desde" type="number" placeholder="precio">
			<br>
			<label for="hasta">Hasta: </label>
			<input name="hasta" type="number" placeholder="precio">
			<br>
			<button class="btn btn-outline-warning" type="submit">FILTRAR</button>
		</form:form>
	</div>
	
     <c:if test="${sessionScope.rol=='admin'}">
     <div class="col-2">
		<div class="organizar-tor">
			<a class="btn btn-outline-warning" href="ver-formulario-inmueble" role="button">
				PUBLICÁ TU INMUEBLE </a>
				
				 </div>
				
		</div>
		
		</c:if>
		
		
			
		
			</div>
		<c:if test="${not empty error}">
	<h6>
		${error}
	</h6>
	<br>
</c:if>
	<br>
	<div class="row-12">

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
      <th scope="row"><img src="<c:url value="/inmuebles/${I.foto}"/>" width="150px" height="120px"/></th>
      <td>${I.nombre}</td>
      <td>${I.direccion.calle} ${I.direccion.numero}</td>
      <td>$ ${I.precio}</td>
      <td><a class="btn btn-outline-warning btn-sm" href="/proyecto-practica/ver-inmueble-detalle?inmuebleId=${I.id}" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>

	</div>

</body>
</html>