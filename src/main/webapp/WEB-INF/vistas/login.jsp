
		<%@include file="header.jsp"%>
		
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">INICIAR SESIÓN</h2>
		</div>
		<div class="formulario-publicar">
			<%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
			<%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
			<%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
			<form:form action="validar-login" method="POST"
				modelAttribute="usuario">
				

				<%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
				<label class="or">Email</label>
				</br>
				<form:input path="email" id="email" type="email"
					class="f" />
					<label class="or">Password</label>
				</br>
				<form:input path="password" type="password" id="password"
					class="f" />

				<button class="btn-pub" Type="Submit"/>Iniciar sesión</button>
			</form:form>

			<%--Bloque que es visible si el elemento error no estÃ¡ vacÃ­o	--%>
			<c:if test="${not empty error}">
				<h4>
					<span>${error}</span>
				</h4>
				<br>
			</c:if>
		</div>
	</div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
