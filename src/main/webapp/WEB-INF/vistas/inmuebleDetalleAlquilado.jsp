
		<%@include file="header.jsp"%>


	<h2 class="titulo-inmuebles">DETALLES DEL INMUEBLE</h2>

	<main class="container">

		<div class="row mt-5">

			<div class="col-4  ml-4">

				<img src="<c:url value="/inmuebles/${detalleInmuebleAlquilado.foto}"/>"
					width="380px" height="265px" />

			</div>

			<div class="col-4  ml-3">

				<h5>${detalleInmuebleAlquilado.nombre}</h5>
				<br>

				<p>$ ${detalleInmuebleAlquilado.precio}</p>
				<br>

				<p>${detalleInmuebleAlquilado.direccion.calle}
					${detalleInmuebleAlquilado.direccion.numero}</p>
				<br>
				<p>${detalleInmuebleAlquilado.direccion.ciudad.nombre}</p>
				<br>
				<p>${detalleInmuebleAlquilado.direccion.ciudad.provincia.nombre}</p>


			</div>
		</div>

	</main>

</body>
</html>