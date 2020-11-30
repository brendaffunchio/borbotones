
		<%@include file="header.jsp"%>


	<h2 class="titulo-inmuebles">MIS INMUEBLES</h2>
<c:if test="${not empty error}">
				<h2 class="text-center">
					${error}
				</h2>
				<br>
			</c:if>

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
	
		<c:forEach items="${misInmueblesAlquilados}" var="IA">

		<tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/inmuebles/${IA.foto}"/>" width="150px" height="120px"/></th>
      <td>${IA.nombre}</td>
      <td>${IA.direccion.calle} ${IA.direccion.numero}</td>
      <td>$ ${IA.precio}</td>
      <td><a class="btn btn-outline-warning btn-sm" href="/proyecto-practica/ver-inmueble-alquilado-detalle?inmuebleId=${IA.id}" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>
</body>
</html>