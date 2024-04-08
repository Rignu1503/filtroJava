CREATE DATABASE filtro;

USE filtro;

CREATE TABLE tienda(
	id_tienda INT(11) PRIMARY KEY auto_increment,
    nombre VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255) NOT NULL
);

CREATE TABLE producto(
	id_producto INT(11)  PRIMARY KEY auto_increment,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2),
    id_tienda INT(11),
    CONSTRAINT fk_id_tienda FOREIGN KEY(id_tienda) REFERENCES tienda(id_tienda) ON DELETE CASCADE
    
 );   
 
 CREATE TABLE cliente(
	id_cliente INT(11)  PRIMARY KEY auto_increment,
	nombre VARCHAR(255) NOT NULL,
	apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
 );
 
 
 CREATE TABLE compra(
	id_compra INT(11)  PRIMARY KEY auto_increment,
    
    id_cliente INT(11),
    CONSTRAINT fk_id_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente) ON DELETE CASCADE,
 
	id_producto INT(11),
    CONSTRAINT fk_id_producto FOREIGN KEY(id_producto) REFERENCES producto(id_producto) ON DELETE CASCADE,
    
    fecha_compra DATE,
    cantidad INT(11)
 );
 
ALTER TABLE producto
ADD stock INT NOT NULL;


  SELECT * FROM compra 
  INNER JOIN cliente ON cliente.id_cliente = compra.id_cliente
INNER JOIN producto ON producto.id_producto = compra.id_producto
INNER JOIN tienda ON tienda.id_tienda  = producto.id_tiendacompra
