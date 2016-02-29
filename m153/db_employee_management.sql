/* 
 * db name:    db_employee_management
 * db type:    MariaDB
 * db version: 5.X
 */

CREATE OR REPLACE DATABASE db_employee_management;

CREATE OR REPLACE TABLE tbl_company(
	company_id			INTEGER 		NOT NULL 	AUTO_INCREMENT,
	company_name 		VARCHAR (32) 	NOT NULL	,
	
	PRIMARY KEY(company_id)
);

CREATE OR REPLACE TABLE tbl_division(
	division_id 		INTEGER			NOT NULL	AUTO_INCREMENT,
	division_name		VARCHAR (32)	NOT NULL	,
	division_company_id	INTEGER			NOT NULL,
	
	PRIMARY KEY(division_id)
);

CREATE OR REPLACE TABLE tbl_employee(
	employee_id			INTEGER 		NOT NULL	AUTO_INCREMENT,
	employee_name		VARCHAR (32)	NOT NULL	,
	employee_division
);