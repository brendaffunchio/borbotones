
		<%@include file="header.jsp"%>
		
	<div class="row justify-content-center">
	<div class="col-md-3 text-center">

	
			<h2>PARTICIPANTES</h2>
			
			<table class="table table-hover table-dark">
			
				<c:forEach items="${participantes}" var="P">

					<tbody>
						<tr>
							<td>${P.nombre}</td>
						</tr>
</tbody>

</c:forEach>
</table>

</div>
</div>


</body>
</html>