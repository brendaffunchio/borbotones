
		<%@include file="header.jsp"%>
		
	<div class="contenedor-publicar">
		<div class="titulo-pub">
			<h2 class="titulo-publicar">PUBLICÁ TU INMUEBLE</h2>
		</div>
		<div class="text-center">
		<c:if test="${not empty errorDireccion}">
				<p>
					<span>${errorDireccion}</span>
				</p>
				<br>
			</c:if>
			</div>
		<div class="formulario-publicar">

			<form:form action="crear-inmueble" method="POST" enctype="multipart/form-data"
				modelAttribute="inmueble">
				
				<p class="datos">DATOS DEL INMUEBLE</p>
				</br>
				<label class="or">Nombre del inmueble</label>
				</br>
				<form:input class="f" path="nombre" type="text" required="required"/>
				</br>
				

				<label class="or">Provincia</label>
				<br>
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
				<label for="precio"class="or">Valor del inmueble</label>
				</br>
				<form:input name="precio" class="f" path="precio" type="number" required="required"/>
				</br>

				<label class="or">Imagen del inmueble</label>
				<input type="file" name="file"/> 
				
				<br>
				<button class="btn-pub" Type="Submit" />PUBLICAR</button>

			</form:form>
		 
		</div>
	</div>

</body>
</html>