
		<%@include file="header.jsp"%>
		
	<h2 class="titulo-torn">TORNEOS DISPONIBLES</h2>

	<div class="row-12">
	
		<div class="col-6">
		
			<form:form action="buscar-torneo" method="GET">


	<div class="form-group">
	
				<label class="ors">Buscar torneo</label>
				</br>
			</div>

			<div class="form-group col-sm-5">
				<label > Elegir por categoría </label>
				<select class="form-control" name="categoria" multiple>
					<option selected value="">Categoría</option>
					<option value="Deporte">Deporte</option>
					<option value="Aventura">Aventura</option>
					<option value="Terror">Terror</option>
					<option value="Accion">Accion</option>
					<option value="Fantasia">Fantasia</option>
					<option value="Otra">Otra</option>
				  </select>

			 
				  <label >Ingresar juego </label>
				<input class="busc" type="text" name="juego">

				</div>
				
			<div class="col-6">
			
				<button class="btn btn-outline-success" type="submit">Buscar</button>
				
				</div>

			</form:form>
			
			
			<div class="col-6">
			
			<a class="btn btn-outline-success" href="ver-torneos-filtrados-distancia">FILTRAR MAS CERCANOS</a>
			</div>
			 </div>
			 
			 
			 
			<div class="col-2">
		
		
			</br> <a class="btn btn-outline-success" href="ver-formulario-torneo?usuarioId=1" role="button">
				ORGANIZÁ TU TORNEO </a>

	
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