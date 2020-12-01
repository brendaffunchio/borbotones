
		<%@include file="header.jsp"%>

	<h2 class="titulo-torn">TORNEOS QUE PARTICIPO</h2>
	
	
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
		
		<c:forEach items="${misTorneosParticipo}" var="TP">

			<tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/torneos/${TP.foto}"/>" width="150px" height="120px"/></th>
      <td>${TP.nombre}</td>
      <td>${TP.juego}</td>
      <td>$ ${TP.precio}</td>
      <td><a class="btn btn-outline-success btn-sm"  href="/proyecto-practica/ver-torneos-participo-detalles?torneoId=${TP.id}"role="button">ver
					detalles</a>  </td>
    </tr>
   
  </tbody>
  
  </c:forEach>
</table>


</body>
</html>