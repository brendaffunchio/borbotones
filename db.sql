create database db;
use db;

insert into provincia (nombre) 
values ("Buenos Aires"),
("Catamarca"),
("Chaco"),
("Chubut"),
("Córdoba"),
("Corrientes"),
("Entre Ríos"),
("Formosa"),
("Jujuy"),
("La Pampa"),
("La Rioja"),
("Mendoza"),
("Misiones"),
("Neuquén"),
("Río Negro"),
("Salta"),
("San Juan"),
("San Luis"),
("Santa Cruz"),
("Santa Fe"),
("Santiago del Estero"),
("Tierra del Fuego"),
("Tucumán");

insert into ciudad(nombre, codigoPostal,provincia_id)
values("Cañuelas","B1814",1);
insert into ciudad(nombre, codigoPostal,provincia_id)
values("Lobos","B7240",1);
insert into ciudad(nombre, codigoPostal,provincia_id)
values("Luján","B6700",1);
insert into ciudad(nombre, codigoPostal,provincia_id)
values("Moron","B1708",1);
insert into ciudad(nombre, codigoPostal,provincia_id)
values("CABA","C1414",1);

insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values ("Libertad",325,0.6119,1.0255,1),
("Rivadavia",1356,0.6117,1.0256,1),
("Moreno",430,0.6119,1.0256,1),
("Juarez",530,0.6119,1.0256,1),
("Mitre",1530,0.6117,1.0257,1),
("Ameghino",645,0.6119,1.0255,1);

insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values("San Martin",168,0.6141,1.0315,2),
("Patagones",8,0.6142,1.0314,2);
insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values("Los Claveles",963,0.6030,1.0320,3),
("Los Lotos",2560,0.6029,1.0317,3);
insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values("Machado",730,0.6047,1.0231,4),
("Larralde",1341,0.5742,1.2015,4);
insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values ("Avenida Córdoba",4266,0.6038,1.0197,5);

insert into usuario (apellido, email, nombre, password, rol,torGanados, direccion_id)
values ("Inmobiliaria Gaming", 
"gamehouse@borbotones.com",
"Los Borbotones",
"borbotones",
"admin",
0, 1);

select*from inmueble;
select * from torneo;
select*from usuario;
select*from participa;
select*from direccion;
select*from ciudad;
select*from provincia;

insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,2,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,3,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,4,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,5,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,6,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,7,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,8,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,9,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,10,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,11,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,12,null);
insert into inmueble(disponible,foto,nombre,precio,direccion_id,inquilino_id)
values(1,null,"fiwek",55252,13,null);

drop database db;