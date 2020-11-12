create database db;

use db;

/*drop database db;*/

insert into usuario (apellido, email, nombre, password, rol)
values ("Inmobiliaria Gaming", 
"gamehouse@borbotones.com",
"Los Borbotones",
"borbotones",
"admin");

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
("Luján","B6700",1);

insert into direccion(calle, numero, ciudad_id)
values ("Libertad",325,1),
("Rivadavia",1356,1),
("Machado",728,2),
("Mendoza",1850,2),
("Balcarce",852,3),
("Necochea",1605,3),
("Los Claveles",963,4),
("Los Lotos",2560,4);

 select*from inmueble;
select*from torneo;
select*from usuario;
select*from participa;
select*from direccion;
select*from ciudad;
select*from provincia;
