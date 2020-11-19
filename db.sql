create database db;

use db;


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

insert into direccion(calle, numero, latitud, longitud, ciudad_id )
values ("Libertad",325,-35.056713, -58.757346,1),
("Rivadavia",1356,-35.0497958,-58.7654189,1),
("Machado",728,-34.646546,-58.6211149,2),
("Mendoza",1850,-34.6454243,-58.6247797,2),
("Balcarce",852,-35.186508,-59.0886316,3),
("Necochea",1605,-35.1872029,-59.0782694,3),
("Los Claveles",963,-34.5470022,-59.1295741,4),
("Los Lotos",2560,-34.5425304,-59.1153651,4);

 select*from inmueble;
select*from torneo;
select*from usuario;
select*from participa;
select*from direccion;
select*from ciudad;
select*from provincia;
