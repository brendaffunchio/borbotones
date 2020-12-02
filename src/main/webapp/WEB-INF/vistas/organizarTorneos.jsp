
		<%@include file="header.jsp"%>

	<div class="contenedor-organizar">
		<div class="titulo-org">
			<h2 class="titulo-organizar">ORGANIZÁ TU TORNEO</h2>
		</div>
		<div class="text-center">
		<c:if test="${not empty errorInmueble}">
				<p>
					<span>${errorInmueble}</span>
				</p>
				<br>
			</c:if>
			</div>
		<div class="formulario-torneo">
			<form:form action="crear-torneo" method="POST"
				enctype="multipart/form-data" modelAttribute="torneo">

				<input type="hidden" value="${sessionScope.id}" name="creadorId">

				<label class="or">Nombre del torneo</label>
				<br>
				<form:input class="f" path="nombre" type="text" required="required" />
				<br>
				<label class="or">Juego</label>
				<br>
				<form:input class="f" path="juego" type="text" required="required" />
				<br>
				<label class="or">Categoria</label>
				<br>

				<form:select path="categoria">
					<form:option value="Deporte">Deporte</form:option>
					<form:option value="Aventura">Aventura</form:option>
					<form:option value="Terror">Terror</form:option>
					<form:option value="Accion">Accion</form:option>
					<form:option value="Fantasia">Fantasia</form:option>
					<form:option value="Otra">Otra</form:option>

				</form:select>

				<br>
				<label class="or">Fecha</label>
				<br>
				<form:input class="f" path="fecha" type="date" required="required" />
				<br>

				<label class="or">Horario</label>
				<br>
				<form:input class="f" path="horario" type="time" required="required" />

				</br>

				<label class="or" for="inmuebleId">Inmueble</label>
				<br>

				<select name="inmuebleId">
				
					<c:forEach items="${inmuebles}" var="I">

						<option value="${I.id}">${I.nombre}</option>

					</c:forEach>

				</select>
				<br>
				<label class="or">Cupo</label>
				<br>
				<form:input class="f" path="cupo" type="number" required="required" />
				<br>
				<label class="or">Precio</label>
				<br>
				<form:input class="f" path="precio" type="number"
					required="required" />

				</br>

				<label class="or">Imagen del inmueble</label>
				<input type="file" name="file">

				</br>

				<div class="boton-organizar">
					<button class="btn-org" Type="Submit">CREAR TORNEO</button>

				</div>
			</form:form>
              
			
		</div>
	</div>
</body>
</html>