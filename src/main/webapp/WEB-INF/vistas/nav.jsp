<nav class="contenedorNav">

			<a href="inicio" id="btnHome"> HOME </a> <a href="ver-inmuebles"
				id="btnInmueble"> INMUEBLES </a> <a href="ver-torneos"
				id="btnTorneo"> TORNEOS </a> <a href="contacto" id="btnInmueble">
				CONTACTO </a>

			<c:if
				test="${(sessionScope.rol=='admin')or(sessionScope.rol=='invitado')}">
				<a href="ver-perfil-del-usuario?usuarioId=${sessionScope.id}" id="btnTorneo">
					PERFIL </a>
				<a href="logout" id="btnContacto"> CERRAR SESIÓN </a>
			</c:if>


		</nav>