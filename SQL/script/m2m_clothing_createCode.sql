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
  is_disable bit default 0,
  is_admin bit
);


CREATE TABLE Userinfo (
  user_id int PRIMARY KEY,
  fullname nvarchar(127) default 'your fullname here',
  gender nvarchar(20) default 'Male',
  avatar nvarchar(255) default 'user.jpg',
  dob date default getdate(),
  description nvarchar(300),
  job_title nvarchar(63),
  FOREIGN KEY (user_id) REFERENCES Account(user_id)
);

CREATE TABLE Category (
  category_id int IDENTITY(1,1) PRIMARY KEY,
  category_name nvarchar(63) NOT NULL unique,
  logo varchar(255),
  description nvarchar(300)
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
  slug_url varchar(255) default '',
  category_id int FOREIGN KEY REFERENCES Category(category_id)
);
create table [user] (
    id          int             primary key,
    username    varchar(63)     not null unique ,
    email       varchar(63)     not null unique ,
    gg_token    varchar(255)    null ,
    hashed_pass varchar(255)    not null,
    is_admin    bit             default 0,
    is_disable  bit             default 0,
    fullname    nvarchar(63)    default 'fullname_val',
    gender      nvarchar(10)    default 'Nam',
    avatar      varchar(63)     default 'user.png',
    dob         date            default getdate(),
    description nvarchar(300)   default 'description_val',
    job_title   nvarchar(63)    default 'site user'
)
go

create or alter trigger trigger_insert_into_user
    on Userinfo
    after insert
    as
    begin
        insert into [user]
        select  top 1 a.user_id,
                a.username,
                a.email,
                null,
                a.hashed_password,
                a.is_admin,
                a.is_disable,
                i.fullname,
                i.gender,
                i.avatar,
                i.dob,
                i.description,
                i.job_title
        from inserted i
        join Account a
        on a.user_id = i.user_id
    end
go

go
CREATE OR ALTER TRIGGER gen_user_info 
ON Account
AFTER INSERT
AS
BEGIN
    INSERT INTO Userinfo (user_id)
    SELECT user_id
    FROM inserted;
END;

go













































