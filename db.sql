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
values ("Cañuelas","B1814",1),
("Moron", "B1708",1),
("Lobos", "B7240",1),
("Luján","B6700",1),
("CABA","C1414",1);

insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values ("Libertad",325,0.6119,1.0255,1),
("Rivadavia",1356,0.6117,1.0256,1),
("Machado",728,0.6047,1.0231,2),
("Mendoza",1850,0.5742,1.2015,2),
("San Martin",168,0.6141,1.0315,3),
("Patagones",8,0.6142,1.0314,3),
("Los Claveles",963,0.6030,1.0320,4),
("Los Lotos",2560,0.6029,1.0317,4),
("Avenida Córdoba",4266,0.6038,1.0197,5);

insert into usuario (apellido, email, nombre, password, rol,torGanados, direccion_id)
values ("Inmobiliaria Gaming", 
"gamehouse@borbotones.com",
"Los Borbotones",
"borbotones",
"admin",
0, 9);

select*from inmueble;
select*from torneo;
select*from usuario;
select*from participa;
select*from direccion;
select*from ciudad;
select*from provincia;

drop database db;