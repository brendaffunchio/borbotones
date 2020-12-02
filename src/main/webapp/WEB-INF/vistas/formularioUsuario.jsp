
		<%@include file="header.jsp"%>
		
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">REGISTRATE</h2>
		</div>
		<div class ="text-center">
		 <c:if test="${not empty errorDireccion}">
				<p>
					<span>${errorDireccion}</span>
				</p>
				<br>
			</c:if>
			</div>
		<div class="formulario-publicar">
			<form:form action="crear-usuario" method="POST"
				modelAttribute="usuario">
				<p class="datos">DATOS DEL USUARIO</p>
				</br>
				<label class="or">Nombre</label>
				</br>
				<form:input class="f" path="nombre" type="text" required="required"/>
				</br>
				<label class="or">Apellido</label>
				</br>
				<form:input class="f" path="apellido" type="text" required="required"/>
				</br>
				
				<label class="or">Provincia</label>
				
				<select>
				
				<c:forEach items="${provincias}" var="P">
				
               <option value="provincia">${P.nombre}</option>
              
              </c:forEach>
         	  </select>
         	  
				</br>
				
				<label for="nombreCiudad" class="or" for="ciudades">Ciudad</label>
				<br>
				<select>
				<c:forEach items="${ciudades}" var="C">

				<option name="nombreCiudad" value="ciudad"> ${C.nombre} </option>
				
				</c:forEach>
				</select>
				</br>
				<label class="or">Direccion</label>
				</br>
				<input name="calle" class="f"  type="text" placeholder="calle" required/>
				</br>
				<input name="numero" class="f" type="number" placeholder="numero" required/>
				<br>
				<label class="or">Email</label>
				</br>
				<form:input class="f" path="email" type="email" required="required" />
				</br>
				<label class="or">Password</label>
				</br>
				<form:input class="f" path="password" type="password" required="required"/>
				</br>
				
				<button class="btn-pub" Type="Submit" />REGISTRARSE</button>

			</form:form>

           
			
		</div>
	</div>

</body>
</html>