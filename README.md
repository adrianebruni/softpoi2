# softpoi2
Configurar la clave de MySQL en la variable MySQL_PASS que se encuentra en la clase Parametros del paquete dds.softpoi (por defecto, es "")
Ejecutar estas lineas en MySQL para crear el esquema DDS y sus respectivas tablas

CREATE DATABASE DDS;

CREATE TABLE `DDS`.`POI` (
  IDPOI INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NOMBRE VARCHAR(45) NULL,
  LONGITUD FLOAT NULL,
  LATITUD FLOAT NULL,
  TIPOPOI VARCHAR(45) NULL);

CREATE TABLE `DDS`.`ADMINISTRADOR` (
  id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(45) NULL,
  clave VARCHAR(45) NULL,
  email VARCHAR(80) NULL,
  flagAuditoriaBusqueda TINYINT(1) NULL,
  flagNotificaciones TINYINT(1) NULL);

CREATE TABLE `DDS`.`TERMINAL` (
  id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(45) NULL,
  clave VARCHAR(45) NULL,
  longitud FLOAT NULL,
  latitud FLOAT NULL,
  flagAuditoriaBusqueda TINYINT(1) NULL,
  flagNotificaciones TINYINT(1) NULL);

INSERT INTO DDS.ADMINISTRADOR
VALUES (1,"root","root","root@softpoi.com.ar",1,0);
