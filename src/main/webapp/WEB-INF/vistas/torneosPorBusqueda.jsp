
		<%@include file="header.jsp"%>
		
	<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

	<div class="cont-acciones">
	
		<div class="buscador">
			<form:form action="buscar-torneo" method="GET">

				<label class="ors">Buscar torneo deseado</label>
				</br>
				<input name="busqueda" class="busc" type="search">
				<button class="btn-org" type="submit">Buscar</button>

			</form:form>
			
		</div>

		<div class="organizar-tor">
			<p class="pp">Para organizar tu propio torneo, hac� clikc en el
				bot�n:</p>
			</br> <a class="boton-part" href="ver-formulario-torneo" role="button">
				ORGANIZAR </a> <br> <br>
				
			</br> <a class="boton-part" href="ver-torneos" role="button">
				TORNEOS </a>


		</div>
	</div>
		
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
		
	
		<c:forEach items="${torneosBusqueda}" var="TB">
<tbody>
    <tr>
      <th scope="row"><img src="<c:url value="/torneos/${TB.foto}"/>" width="150px" height="120px"/></th>
      <td>${TB.nombre}</td>
      <td>${TB.juego}</td>
      <td>$ ${TB.precio}</td>
      <td><a class="btn btn-outline-success btn-sm"  href="/proyecto-practica/ver-torneo-detalles?torneoId=${TB.id}&usuarioId=${sessionScope.id}" role="button">ver
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