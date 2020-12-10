
<%@include file="header.jsp"%>

<div class="contenedor-publicar">
	<div class="text-center">


		<c:if test="${not empty errorRegistrar}">
			<h4>
				<span>${errorRegistrar}, volv� a registrarte</span>

			</h4>
			<br>
			<a class="btnEmpezar"
				href="/proyecto-practica/ver-formulario-usuario"> REGISTRARSE </a>
		</c:if>

		<c:if test="${not empty error}">
			<h4>
				<span>${error}</span>

			</h4>
			<br>
			<h6>
				<a class="link" href="ver-formulario-inmueble"> Volv� </a> a
				publicar t� inmueble �Esta vez con foto!
			</h6>

		</c:if>

		<c:if test="${not empty errorSubida}">
			<h4>
				<span>${errorSubida}</span>
			</h4>
			<br>
			<h6>
				<a class="link" href="ver-formulario-inmueble"> Volv� </a> a
				publicar t� inmueble �Error en la carga de la imagen!
			</h6>

		</c:if>

		<c:if test="${not empty errorDireccionInmueble}">
			<h4>
				<span>${errorDireccionInmueble}</span>
			</h4>
			<br>

			<h6>
				<a class="link" href="ver-formulario-inmueble">Volv�</a> a publicar
				t� inmueble �Esta vez con una direcci�n v�lida!
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
		<c:if test="${not empty errorInmueble}">
			<h4>
				<span>${errorInmueble}, necesitas alquilar al menos un
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