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
select*from torneo;
select*from usuario;
select*from participa;
select*from direccion;
select*from ciudad;
select*from provincia;

update Usuario set direccion_id = 4  where id = 3;

update Torneo set cupo = 2 where id = 1;
update Torneo set estadoCompleto = 0 where id = 1;


update Inmueble set disponible =  false where id = 1;

update Torneo set foto = "840_560.jpeg" where id = 1;

drop database db;