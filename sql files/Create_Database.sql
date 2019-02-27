
-------------------------------------------------------
-------------------------------------------------------
-- Acessar a pasta do script Create_Database.sql

-- Conectar no psql e rodar o script

-- "C:\Arquivos de programas\PostgreSQL\9.0\bin\psql.exe" -U postgres -W -d postgres -f Create.sql

-------------------------------------------------------
-------------------------------------------------------

--
---------------------------------------------
-- Cria o banco de dados
---------------------------------------------
--

CREATE	DATABASE		master
WITH
	TEMPLATE		=	template0
	ENCODING		=	'UTF8'
	CONNECTION LIMIT	=	-1
;


--
---------------------------------------------
-- Conecta no banco master rec�m criado
---------------------------------------------
--

\c	master
;


--
---------------------------------------------
-- CRIA FUN��ES COMPAT�VEIS COM ORACLE
---------------------------------------------
--

\i		Function.sql
;


--
---------------------------------------------
-- CRIA AS TABELAS DO SISTEMA
---------------------------------------------
--

\i		Table.sql
;


--
---------------------------------------------
-- INSERE OS DADOS PADROES
---------------------------------------------
--

\i		Insert_Cidades.sql
;


--
---------------------------------------------
-- CRIA O ROLE ADMIN DO SISTEMA
---------------------------------------------
--

\i		Admin.sql
;