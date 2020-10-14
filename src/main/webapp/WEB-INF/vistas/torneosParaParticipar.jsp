<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="css/estilos.css">
<title>Torneos</title>
</head>
<body>

<h1>Lista de Torneos</h1>


<<<<<<< HEAD

=======
>>>>>>> 10cca270ec755af1d939767dedaae30647d8455d
<div class="torneos">
<c:forEach items="${torneos}" var="T">


<div class="tor">
<h2>${T.nombre}</h2>
</br>
<p>Fecha: ${T.fecha}</p>
</br>
<p>Horario: ${T.horario}</p>
</br>
<p>Precio: ${T.precio}</p>
</br>

<<<<<<< HEAD
=======
<a class="btn btn-lg btn-primary btn-block" href="/proyecto-practica/" role="button">Participar</a>
</br>

>>>>>>> 10cca270ec755af1d939767dedaae30647d8455d

<a href="registrar-participante"> Participar </a>
</div>

</c:forEach>


</div>



</body>
</html>