
<%@include file="header.jsp"%>

<div class="contenedor-publicar">
	<div class="text-center">

		<c:if test="${not empty error}">
			<h4>
				<span>${error}</span>
				
			</h4>
			<br>
			<h6>
			<a class="link" href="ver-formulario-inmueble"> Volvé </a> a publicar tú inmueble
			¡Esta vez con foto!
		</h6>
			
		</c:if>

		<c:if test="${not empty errorSubida}">
			<h4>
				<span>${errorSubida}</span>
			</h4>
			<br>
			<h6>
			<a class="link" href="ver-formulario-inmueble"> Volvé </a> a publicar tú inmueble
			¡Error en la carga de la imagen!
		</h6>
			
		</c:if>
	
			<c:if test="${not empty errorDireccionInmueble}">
				<p>
					<span>${errorDireccionInmueble}</span>
				</p>
				<br>
				
			<h6>
			<a class="link" href="ver-formulario-inmueble">Volvé</a> a publicar tú inmueble
			¡Esta vez con una dirección válida!
		</h6>
			
			</c:if>
			<c:if test="${not empty errorDireccionUsuario}">
				<p>
					<span>${errorDireccionUsuario}</span>
				</p>
				<br>
				
			<h6>
			<a class="link" href="ver-formulario-usuario">  Volvé</a> a registrarte
			¡Esta vez con una dirección válida!
		</h6>
			
			</c:if>
				<c:if test="${not empty errorParticipar}">
				<p>
					<span>${errorParticipar}</span>
				</p>
				<br>
			
			</c:if>
			
				<c:if test="${not empty errorAlquilar}">
				<p>
					<span>${errorAlquilar}</span>
				</p>
				<br>
			
			</c:if>
			
			
				<c:if test="${not empty errorDesubscribirse}">
				<p>
					<span>${errorDesubscribirse}</span>
				</p>
				<br>
			
			</c:if>
			<c:if test="${not empty errorGanador}">
				<p>
					<span>${errorGanador}</span>
				</p>
				<br>
			
			</c:if>
			
		<br>
		
		<img src="img/fileNotFound.png" width="300px" height="300px">

	</div>
</div>

</body>
</html>