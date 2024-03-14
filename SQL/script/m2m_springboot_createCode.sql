use master
go
drop database if exists m2m_springboot
go
create database m2m_springboot;
go
use m2m_springboot;
go

create table nguoidung (
	userid int identity(1, 1) primary key,
	username nvarchar(63) not null,
	email nvarchar(127) not null,
	matkhau nvarchar(127) not null,
	vaitro bit
);

create table diachi (
	dcid int identity(1,1) primary key,
	idnd int not null,
	country nvarchar(50) not null,
	dcct nvarchar(255),
	constraint fk_location_user foreign key (idnd) references nguoidung(userid)
);

-- Insert 5 records into [user] table
INSERT INTO nguoidung (username, email, matkhau, vaitro)
VALUES 
  ('User1', 'user1@example.com', 'password1', 1),
  ('User2', 'user2@example.com', 'password2', 0),
  ('User3', 'user3@example.com', 'password3', 1),
  ('User4', 'user4@example.com', 'password4', 0),
  ('User5', 'user5@example.com', 'password5', 1);

-- Insert 10 records into [location] table
INSERT INTO DiaChi (idnd, country, dcct)
VALUES 
  (1, 'Country1', 'Location Details 1'),
  (2, 'Country2', 'Location Details 2'),
  (3, 'Country3', 'Location Details 3'),
  (4, 'Country4', 'Location Details 4'),
  (5, 'Country5', 'Location Details 5'),
  (1, 'Country6', 'Location Details 6'),
  (2, 'Country7', 'Location Details 7'),
  (3, 'Country8', 'Location Details 8'),
  (4, 'Country9', 'Location Details 9'),
  (5, 'Country10', 'Location Details 10');

