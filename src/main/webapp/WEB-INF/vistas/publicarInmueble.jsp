
<%@include file="header.jsp"%>

<div class="contenedor-publicar">
	<div class="titulo-pub">
		<h2 class="titulo-publicar">PUBLICÁ TU INMUEBLE</h2>
	</div>

	<div class="container">

		<div class="row">

			<div class="card" style="width: 39rem;">

				<form:form action="crear-inmueble" method="POST"
					enctype="multipart/form-data" modelAttribute="inmueble">

					<ul class="list-group list-group-flush">
						<li class="list-group-item text-white"><label class="or">Nombre
								del inmueble</label> 
								
								<form:input class="f text-white form-control" path="nombre"
								type="text" required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Provincia</label>
							<br> <select class="custom-select" multiple>

								<c:forEach items="${provincias}" var="P">

									<option value="provincia">${P.nombre}</option>

								</c:forEach>
						</select></li>
						<li class="list-group-item"><label for="nombreCiudad"
							class="or text-white" for="ciudades">Ciudad</label> <br> <select class="custom-select" multiple>
								<c:forEach items="${ciudades}" var="C">
								
			

									<option name="nombreCiudad" value="ciudad">
										${C.nombre}</option>
						</c:forEach>
						</select></li>

						<li class="list-group-item"><label class="or text-white">Direccion</label>

							<input name="calle" class="f form-control" type="text" placeholder="calle"
							required /> <input name="numero" class="f form-control" type="number"
							placeholder="numero" required /></li>


						<li class="list-group-item"><label for="precio"
							class="or text-white">Valor del inmueble</label> </br> <form:input
								name="precio" class="f form-control" path="precio" type="number"
								required="required" /></li>

						<li class="list-group-item"><label class="or text-white">Imagen
								del inmueble</label> <input type="file" name="file" /></li>

						<li class="list-group-item">
							<button class="btn-pub btn-outline-warning" Type="Submit" />PUBLICAR
							</button>
						</li>

					</ul>

				</form:form>



			</div>


		</div>

	</div>


</div>

</body>

<style> 

.card{

background: #ecff16;

}

.container{

	margin: 0px auto;
	width: 35%;
	
}

.list-group-item{

	background-color: black;
}

.btn-pub{

width: 200px;
}

</style>
</html>