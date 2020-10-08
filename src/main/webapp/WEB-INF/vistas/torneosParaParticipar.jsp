<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="css/estilos.css">
<title>Torneos</title>
</head>
<body>

<h1>Torneos</h1>

<form:form action="" method="POST" modelAttribute="torneo">
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

<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>PARTICIPAR</button>

</div>

</c:forEach>
</div>

</form:form>

</body>
</html>