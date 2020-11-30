
		<%@include file="header.jsp"%>

	<h2 class="titulo-inmuebles">INMUEBLES DISPONIBLES</h2>

	<div class="cont-acciones">
		<div class="buscador">

			<form:form action="buscar-inmueble" method="GET">

				<label class="ors">Buscar inmueble</label>
				</br>
				<input name="busqueda" class="busc" type="search">
				<button class="btn-org" type="submit" id="buscarInmueble">Buscar</button>


			</form:form>



		</div>



		<div class="organizar-tor">
			<div>
				<a class="boton-pub" href="ver-formulario-inmueble" role="button">
					PUBLICAR </a>

			</div>

			<br> <br>
			<div>

				<a class="boton-pub" href="ver-inmuebles" role="button">
					INMUEBLES </a>
			</div>

		</div>
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
	
   
		<c:forEach items="${inmueblesBusqueda}" var="IB">

		<tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/inmuebles/${IB.foto}"/>" width="150px" height="120px"/></th>
      <td>${IB.nombre}</td>
      <td>${IB.direccion.calle} ${I.direccion.numero}</td>
      <td>$ ${IB.precio}</td>
      <td><a class="btn btn-outline-warning btn-sm" href="/proyecto-practica/ver-inmueble-detalle?inmuebleId=${IB.id}" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>

	<c:if test="${not empty error}">
	<h6>
		${error}
	</h6>
	<br>
</c:if>
	

</body>
</html>