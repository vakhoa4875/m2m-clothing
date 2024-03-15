use master
go

drop database if exists m2m_clothing
go

create database m2m_clothing
go

use m2m_clothing
go

--create table
CREATE TABLE Account (
  user_id int IDENTITY(1,1) PRIMARY KEY,
  username nvarchar(63) NOT NULL unique,
  email varchar(255) NOT NULL unique,
  hashed_password varchar(255) NOT NULL,
  is_admin bit
);

CREATE TABLE Category (
  category_id int IDENTITY(1,1) PRIMARY KEY,
  category_name nvarchar(63) NOT NULL unique,
  logo varchar(255),
  description nvarchar(300)
);

CREATE TABLE Userinfo (
  user_id int PRIMARY KEY,
  fullname nvarchar(127),
  gender nvarchar(20),
  avatar nvarchar(255),
  dob date,
  description nvarchar(300),
  job_title nvarchar(63),
  FOREIGN KEY (user_id) REFERENCES Account(user_id)
);


CREATE TABLE Product (
  product_id int IDENTITY(1,1) PRIMARY KEY,
  product_name nvarchar(255) NOT NULL,
  price float,
  quantity int,
  description nvarchar(max),
  average_rate float,
  rate_count int,
  sold int,
  pictures varchar(max),
  videos varchar(255),
  category_id int FOREIGN KEY REFERENCES Category(category_id)
);















































