use master
go

drop database if exists m2m_clothing
go

create database m2m_clothing
go

use m2m_clothing
go

--create table
CREATE TABLE Account
(
    user_id         int IDENTITY (1,1) PRIMARY KEY,
    username        nvarchar(63) NOT NULL unique,
    email           varchar(255) NOT NULL unique,
    hashed_password varchar(255) NOT NULL,
    is_disable      bit default 0,
    is_admin        bit
);


CREATE TABLE Category
(
    category_id   int IDENTITY (1,1) PRIMARY KEY,
    category_name nvarchar(63) NOT NULL unique,
    logo          varchar(255),
    description   nvarchar(300)
);

CREATE TABLE Userinfo
(
    user_id     int PRIMARY KEY,
    fullname    nvarchar(127) default 'your fullname here',
    gender      nvarchar(20)  default 'Male',
    avatar      nvarchar(255) default 'user.jpg',
    dob         date          default getdate(),
    description nvarchar(300),
    job_title   nvarchar(63),
    FOREIGN KEY (user_id) REFERENCES Account (user_id)
);

CREATE TABLE Sale
(
    sale_ID      int IDENTITY (1,1) PRIMARY key,
    sale_Name    nvarchar(255) not null,
    sale_Percent int           not null,
    sale_Start   date,
    sale_End     date,
)

create table [user]
(
    id          int primary key identity (1,1),
    username    varchar(63)  not null unique,
    email       varchar(63)  not null unique,
--     gg_token    varchar(255) null,
--     hashed_pass varchar(255) null,
    is_admin    bit                   default 0,
    is_disable  bit                   default 0,
    fullname    nvarchar(63)          default 'fullname_val',
    gender      nvarchar(10)          default 'Nam',
    avatar      varchar(63)           default 'user.png',
    dob         date                  default getdate(),
    description nvarchar(300)         default 'description_val',
    job_title   nvarchar(63)          default 'Unemployed',
    role_id     int          not null default 3,
    role_name   nvarchar(63) not null default 'User',
    processed   bit                   default 0
);
create table Shop
(
    shop_id int IDENTITY (1,1) primary key,
    logo varchar(255),
    name_shop nvarchar(255),
    date_established date,
    id int unique ,
    foreign key (id) references [user] (id)
)
go
CREATE TABLE Product
(
    product_id   int IDENTITY (1,1) PRIMARY KEY,
    product_name nvarchar(255) NOT NULL,
    price        float,
    quantity     int,
    description  nvarchar(max),
    average_rate float,
    rate_count   int,
    sold         int,
    pictures     varchar(max),
    videos       varchar(255),
    slug_url     varchar(255) default '',
    shop_id      int foreign key references Shop(shop_id),
    category_id  int FOREIGN KEY REFERENCES Category (category_id),
    sale_ID      int FOREIGN KEY REFERENCES Sale (sale_ID),
);

CREATE TABLE AccountGG
(
    user_id_gg         int IDENTITY (1,1) PRIMARY KEY,
    access_token_gg    varchar(225) NOT NULL,
    sub_gg             varchar(225) NOT NULL unique,
    username_gg        nvarchar(63) NOT NULL unique,
    email_gg           varchar(255) NOT NULL unique,
    is_disable_gg      bit default 0,
    is_admin_gg        bit default 0
)
INSERT INTO AccountGG (access_token_gg, sub_gg, username_gg, email_gg)
VALUES ('abc123xyz', 'sub123', N'ten_nguoi_dung', 'example@email.com');


create table [Cart]
(
    id           int FOREIGN KEY REFERENCES [user] (id),
    name_product varchar(255),
    quatity      int,
    price        int
);


create table Comment
(
    comment_id  int IDENTITY (1,1) PRIMARY KEY,
    comment     nvarchar(255),
    product_id  int,
    user_id     int,
    create_date date,
    FOREIGN KEY (user_id) REFERENCES [user] (id),
    FOREIGN KEY (product_id) REFERENCES Product (product_id)
);
go

create table Voucher
(
    voucher_id   int IDENTITY (1,1) primary key,
    voucher_name nvarchar(255),
    reduce       int,
    quantity     int,
    start_day    date,
    end_day      date
)
go

create table VoucherDetails
(
    voucher_details_id int IDENTITY (1,1) primary key,
    voucher_id         int,
    user_id            int,
    FOREIGN KEY (voucher_id) REFERENCES Voucher (voucher_id),
    FOREIGN KEY (user_id) REFERENCES [user] (id)
)
go
alter table [user]
add sdt varchar(15);
ALTER TABLE [user]
ADD address nvarchar(255);
ALTER TABLE [user]
ADD account_id int null foreign key references Account(user_id);
--     ,gg_account_id int null foreign key;
ALTER TABLE [user]
ADD account_id_gg int null foreign key references AccountGG(user_id_gg);
go
--trigger insert into user after insert into Account
-- create or alter trigger trigger_after_insert_into_UserInfo
--     on Account
--     after insert
--     as
-- begin
--     --check if the inserted user is already existed in [user]
--     declare @existed int = 0;
--     select @existed = id
--     from [user]
--     where id = (select user_id from inserted);
--     --if inserted user is not existed -> insert into [user]
--     if (@existed = 0)
--         begin
--             insert into [user] (username, email, is_admin, is_disable, role_id, role_name, processed)
--             select top 1 a.username,
--                          a.email,
--                          a.is_admin,
--                          a.is_disable,
--                          iif(a.is_admin = 1, 1, 3),
--                          iif(a.is_admin = 1, 'Admin', 'User'),
--                          1
--             from inserted i
--                      join Account a on a.user_id = i.user_id;
--             --update role
--             update [user]
--             set role_id   = 1,
--                 role_name = 'Admin'
--             where id = (select user_id from inserted)
--               and is_admin = 1;
--         end
-- end;
-- go
--trigger after insert into user for syncing with account
-- create or alter trigger trigger_after_insert_into_user
--     on [user]
--     AFTER insert
--     as
-- begin
--     declare @i_processed bit;
--     select @i_processed = i.processed
--     from inserted i;
--
--     if (@i_processed <> 1)
--         begin
--             --             begin TRANSACTION ;
--             print ('begin transaction');
--             -- insert into account with inserted value of table user
--             insert into Account (username, email, hashed_password, is_admin)
--             select u.username,
--                    u.email,
--                    u.hashed_pass,
--                    IIF(u.role_id = 3, 0, 1)
--             from [inserted] u;
--             -- set processed to 1
--             update [user]
--             set processed = 1
--             where id in (select i.id from inserted i);
--             --             IF @@TRANCOUNT > 0
-- --             begin
-- --                 print ('end transaction');
-- --                 COMMIT TRANSACTION;
-- --             end
--         end
-- end
-- go

--trigger after update user for syncing with account table
-- create or alter trigger trigger_after_update_user
--     on [user]
--     after update
--     as
-- begin
--     declare @u_isAdmin bit,
--         @u_isDisable bit,
--         @u_hashed_pass varchar(255),
--         @u_user_id int;
--     select @u_isAdmin = i.is_admin,
--            @u_isDisable = i.is_disable,
-- --            @u_hashed_pass = i.hashed_pass,
--            @u_user_id = i.id
--     from inserted i;
--     update Account
--     set is_admin        = @u_isAdmin,
--         is_disable      = @u_isDisable,
--         hashed_password = @u_hashed_pass
--     where user_id = @u_user_id;
-- end
-- go

create or alter trigger trigger_after_create_user
    on [user]
    after INSERT
    as
begin
    declare @id int;

    select @id = inserted.id from inserted;

    insert into Cart (id) values (@id)
end
go

create or alter trigger trigger_before_delete_user
    on [user]
    after delete
    as
begin
    declare @iddelete int;
    select @iddelete = deleted.id from deleted;
    BEGIN TRANSACTION;

    delete from Cart where Cart.id = @iddelete;

    delete from [user] where [user].id = @iddelete;

    COMMIT TRANSACTION;
end
go
--CREATE OR ALTER TRIGGER gen_user_info
--ON Account
--AFTER INSERT
--AS
--BEGIN
--    INSERT INTO Userinfo (user_id)
--    SELECT user_id
--    FROM inserted;
--END;

--go
-- select * from Comment
-- select * from Comment where product_id = 1;

-- select * from [user]

CREATE TABLE [Order]
(
    order_id         int IDENTITY (1,1) PRIMARY KEY,
    customer_id      int NOT NULL,
    order_date       date DEFAULT GETDATE(),
    phone_number     varchar(20),
    delivery_address nvarchar(255),
    payment_method   nvarchar(50),
    total_amount     float,
    order_status     nvarchar(50),
    count_sp         int,
    CONSTRAINT FK_Customer_User FOREIGN KEY (customer_id) REFERENCES [user] (id)
);

Create table [OrderDetail]
(
    order_id_detail int FOREIGN KEY REFERENCES [Order] (order_id),
    nameproduct     nvarchar(255),
    quatity         int,
    toal_product    float
)

go
create or alter trigger trigger_after_create_Order
    on [Order]
    after INSERT
    as
begin
    declare @id int;

    select @id = inserted.order_id from inserted;

    insert into OrderDetail (order_id_detail) values (@id)
end
go
CREATE OR ALTER TRIGGER trigger_before_delete_voucher
    ON Voucher
    INSTEAD OF DELETE
    AS
BEGIN
    BEGIN TRANSACTION;
    BEGIN TRY
        DELETE FROM VoucherDetails WHERE voucher_id IN (SELECT voucher_id FROM deleted);
        DELETE FROM Voucher WHERE voucher_id IN (SELECT voucher_id FROM deleted);
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
    END CATCH;
END;
go

create or alter trigger trigger_after_delete_Sale
    on [Sale]
    instead of DELETE
    as
begin
    -- Xóa các bản ghi liên quan trong bảng Product
    update Product
    set sale_id = null
    where sale_id in (select deleted.sale_ID from deleted)

    -- Tiếp tục xóa bản ghi Sale chính
    delete
    from Sale
    where Sale.sale_ID in (select deleted.sale_ID from deleted)
end
go

create or alter trigger trigger_after_insert_account
    on [Account]
    for insert
    as
    begin
        insert into [user] (username, email, is_admin, is_disable, role_id, role_name, account_id)
        select  i.username,
                i.email,
                i.is_admin,
                i.is_disable,
                iif(i.is_admin = 1, 1, 3),
                iif(i.is_admin = 1, 'Admin', 'User'),
                i.user_id
        from inserted i
    end
go

create table Notification
(
    user_id INT,
    namenotification nvarchar(255),
)
go
create or alter trigger trigger_after_insert_accountgg
    on [AccountGG]
    for insert
    as
    begin
        insert into [user] (username, email, is_admin, is_disable, role_id, role_name, account_id_gg)
        select  i.username_gg,
                i.email_gg,
                i.is_admin_gg,
                i.is_disable_gg,
                iif(i.is_admin_gg = 1, 1, 3),
                iif(i.is_admin_gg = 1, 'Admin', 'User'),
                i.user_id_gg
        from inserted i
    end
go
   CREATE OR ALTER TRIGGER trigger_after_insert_voucherdetails
    ON VoucherDetails
    for INSERT
    AS
    BEGIN
        INSERT INTO Notification (user_id, namenotification)
        SELECT i.user_id,null
        FROM inserted i;
    END;




	SELECT COUNT(*) AS total_accounts FROM
(
    SELECT user_id FROM Account
    UNION ALL
    SELECT user_id_gg FROM AccountGG
) AS combined_accounts;

SELECT COUNT(*) AS total_accounts_account FROM Account;
SELECT COUNT(*) AS total_accounts_accountgg FROM AccountGG;
SELECT COUNT(*) AS so_loai_san_pham FROM Category;
SELECT c.category_name, COUNT(p.product_id) AS so_san_pham
FROM Category c
LEFT JOIN Product p ON c.category_id = p.category_id
GROUP BY c.category_name;

select * from Product
SELECT * FROM Product WHERE shop_id = 1;


