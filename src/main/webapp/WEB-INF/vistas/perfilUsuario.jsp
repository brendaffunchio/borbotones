
<%@include file="header.jsp"%>

<div class="container">

	<div class="row">
	
		<div class="col">
			<div class="card bg-dark" style="width: 40rem;">
				<img src="img/paneldecontrol.jpg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title text-white">Panel De Control</h5>
					<p class="card-text text-white">En el panel tendrás los
						inmuebles que tienes alquilados, los torneos que creaste y los que
						estás participando.</p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item item1"><a
						href="/proyecto-practica/ver-inmuebles-alquilados?usuarioId=${sessionScope.id}"
						role="button">INMUEBLES ALQUILADOS</a></li>
					<li class="list-group-item item2"><a
						href="/proyecto-practica/ver-torneos-que-participo?usuarioId=${sessionScope.id}"
						role="button">TORNEOS QUE PARTICIPO </a></li>
					<li class="list-group-item item3"><a
						href="/proyecto-practica/ver-torneos-que-cree?usuarioId=${sessionScope.id}"
						role="button">TORNEOS ORGANIZADOS</a></li>
				</ul>


			</div>

</div>

		</div>


	
</div>

<style>



.container{
margin: 0px auto;
width: 55%;

}

.item1{

background: #ecff16;
}

.item2{

background: #00f000;
}

.item3{

background: #2BE4FF;
}

a{
	color: black;
}

a:hover{
	color: blue;
	text-decoration: none;
}

</style>

</body>
</html>

