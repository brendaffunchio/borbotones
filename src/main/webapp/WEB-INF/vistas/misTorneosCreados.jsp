
		<%@include file="header.jsp"%>

	<h2 class="titulo-torn">MIS TORNEOS</h2>
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
      <th scope="col">Torneo</th>
      <th scope="col">Juego</th>
      <th scope="col">Precio</th>
       <th scope="col">Acciones</th>
    </tr>
  </thead>
		
		<c:forEach items="${misTorneosCreados}" var="TC">
<tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/torneos/${TC.foto}"/>" width="150px" height="120px"/></th>
      <td>${TC.nombre}</td>
      <td>${TC.juego}</td>
      <td>$ ${TC.precio}</td>
      <td><a class="btn btn-outline-success btn-sm"  href="/proyecto-practica/ver-mis-torneos-detalles?torneoId=${TC.id}" role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>

</body>
</html>