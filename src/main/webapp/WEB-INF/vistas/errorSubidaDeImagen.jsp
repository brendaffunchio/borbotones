
		<%@include file="header.jsp"%>
	
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">	<c:if test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
				</c:if></h2> 
			<br>
			<h6> <a href="ver-formulario-inmueble"> Volvé </a> a publicar tú inmueble ¡Esta vez con foto!</h6>
			<img src="img/fileNotFound.png" width="300px" height="300px">
			
		</div>
	</div>

</body>
</html>