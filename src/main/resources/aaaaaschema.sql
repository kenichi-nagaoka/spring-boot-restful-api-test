CREATE table IF NOT EXISTS customers(
	id int primary key auto_increment, 
	name varchar(30), 
	zip_code varchar(7), 
	address varchar(50), 
	phone_number varchar(12), 
	sex varchar(1)
);