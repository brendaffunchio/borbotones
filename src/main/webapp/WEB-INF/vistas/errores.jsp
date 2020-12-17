
<%@include file="header.jsp"%>

<div class="contenedor-publicar">
	<div class="text-center">


		<c:if test="${not empty errorRegistrar}">
			<h4>
				<span>${errorRegistrar}, volvé a registrarte</span>

			</h4>
			<br>
			<a class="btnEmpezar"
				href="/proyecto-practica/ver-formulario-usuario"> REGISTRARSE </a>
		</c:if>

		<c:if test="${not empty errorInmueble}">
			<h4>
				<span>${errorInmueble}</span>

			</h4>
			<br>
			<h6>
				<a class="link" href="ver-formulario-inmueble"> Volvé </a> a
				publicar tú inmueble.
			</h6>

		</c:if>


		<c:if test="${not empty errorParticipar}">
			<h4>
				<span>${errorParticipar}</span>
			</h4>
			<br>

		</c:if>

		<c:if test="${not empty errorAlquilar}">
			<h4>
				<span>${errorAlquilar}</span>
			</h4>
			<br>

		</c:if>


		<c:if test="${not empty errorDesubscribirse}">
			<h4>
				<span>${errorDesubscribirse}</span>
			</h4>
			<br>

		</c:if>
		<c:if test="${not empty errorGanador}">
			<h4>
				<span>${errorGanador}</span>
			</h4>
			<br>
			</c:if>
			
			<c:if test="${not empty errorFotoTorneo}">
			<h4>
				<span>${errorFotoTorneo}</span>

			</h4>
			<br>
			<h6>
				<a class="link" href="organizarTorneos"> Volvé </a> a
				publicar tú torneo. ¡Esta vez con foto!
			</h6>

		</c:if>

		
		<c:if test="${not empty errorInmuebleInexistente}">
			<h4>
				<span>${errorInmuebleInexistente}, necesitas alquilar al menos un
					inmueble para poder organizar un torneo</span>
			</h4>
			<br>

		</c:if>
		<c:if test="${not empty errorCreadorTorneo}">

		</c:if>

		<br> <img src="img/fileNotFound.png" width="300px" height="300px">

	</div>
</div>

</body>
</html>