<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2> Participantes del Torneo </h2>

<c:forEach  items= "${participantes}" var="PARTICIPANTE">

<p> Nombre: ${PARTICIPANTE.nombre} </p>

<br>

<p> Apellido: ${PARTICIPANTE.apellido} </p>

<br>

<p> DNI: ${PARTICIPANTE.dni} </p>

<br>


</c:forEach>


</body>
</html>

