use m2m_clothing
go
drop table if exists Payment
go
create table [Payment]
(
    sys_payment_id int primary key identity,
    payment_id     nvarchar(255) unique,
    payer_id       nvarchar(255),
    total_amount   float         not null,
    currency       nvarchar(10)           default 'USD',
    method         nvarchar(20)           default 'Paypal',
    intent         nvarchar(255)          default 'sale',
    description    nvarchar(255),
    payment_status nvarchar(50)  not null default 'Processing',
    date_created   datetime               default getdate(),
    date_updated   datetime,
    order_id       int foreign key references [Order] (order_id)
)
go

Alter table [Order]
    add voucher_id int foreign key references [Voucher] (voucher_id) NULL;
go
-- create table order_detail
drop trigger if exists trigger_after_create_Order;
go
drop table if exists OrderDetail;
go
Create table order_detail
(
    order_detail_id int identity (1,1) primary key,
    product_id      int foreign key references Product (product_id),
    order_id        int foreign key references [Order] (order_id),
    price           float not null,
    quantity        int   not null
);
go
alter table [Order]
add order_code nvarchar(127) unique ;
--! create table order_detail
-- template data for shop
insert into Shop(logo, name_shop, date_established, id) values
    ('m2mlogo.png', 'm2mClothing.official', '2024-05-25', 3)
go
--! template data for shop
-- thong ke voucher
update Product
set sold = 0
where 1 = 1
go
create or alter trigger triggerOnCreateOrder
    on Order_Detail
    for INSERT
    as
begin
    update p
    set p.sold += i.quantity,
        p.quantity -= i.quantity
    from Product p
             join inserted i on p.product_id = i.product_id
end
go
create or alter procedure getTop10SoldProduct @month int,
                                              @year int
as
begin
    select top 10 p.product_id
    from Product p
             left join [Order_Detail] od on p.product_id = od.product_id
             left join [Order] o on o.order_id = od.order_id
    where month(o.order_date) = @month
      and year(o.order_date) = @year
    order by p.sold desc, p.quantity
end
-- go
-- exec dbo.getTop10SoldProduct 5, 2024
go
create or alter procedure getActiveMonths
as
begin
    select concat(month(o.order_date), '/', year(o.order_date)) as month
    from [Order] o
    group by year(o.order_date), month(o.order_date)
    order by year(o.order_date) desc, month(o.order_date) desc
end
-- go
-- exec dbo.getActiveMonths
go
create or alter procedure getVoucherUsedInMonth
as
begin
    select concat(month(o.order_date), '/', year(o.order_date)) as month, count(o.voucher_id) voucherCount
    from [Order] o
    group by year(o.order_date), month(o.order_date)
    order by year(o.order_date), month(o.order_date)
end
go
-- exec dbo.getVoucherUsedInMonth
create or alter procedure getTopUsedVoucher @month int,
                                            @year int
as
begin
    select v.voucher_id, count(v.voucher_id) as used_count
    from Voucher v
             join [Order] o on o.voucher_id = v.voucher_id
    where month(o.order_date) = @month
      and year(o.order_date) = @year
    group by v.voucher_id
end
go
drop table if exists Cart;

-- create or alter trigger slugUrlGenerator
--     on Product
--     after insert
--     as
-- begin
--     update Product
--     set slug_url = ()
--     from Product p
--              join inserted i on i.product_id = p.product_id
--     where p.slug_url = ''
--        or p.slug_url is null;
-- end
go
drop trigger if exists trigger_after_create_Order;
go
drop table if exists OrderDetail;
go
Create table order_detail
(
    order_detail_id int identity (1,1) primary key,
    product_id      int foreign key references Product (product_id),
    order_id        int foreign key references [Order] (order_id),
    price           float not null,
    quantity        int   not null
);
go
alter table [Order]
add order_code nvarchar(127) unique ;
-- truncate table Cart;
-- go
insert into Shop(logo, name_shop, date_established, id) values
    ('', 'niggaonsale', '2024-05-25', 3)

-- go
-- exec dbo.getTopUsedVoucher 5, 2024;
-- select top 10 od.product_id, sum(od.quatity) as sold
-- from OrderDetail od
--          join [Order] o on od.order_id = o.order_id
--          join [Product] p on p.product_id = od.product_id
-- where month(o.order_date) = @month
--   and year(o.order_date) = @year
-- group by od.product_id
-- order by sum(od.quatity) desc
--
-- select top 10 od.product_id, p.pictures, p.slug_url, p.product_name, p.price, sum(od.quatity) as sold
-- from OrderDetail od
--          join [Order] o on od.order_id = o.order_id
--          join [Product] p on p.product_id = od.product_id
-- where month(o.order_date) = 5
--   and year(o.order_date) = 2024
-- group by od.product_id, p.slug_url, p.pictures, p.product_name, p.price
-- order by sum(od.quatity) desc, p.price desc
--     exec dbo.getTop10SoldProduct 5, 2024;

-- select top 10 p.*
-- from Product p
--          join [OrderDetail] od on p.product_id = od.product_id
--          join [Order] o on o.order_id = od.order_id
-- where month(o.order_date) = @month
--   and year(o.order_date) = @year
-- order by p.sold desc

-- select top 10 od.product_id, sum(od.quatity) as sold
--         from OrderDetail od
--                  join [Order] o on od.order_id = o.order_id
--                  join [Product] p on p.product_id = od.product_id
--         where month(o.order_date) = @month
--           and year(o.order_date) = @year
--         group by od.product_id
--         order by sum(od.quatity) desc-- CREATE OR ALTER TRIGGER trigger_insert_payment
--     ON Payment
--     AFTER INSERT
-- AS
-- BEGIN
--     UPDATE p
--     SET user_id = o.customer_id
--     FROM Payment p
--     JOIN inserted i ON p.sys_payment_id = i.sys_payment_id
--     JOIN [Order] o ON i.order_id = o.order_id;
-- END;
--
-- drop trigger if exists trigger_insert_payment;

-- create or alter trigger trigger_update_payment
--     on Payment
--     after update
--     as
--     begin
--         update Payment
--         set date_updated = getdate()
--         where sys_payment_id = (select i.sys_payment_id from inserted i)
--     end
-- go
-- ALTER TABLE [user]
-- DROP COLUMN gg_token, hashed_pass;
-- ALTER TABLE [user]
-- ADD is_disable bit default 0;
-- procedure shopdetail 

create table Favorite
(
    id int identity primary key,
    user_id int foreign key references [user] (id),
    product_id int foreign key references Product (product_id),
    date_created datetime default getdate()
)

CREATE PROCEDURE GetShopDetails
    @shop_id int
AS
BEGIN
    -- Shop Details
    ;WITH ShopDetails AS (
        SELECT 
            s.logo AS ShopLogo,
            s.name_shop AS ShopName
        FROM 
            Shop s
        WHERE 
            s.shop_id = @shop_id
    ),
    TotalProducts AS (
        SELECT 
            COUNT(p.product_id) AS TotalProducts
        FROM 
            Product p
        WHERE 
            p.shop_id = @shop_id
    ),
    OrdersSoldInOneMonth AS (
        SELECT 
            COUNT(od.order_detail_id) AS OrdersSoldInOneMonth
        FROM 
            [Order] o
            JOIN order_detail od ON o.order_id = od.order_detail_id
        WHERE 
            o.customer_id IN (SELECT u.id FROM [user] u WHERE u.id = @shop_id)
            AND o.order_date >= DATEADD(MONTH, -1, GETDATE())
    ),
    --ShopParticipation AS (
    --    SELECT 
    --        DATEDIFF(DAY, MIN(u.dob), GETDATE()) AS DaysParticipated
    --    FROM 
    --        [user] u
    --    WHERE 
    --        u.id = @shop_id
    --),
	    ShopParticipation AS (
        SELECT 
             s.date_established AS DaysParticipated
        FROM 
            Shop s
        WHERE 
            s.shop_id = @shop_id
    ),
    TotalComments AS (
        SELECT 
            COUNT(c.comment_id) AS TotalComments
        FROM 
            Comment c
        WHERE 
            c.product_id IN (SELECT p.product_id FROM Product p WHERE p.shop_id = @shop_id)
    ),
    TotalCategories AS (
        SELECT 
            COUNT(DISTINCT p.category_id) AS TotalCategories
        FROM 
            Product p
        WHERE 
            p.shop_id = @shop_id
    ),
	    TotalLikes AS (
        SELECT 
            COUNT(f.id) AS TotalLikes
        FROM 
            Favorite f
        WHERE 
            f.product_id IN (SELECT p.product_id FROM Product p WHERE p.shop_id = @shop_id)
    )
    SELECT
        sd.ShopLogo,
        sd.ShopName,
        tp.TotalProducts,
        osm.OrdersSoldInOneMonth,
        sp.DaysParticipated,
        tc.TotalComments,
        tcat.TotalCategories,
        tl.TotalLikes
    FROM
        ShopDetails sd
        CROSS JOIN TotalProducts tp
        CROSS JOIN OrdersSoldInOneMonth osm
        CROSS JOIN ShopParticipation sp
        CROSS JOIN TotalComments tc
        CROSS JOIN TotalCategories tcat
        CROSS JOIN TotalLikes tl;

END
GO

create table Favorite
(
    id int identity primary key,
    user_id int foreign key references [user] (id),
    product_id int foreign key references Product (product_id),
    date_created datetime default getdate()
)
