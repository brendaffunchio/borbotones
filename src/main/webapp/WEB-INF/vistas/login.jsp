
<%@include file="header.jsp"%>

<div class="contenedor-publicar">
	<div class="titulo-pub">
		<h2 class="titulo-publicar">INICIAR SESIÓN</h2>
	</div>

	<div class="container">

		<div class="row">

			<div class="card" style="width: 30rem;">

				<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
				<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
				<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
				<form:form action="validar-login" method="POST"
					modelAttribute="usuario">

					<ul class="list-group list-group-flush">
						<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
						<li class="list-group-item"><label class="or text-white">Email</label>

							<form:input path="email" id="email" type="email"
								class="f form-control" /></li>

						<li class="list-group-item"><label class="or text-white">Password</label>

							<form:input path="password" type="password" id="password"
								class="f form-control" /></li>

						<li class="list-group-item">
							<button class="btn-pub btn-outline-danger" Type="Submit" />Iniciar
							sesión
							</button>
						</li>


					</ul>

				</form:form>

				<%--Bloque que es visible si el elemento error no está vacio--%>
				<c:if test="${not empty error}">
					<h4>
						<span>${error}</span>
					</h4>
					<br>
				</c:if>

			</div>


		</div>


	</div>


</div>

<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
	window.jQuery
			|| document
					.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
</script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>


<style>
.card {
	background: #f71df6;
}

.container {
	margin: 0px auto;
	width: 20%;
}

.list-group-item {
	background-color: black;
}

.btn-pub {
	width: 200px;
}

.btn-outline-danger{

border: 1px solid #f71df6;
	
	color: #f71df6;

}

.btn-outline-danger:hover{

border: 1px solid #f71df6;
	
	color: black;
	
	background-color:#f71df6  ;

}


</style>


</html>
