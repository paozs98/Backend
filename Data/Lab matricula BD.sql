-- LABORATORIO MATRICULA

-- ESQUEMA
DROP DATABASE IF EXISTS `MATRICULA`;
CREATE DATABASE `MATRICULA`;
USE `MATRICULA`;

DROP TABLE IF EXISTS CARRERA;
CREATE TABLE CARRERA(
CODIGO_CARRERA VARCHAR(50),
NOMBRE VARCHAR(50),
TITULO VARCHAR(50),
CONSTRAINT CARRERA_PK PRIMARY KEY(CODIGO_CARRERA));

DROP TABLE IF EXISTS CURSO;
CREATE TABLE CURSO(
CODIGO_CURSO VARCHAR(50),
CODIGO_CARRERA VARCHAR(50),
NO_CICLO VARCHAR(50),
NOMBRE VARCHAR(50),
CREDITOS INT(11),
HORAS_SEMANALES INT(11),
CONSTRAINT CURSO_PK PRIMARY KEY(CODIGO_CURSO));

DROP TABLE IF EXISTS PROFESOR;
CREATE TABLE PROFESOR(
CEDULA_PROFESOR VARCHAR(50),
NOMBRE VARCHAR(50),
TELEFONO VARCHAR(50),
EMAIL VARCHAR(50),
CONSTRAINT PROFESOR_PK PRIMARY KEY(CEDULA_PROFESOR));

DROP TABLE IF EXISTS ALUMNO;
CREATE TABLE ALUMNO(
CEDULA_ALUMNO VARCHAR(50),
NOMBRE VARCHAR(50),
TELEFONO VARCHAR(50),
EMAIL VARCHAR(50),
FECHA_NACIMIENTO DATE,
CARRERA VARCHAR(50),
CONSTRAINT ALUMNO_PK PRIMARY KEY(CEDULA_ALUMNO));

DROP TABLE IF EXISTS CICLO;
CREATE TABLE CICLO(
NO_CICLO VARCHAR(50),
ANIO VARCHAR(4),
NUMERO VARCHAR(1),
FECHA_INICIO DATE,
FECHA_FIN DATE,
CONSTRAINT CICLO_PK PRIMARY KEY(NO_CICLO));

DROP TABLE IF EXISTS GRUPO;
CREATE TABLE GRUPO(
NUMERO_GRUPO VARCHAR(50),
NO_CICLO VARCHAR(50),
CODIGO_CURSO VARCHAR(50),
CEDULA_PROFESOR VARCHAR(50),
HORARIO VARCHAR(50),
CONSTRAINT GRUPO_PK PRIMARY KEY(NUMERO_GRUPO));

DROP TABLE IF EXISTS USUARIOS;
CREATE TABLE USUARIOS(
CEDULA VARCHAR(50),
CLAVE VARCHAR(50),
ROL VARCHAR(50),
CONSTRAINT USUARIOS_PK PRIMARY KEY(CEDULA));

DROP TABLE IF EXISTS MATRICULAS;
CREATE TABLE MATRICULAS(
NO_MATRICULA VARCHAR(50),
CEDULA_ALUMNO VARCHAR(50),
NUMERO_GRUPO VARCHAR(50),
NOTA FLOAT,
CONSTRAINT MATRICULAS_PK PRIMARY KEY(NO_MATRICULA));

--------------------------------------------------------------------------------------------------------------------

-- LLAVES FORÁNEAS

-- TABLA CURSO
ALTER TABLE CURSO ADD CONSTRAINT FK_CURSO_CARRERA
FOREIGN KEY(CODIGO_CARRERA)
REFERENCES CARRERA(CODIGO_CARRERA);

ALTER TABLE CURSO ADD CONSTRAINT FK_CURSO_CICLO
FOREIGN KEY(NO_CICLO)
REFERENCES CICLO(NO_CICLO);

-- TABLA GRUPO
ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_CURSO
FOREIGN KEY(CODIGO_CURSO)
REFERENCES CURSO(CODIGO_CURSO);

ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_CICLO
FOREIGN KEY(NO_CICLO)
REFERENCES CICLO(NO_CICLO);

ALTER TABLE GRUPO ADD CONSTRAINT FK_GRUPO_PROFESOR
FOREIGN KEY(CEDULA_PROFESOR)
REFERENCES PROFESOR(CEDULA_PROFESOR);

-- TABLA USUARIOS
ALTER TABLE USUARIOS ADD CONSTRAINT FK_USUARIOS_PROFESOR
FOREIGN KEY(CEDULA)
REFERENCES PROFESOR(CEDULA_PROFESOR);

-- TABLA MATRICULAS
ALTER TABLE MATRICULAS ADD CONSTRAINT FK_MATRICULAS_ALUMNO
FOREIGN KEY(CEDULA_ALUMNO)
REFERENCES ALUMNO(CEDULA_ALUMNO);

ALTER TABLE MATRICULAS ADD CONSTRAINT FK_MATRICULAS_GRUPO
FOREIGN KEY(NUMERO_GRUPO)
REFERENCES GRUPO(NUMERO_GRUPO);

-- TABLA CARRERA--------------------------------------------------------------------------------------------------------------------

-- INSERTAR
DROP PROCEDURE IF EXISTS INSERTAR_CARRERA;
DELIMITER //
CREATE PROCEDURE INSERTAR_CARRERA(IN CODIGO_CARRERA VARCHAR(50), IN NOMBRE VARCHAR(50), IN TITULO VARCHAR(50))
	BEGIN
		INSERT INTO CARRERA VALUES(CODIGO_CARRERA,NOMBRE,TITULO);
	END //
DELIMITER ;

-- MODIFICAR
DROP PROCEDURE IF EXISTS MODIFICAR_CARRERA;
DELIMITER //
CREATE PROCEDURE MODIFICAR_CARRERA(IN P_CODIGO_CARRERA VARCHAR(50), IN P_NOMBRE VARCHAR(50), IN P_TITULO VARCHAR(50))
	BEGIN
		UPDATE CARRERA SET NOMBRE = P_NOMBRE, TITULO = P_TITULO  WHERE CODIGO_CARRERA = P_CODIGO_CARRERA;
	END
	//
DELIMITER ;

-- BUSCAR (UN ELEMENTO)
DROP PROCEDURE IF EXISTS BUSCAR_CARRERA;
DELIMITER //
	CREATE PROCEDURE BUSCAR_CARRERA(IN P_CODIGO_CARRERA VARCHAR(50))
	BEGIN 
		SELECT CODIGO_CARRERA,NOMBRE,TITULO FROM CARRERA WHERE CODIGO_CARRERA = P_CODIGO_CARRERA; 
	END
//
DELIMITER ;

-- LISTAR (TODOS LOS ELEMENTOS)
DROP PROCEDURE IF EXISTS LISTAR_CARRERA
DELIMITER //
CREATE PROCEDURE LISTAR_CARRERA()
	BEGIN 
		SELECT CODIGO_CARRERA,NOMBRE,TITULO FROM CARRERA; 
	END;
//
DELIMITER ;

-- BORRAR
DROP PROCEDURE IF EXISTS ELIMINAR_CARRERA;
DELIMITER //
CREATE PROCEDURE ELIMINAR_CARRERA(IN P_CODIGO_CARRERA VARCHAR(50))
	BEGIN
		DELETE FROM CARRERA WHERE CODIGO_CARRERA = P_CODIGO_CARRERA;
	END
//
DELIMITER ;

--  TABLA USUARIOS--------------------------------------------------------------------------------------------------------------------

-- INSERTAR
DROP PROCEDURE IF EXISTS INSERTAR_USUARIO;
DELIMITER //
CREATE PROCEDURE INSERTAR_USUARIO(IN CEDULA VARCHAR(50), IN CLAVE VARCHAR(50), IN ROL VARCHAR(50))
	BEGIN
		INSERT INTO USUARIOS VALUES(CEDULA, CLAVE, ROL);
	END //
DELIMITER ;

-- BUSCAR (UN ELEMENTO)
DROP PROCEDURE IF EXISTS BUSCAR_USUARIO;
DELIMITER //
	CREATE PROCEDURE BUSCAR_USUARIO(IN P_CEDULA VARCHAR(50), IN P_CLAVE VARCHAR(50))
	BEGIN 
		SELECT CEDULA, CLAVE, ROL FROM USUARIOS WHERE CEDULA = P_CEDULA AND CLAVE = P_CLAVE; 
	END
//
DELIMITER ;

--  TABLA CURSO--------------------------------------------------------------------------------------------------------------------

-- INSERTAR
DROP PROCEDURE IF EXISTS INSERTAR_CURSO;
DELIMITER //
CREATE PROCEDURE INSERTAR_CURSO(IN CODIGO_CURSO VARCHAR(50), IN CODIGO_CARRERA VARCHAR(50), IN NO_CICLO VARCHAR(50), IN NOMBRE VARCHAR(50), IN CREDITOS INT, IN HORAS_SEMANALES INT)
	BEGIN
		INSERT INTO CURSO VALUES(CODIGO_CURSO, CODIGO_CARRERA, NO_CICLO, NOMBRE, CREDITOS, HORAS_SEMANALES);
	END //
DELIMITER ;

-- MODIFICAR
DROP PROCEDURE IF EXISTS MODIFICAR_CURSO;
DELIMITER //
CREATE PROCEDURE MODIFICAR_CURSO(IN P_CODIGO_CURSO VARCHAR(50), IN P_CODIGO_CARRERA VARCHAR(50), IN P_NO_CICLO VARCHAR(50), IN P_NOMBRE VARCHAR(50), IN P_CREDITOS INT, IN P_HORAS_SEMANALES INT)
	BEGIN
		UPDATE CARRERA SET CODIGO_CARRERA = P_CODIGO_CARRERA, NO_CICLO = P_NO_CICLO, NOMBRE = P_NOMBRE, CREDITOS = P_CREDITOS, HORAS_SEMANALES = P_HORAS_SEMANALES WHERE CODIGO_CURSO = P_CODIGO_CURSO;
	END
	//
DELIMITER ;

-- BUSCAR (UN ELEMENTO)
DROP PROCEDURE IF EXISTS BUSCAR_CURSO;
DELIMITER //
	CREATE PROCEDURE BUSCAR_CURSO(IN P_CODIGO_CURSO VARCHAR(50))
	BEGIN 
		SELECT CODIGO_CURSO,CODIGO_CARRERA,NO_CICLO,NOMBRE,CREDITOS,HORAS_SEMANALES FROM CURSO WHERE CODIGO_CURSO = P_CODIGO_CURSO; 
	END
//
DELIMITER ;

-- LISTAR (TODOS LOS ELEMENTOS)
DROP PROCEDURE IF EXISTS LISTAR_CURSO
DELIMITER //
CREATE PROCEDURE LISTAR_CURSO()
	BEGIN 
		SELECT CODIGO_CURSO, CODIGO_CARRERA, NO_CICLO,NOMBRE,CREDITOS, HORAS_SEMANALES FROM CURSO; 
	END;
//
DELIMITER ;

-- BORRAR
DROP PROCEDURE IF EXISTS ELIMINAR_CURSO;
DELIMITER //
CREATE PROCEDURE ELIMINAR_CURSO(IN P_CODIGO_CURSO VARCHAR(50))
	BEGIN
		DELETE FROM CURSO WHERE CODIGO_CURSO = P_CODIGO_CURSO;
	END
//
DELIMITER ;

-- TABLA PROFESOR--------------------------------------------------------------------------------------------------------------------

-- INSERTAR
DROP PROCEDURE IF EXISTS INSERTAR_PROFESOR;
DELIMITER //
CREATE PROCEDURE INSERTAR_PROFESOR(IN CEDULA_PROFESOR VARCHAR(50), IN NOMBRE VARCHAR(50), IN TELEFONO VARCHAR(50), IN EMAIL VARCHAR(50))
	BEGIN
		INSERT INTO PROFESOR VALUES(CEDULA_PROFESOR,NOMBRE,TELEFONO,EMAIL);
	END //
DELIMITER ;

-- MODIFICAR
DROP PROCEDURE IF EXISTS MODIFICAR_PROFESOR;
DELIMITER //
CREATE PROCEDURE MODIFICAR_PROFESOR(IN P_CEDULA_PROFESOR VARCHAR(50), IN P_NOMBRE VARCHAR(50), IN P_TELEFONO VARCHAR(50), IN P_EMAIL VARCHAR(50))
	BEGIN
		UPDATE PROFESOR SET NOMBRE = P_NOMBRE, TELEFONO = P_TELEFONO, EMAIL = p_EMAIL  WHERE CEDULA_PROFESOR = P_CEDULA_PROFESOR;
	END
	//
DELIMITER ;

-- BUSCAR (UN ELEMENTO)
DROP PROCEDURE IF EXISTS BUSCAR_PROFESOR;
DELIMITER //
	CREATE PROCEDURE BUSCAR_PROFESOR(IN P_CEDULA_PROFESOR VARCHAR(50))
	BEGIN 
		SELECT CEDULA_PROFESOR,NOMBRE,TELEFONO,EMAIL FROM PROFESOR WHERE CEDULA_PROFESOR = P_CEDULA_PROFESOR; 
	END
//
DELIMITER ;

-- LISTAR (TODOS LOS ELEMENTOS)
DROP PROCEDURE IF EXISTS LISTAR_PROFESOR
DELIMITER //
CREATE PROCEDURE LISTAR_PROFESOR()
	BEGIN 
		SELECT CEDULA_PROFESOR,NOMBRE,TELEFONO,EMAILO FROM PROFESOR; 
	END;
//
DELIMITER ;

-- BORRAR
DROP PROCEDURE IF EXISTS ELIMINAR_PROFESOR;
DELIMITER //
CREATE PROCEDURE ELIMINAR_PROFESOR(IN P_CEDULA_PROFESOR VARCHAR(50))
	BEGIN
		DELETE FROM PROFESOR WHERE CEDULA_PROFESOR = P_CEDULA_PROFESOR;
	END
//
DELIMITER ;

-- LISTA CURSOS DEL PROFESOR
DROP PROCEDURE IF EXISTS LISTAR_GRUPO_PROFESOR;
DELIMITER //
CREATE PROCEDURE LISTAR_GRUPO_PROFESOR(IN P_CEDULA_PROFESOR VARCHAR(50))
	BEGIN
		SELECT * FROM GRUPO WHERE CEDULA_PROFESOR = P_CEDULA_PROFESOR;
	END //
DELIMITER ;	

-- TABLA ALUMNO--------------------------------------------------------------------------------------------------------------------
-- INSERTAR
DROP PROCEDURE IF EXISTS INSERTAR_ALUMNO;
DELIMITER //
CREATE PROCEDURE INSERTAR_ALUMNO(IN CEDULA_ALUMNO VARCHAR(50), IN NOMBRE VARCHAR(50), IN TELEFONO VARCHAR(50), IN EMAIL VARCHAR(50), IN FECHA_NACIMIENTO DATE, IN CARRERA VARCHAR(50))
	BEGIN
		INSERT INTO ALUMNO VALUES(CEDULA_ALUMNO,NOMBRE,TELEFONO,EMAIL,FECHA_NACIMIENTO, CARRERA);
	END //
DELIMITER ;

-- MODIFICAR
DROP PROCEDURE IF EXISTS MODIFICAR_ALUMNO;
DELIMITER //
CREATE PROCEDURE MODIFICAR_ALUMNO(IN P_CEDULA_ALUMNO VARCHAR(50), IN P_NOMBRE VARCHAR(50), IN P_TELEFONO VARCHAR(50), IN P_EMAIL VARCHAR(50), IN P_FECHA_NACIMIENTO DATE, IN P_CARRERA VARCHAR(50))
	BEGIN
		UPDATE ALUMNO SET NOMBRE = P_NOMBRE, TELEFONO = P_TELEFONO, EMAIL = P_EMAIL, FECHA_NACIMIENTO = P_FECHA_NACIMIENTO, CARRERA = P_CARRERA  WHERE CEDULA_ALUMNO = P_CEDULA_ALUMNO;
	END
	//
DELIMITER ;

-- BUSCAR (UN ELEMENTO)
DROP PROCEDURE IF EXISTS BUSCAR_ALUMNO;
DELIMITER //
	CREATE PROCEDURE BUSCAR_ALUMNO(IN P_CEDULA_ALUMNO VARCHAR(50))
	BEGIN 
		SELECT CEDULA_ALUMNO,NOMBRE,TELEFONO,EMAIL,FECHA_NACIMIENTO,CARRERA FROM ALUMNO WHERE CEDULA_ALUMNO = P_CEDULA_ALUMNO; 
	END
//
DELIMITER ;

-- LISTAR (TODOS LOS ELEMENTOS)
DROP PROCEDURE IF EXISTS LISTAR_ALUMNO
DELIMITER //
CREATE PROCEDURE LISTAR_ALUMNO()
	BEGIN 
		SELECT CEDULA_ALUMNO,NOMBRE,TELEFONO,EMAIL, FECHA_NACIMIENTO, CARRERA FROM ALUMNO; 
	END;
//
DELIMITER ;

-- BORRAR
DROP PROCEDURE IF EXISTS ELIMINAR_ALUMNO;
DELIMITER //
CREATE PROCEDURE ELIMINAR_ALUMNO(IN P_CEDULA_ALUMNO VARCHAR(50))
	BEGIN
		DELETE FROM ALUMNO WHERE CEDULA_ALUMNO = P_CEDULA_ALUMNO;
	END
//
DELIMITER ;

-- TABLA CICLO--------------------------------------------------------------------------------------------------------------------

-- LISTAR (TODOS LOS ELEMENTOS)
DROP PROCEDURE IF EXISTS LISTAR_CICLO
DELIMITER //
CREATE PROCEDURE LISTAR_CICLO()
	BEGIN 
		SELECT NO_CICLO,ANIO,NUMERO,FECHA_INICIO, FECHA_FIN FROM CICLO; 
	END;
//
DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------

-- TABLA MATRÍCULA--------------------------------------------------------------------------------------------------------------------

-- LISTAR ALUMNOS SEGUN GRUPO
DROP PROCEDURE IF EXISTS LISTAR_ALUMNOS_GRUPO
DELIMITER //
CREATE PROCEDURE LISTAR_ALUMNOS_GRUPO(IN P_ID_GRUPO VARCHAR(50))
	BEGIN 
		SELECT NO_MATRICULA, NOTA, CEDULA_ALUMNO from MATRICULAS where NUMERO_GRUPO = P_ID_GRUPO;
	END;
//
DELIMITER ;

-- MATRÍCULA

DROP PROCEDURE IF EXISTS REALIZA_MATRICULA;
DELIMITER //
CREATE PROCEDURE REALIZA_MATRICULA(IN NO_MATRICULA VARCHAR(50), IN CEDULA_ALUMNO VARCHAR(50), IN NUMERO_GRUPO VARCHAR(50), IN NOTA FLOAT)
	BEGIN
		INSERT INTO MATRICULAS VALUES(NO_MATRICULA,CEDULA_ALUMNO,NUMERO_GRUPO,NOTA);
	END //
DELIMITER ;

DROP PROCEDURE IF EXISTS REGISTRO_NOTA;
DELIMITER //
CREATE PROCEDURE REGISTRO_NOTA(IN P_NO_MATRICULA VARCHAR(50), IN P_NOTA FLOAT)
	BEGIN
		UPDATE MATRICULAS SET NOTA = P_NOTA WHERE NO_MATRICULA = P_NO_MATRICULA;
	END //
DELIMITER ;


-- ------------------------------------------------------------------------------------------------------------------

-- TABLA GRUPO--------------------------------------------------------------------------------------------------------------------

-- INSERTAR GRUPOS
DROP PROCEDURE IF EXISTS INSERTAR_GRUPO;
DELIMITER //
CREATE PROCEDURE INSERTAR_GRUPO(IN NUMERO_GRUPO VARCHAR(50), IN NO_CICLO VARCHAR(50), IN CODIGO_CURSO VARCHAR(50), IN CEDULA_PROFESOR VARCHAR(50), IN HORARIO VARCHAR(50))
	BEGIN
		INSERT INTO GRUPO VALUES(NUMERO_GRUPO,NO_CICLO,CODIGO_CURSO,CEDULA_PROFESOR, HORARIO);
	END //
DELIMITER ;

DROP PROCEDURE IF EXISTS BUSCAR_GRUPO;
DELIMITER //
	CREATE PROCEDURE BUSCAR_GRUPO(IN P_NUMERO_GRUPO VARCHAR(50))
	BEGIN 
		SELECT * FROM GRUPO WHERE NUMERO_GRUPO = P_NUMERO_GRUPO; 
	END
//
DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------

-- DATOS DE PRUEBA

INSERT INTO CICLO(NO_CICLO,ANIO,NUMERO,FECHA_INICIO,FECHA_FIN) VALUES('1','2019','1','2020-02-15','2020-06-15');

-- TABLA CARRERA
CALL INSERTAR_CARRERA('IN001','INGENIERIA DE SISTEMAS','DIPLOMADO');
CALL INSERTAR_CARRERA('BI001','BIOLOGIA','DIPLOMADO');

-- TABLA CURSO
CALL INSERTAR_CURSO('AD100','IN001','1','ADMINISTRACION DE BASE DE DATOS',4,8);
CALL INSERTAR_CURSO('BI100','BI001','1','BIOLOGIA I',4,8);

-- TABLA PROFESOR
CALL INSERTAR_PROFESOR('117772210','Alvaro Solis','88293785','alvaro.solis@gmail.com');
CALL INSERTAR_PROFESOR('217772210','Maria Rodriguez','88293786','maria.rodriguez@hotmail.com');

-- TABLA ALUMNO
CALL INSERTAR_ALUMNO('119994410','Victor Salazar','66293785','victor.salazar@gmail.com','1995-12-15','IN001');
CALL INSERTAR_ALUMNO('219994410','Catalina Sanchez','66293786','catalina.sanchez@gmail.com','1998-04-10','BI001');
CALL INSERTAR_ALUMNO('117050890','Ruben Pereira','87578204','ruben.pereira@hotmail.com','1998-04-07','IN001');
CALL INSERTAR_ALUMNO('112233445','Paola Zuniga','85445252','paola.zuniga@hotmail.com','1996-04-07','BI001');

-- TABLA USUARIO
CALL INSERTAR_USUARIO('117772210','111','PROF');
CALL INSERTAR_USUARIO('217772210','111','PROF');

-- TABLA GRUPO 
CALL INSERTAR_GRUPO('1','1','AD100','117772210','8:00AM - 9:40AM');
CALL INSERTAR_GRUPO('2','1','AD100','117772210','10:00AM - 11:40AM');
CALL INSERTAR_GRUPO('3','1','BI100','217772210','10:00AM - 11:40AM');
CALL INSERTAR_GRUPO('4','1','BI100','117772210','1:00PM - 2:40PM');

-- TABLA MATRICULAS
CALL REALIZA_MATRICULA('1','119994410','1',0);
CALL REALIZA_MATRICULA('2','117050890','1',0);
CALL REALIZA_MATRICULA('3','112233445','3',0);

COMMIT;
