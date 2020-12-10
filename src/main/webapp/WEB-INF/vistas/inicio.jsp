
		<%@include file="header.jsp"%>
		
	<c:if test="${sessionScope.id == null}">

		<div class="row justify-content-center ">

			<div class="col-4 border border-info">
				<p>

					Nuestro sitio es ideal para: <br> <br> - Elegir un lugar
					donde prepararte, stremear o jugar. <br> <br> -
					Participar de un evento con los mejores jugadores del país. <br>

					<br> - Crear un torneo para jugar con tus amigos/as. <br>
					<br> - Competir con profesionales o con quien desees.
				</p>
			</div>
			<div class="col-4 border border-info">

				<p>
					Requisitos: <br> <br> - Estar logueado para organizar un
					torneo, participar del mismo o alquilar un inmueble. <br> <br>
					- Tener al menos un inmueble alquilado para poder organizar el
					torneo.
				</p>
				</br> </br>
				<div class="contenedorBotonesInicio">

					<a class="btnEmpezar"
						href="/proyecto-practica/ver-formulario-usuario"> REGISTRARSE
					</a> <a class="btnEmpezar" href="/proyecto-practica/login"> INICIAR
						SESIÓN </a>

				</div>
			</div>
		</div>

	</c:if>
	<c:if
		test="${sessionScope.id != null}">
		<h2 class="text-center">RANKING DE GANADORES</h2>
		<div class="row justify-content-center">
			<div class="col-5">
				<table class="table text-center table-hover table-dark">
					<thead>
						<tr>
							<th scope="col">Usuario</th>
							<th scope="col">Torneos Ganados</th>

						</tr>
					</thead>

					<c:forEach end="4" items="${usuarios}" var="U">

						<tbody>
							<tr>
								<td>${U.nombre}</td>
								<td>${U.torGanados}</td>

							</tr>

						</tbody>

					</c:forEach>
				</table>
			</div>
		</div>
	</c:if>

	<!--Sheet Slider-->
	<div class="sheetSlider sh--default">
		<input id="s1" type="radio" name="slide1" checked /> <input id="s2"
			type="radio" name="slide1" /> <input id="s3" type="radio"
			name="slide1" />
		<div class="sh__content">

			<!-- Slider Item -->
			<div class="sh__item">
				<img src="img/imagen1.jpg" alt="imgText" width="100%" height="100%">
				<!-- Item Info -->
				<div class="sh__meta">
					<h4>Prepará a tu equipo</h4>
					<span>Buscá tu lugar para entrenar <a href="ver-inmuebles"
						id="verInmuebles"> acá </a></span>
				</div>
			</div>

			<!-- Slider Item -->
			<div class="sh__item">
				<img src="img/imagen2.jpg" alt="imgText" width="100%" height="100%">
				<!-- Item Info -->
				<div class="sh__meta">
					<h4>Organizá torneos</h4>
					<span>Organizalo de la manera que quieras <a
						href="ver-formulario-torneo" id="organizarTorneos"> acá </a></span>
				</div>
			</div>

			<!-- Slider Item -->
			<div class="sh__item">
				<img src="img/imagen3.jpg" alt="imgText" width="100%" height="100%">
				<!-- Item Info -->
				<div class="sh__meta">
					<h4>Participá en un torneo profesional</h4>
					<span>Elegí el torneo del juego que más te guste <a
						href="ver-torneos" id="verTorneos"> acá </a></span>
				</div>
			</div>

		</div>
		<!-- .sh__content -->

		<!--botones-->
		<div class="sh__btns">
			<label for="s1"></label> <label for="s2"></label> <label for="s3"></label>
		</div>
		<!-- .sh__btns -->

		<!--flechas-->
		<div class="sh__arrows">
			<label for="s1"></label> <label for="s2"></label> <label for="s3"></label>
		</div>
		<!-- .sh__arrows -->

	</div>
	<!-- .sheetSlider -->

	<footer>
		<div class="contenedorFooter">


			<i class="fab fa-youtube"></i> <i class="fab fa-instagram"> </i> <i
				class="fab fa-whatsapp"></i>
		</div>
	</footer>

</body>
<style>
.border-info {
	border-color: #f71df6 !important;
}
</style>
</html>