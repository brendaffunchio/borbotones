<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/sheetslider.min.css"/>
<link rel="stylesheet" href="css/estilo-inicio.css"/>
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600&display=swap" rel="stylesheet"> 

<title>Inmobiliaria GAMING HOUSE</title>
</head>
<body>

<header>



<h2> Inmobiliaria GAMING HOUSE  </h2>

<nav class="contenedorNav"> <a href="inicio"> HOME </a> 

<a href="#"> INMUEBLES </a> 

<a href="#"> TORNEOS </a> 

<a href="#"> CONTACTO </a> 

 </nav>

 </header>
 
 
 	<div class="contenedorDescripcion">
 	
 	<p>  Una nube es un hidrometeoro que consiste en una masa visible formada por cristales de nieve o gotas de agua microscópicas suspendidas en la atmósfera. 
 	Las nubes dispersan toda la luz visible y por eso se ven blancas. 
 	Sin embargo, a veces son demasiado gruesas o densas como para que la luz las atraviese, cuando esto ocurre la coloración se torna gris o incluso negra. 
 	Considerando que las nubes son gotas de agua sobre polvo atmosférico y dependiendo de algunos factores las gotas pueden convertirse en lluvia, granizo o nieve. 
 	Las nubes son un aerosol formado por agua evaporada principalmente de los océanos.   </p>
 	
 	 </div>
 	 
 	

<!--Sheet Slider-->
<div class="sheetSlider sh--default">
   <input id="s1" type="radio" name="slide1" checked/> 
   <input id="s2" type="radio" name="slide1"/> 
   <input id="s3" type="radio" name="slide1"/>
   <div class="sh__content">
 
      <!-- Slider Item -->
      <div class="sh__item">
         <img src="img/imagen1.jpg" alt="imgText" width="100%" height="100%">
         <!-- Item Info -->
         <div class="sh__meta">
            <h4>Prepara a tu equipo</h4>
            <span>Busca tu lugar para entrenar <a href="ver-inmuebles"> acá </a></span>
         </div>
      </div>
 
      <!-- Slider Item -->
      <div class="sh__item">
         <img src="img/imagen2.jpg" alt="imgText" width="100%" height="100%">
         <!-- Item Info -->
         <div class="sh__meta">
            <h4>Organizá torneos</h4>
            <span>Organizalo de la manera que quieras <a href="ver-formulario-torneo"> acá </a></span>
         </div>
      </div>
 
      <!-- Slider Item -->
      <div class="sh__item">
         <img src="img/imagen3.jpg" alt="imgText" width="100%" height="100%">
         <!-- Item Info -->
         <div class="sh__meta">
            <h4>Participá en un torneo profesional</h4>
            <span>Elegí el torneo del juego que más te guste <a href="ver-torneos"> acá </a></span>
         </div>
      </div>
 
   </div><!-- .sh__content -->
 
   <!--botones-->
   <div class="sh__btns">
      <label for="s1"></label>
      <label for="s2"></label>
      <label for="s3"></label>
   </div><!-- .sh__btns -->
 
   <!--flechas-->
   <div class="sh__arrows">
      <label for="s1"></label>
      <label for="s2"></label>
      <label for="s3"></label>
   </div><!-- .sh__arrows -->
   
</div><!-- .sheetSlider -->

</body>
</html>