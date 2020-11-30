
		<%@include file="header.jsp"%>
		
	<h2 class="titulo-inmuebles">INMUEBLES DISPONIBLES</h2>


	<div class="cont-acciones">
		<div class="buscador">

			<form:form action="buscar-inmueble" method="GET">

				<label class="ors">Buscar inmueble</label>
				</br>
				<p>Seleccionar provincia</p>
				<br>
				<select name="provinciaId">
				<c:forEach items="${provincias}" var="P">
				<option value="${P.id}">${P.nombre}</option>
				</c:forEach>
				
				</select>
				<p>Escribir ciudad</p>
				<input name="ciudad" class="busc" type="search">
				<button class="btn btn-outline-warning btn-sm" type="submit">Buscar</button>


			</form:form>



		</div>
		<c:choose>
     <c:when test="${sessionScope.rol=='admin'}">
		<div class="organizar-tor">
			<a class="btn btn-outline-warning" href="ver-formulario-inmueble" role="button">
				PUBLICÁ TU INMUEBLE </a>

		</div>
</c:when>
</c:choose>
	</div>

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

	

</body>
</html>