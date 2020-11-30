
		<%@include file="header.jsp"%>
		
<div class="col-md-12 text-center">
<h1>ELEGÍR GANADOR DEL TORNEO</h1>
<form:form action="elegirGanador" method="POST">

	<div class="form-group col-md-12 text-center">
	 
	<input type="hidden" name="torneoGanadoId" value="${torneoId}">
	
	<select name="ganadorId">

	<c:forEach items="${participantes}" var="P">

	<option value="${P.id}">${P.nombre}</option>

	</c:forEach>

	</select>
	
	<button class="btn btn-primary" type="submit">ELEGIR GANADOR</button>
	
	</div>
	
	</div>
</form:form>



</body>
</html>