<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form action="agregar-participante" modelAttribute="participante" method="POST">

 				<form:input path="nombre" type="text" placeholder="Nombre"/>
			    
			    <form:input path="apellido" type="text" placeholder="Apellido"/>
			    
			    <form:input path="dni" type="number" placeholder="DNI"/>
			    
					    
				<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Participar</button>


</form:form>

</body>
</html>