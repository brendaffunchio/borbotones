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

<h1>Torneos</h1>


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


<a href="registrar-participante"> Participar </a>
</div>

</c:forEach>


</div>



</body>
</html>