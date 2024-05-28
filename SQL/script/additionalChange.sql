use m2m_clothing
go

create table [Payment]
(
    sys_payment_id int primary key identity,
    payment_id     nvarchar(255) unique,
    payer_id       nvarchar(255) not null,
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

Alter table [Order]
    add voucher_id int foreign key references [Voucher] (voucher_id) NULL;
go
ALTER table [OrderDetail]
    add product_id int foreign key references [Product] (product_id) NULL,
        order_id int foreign key references [Order] (order_id) NULL,
        sub_total float;
go
-- Tạo 5 bản ghi cho bảng [Order]
-- INSERT INTO [Order] (customer_id, phone_number, delivery_address, payment_method, total_amount, order_status, count_sp)
-- VALUES (1, '1234567890', '123 Street, City, Country', 'Credit Card', 100.00, 'Pending', 3),
--        (2, '0987654321', '456 Avenue, City, Country', 'PayPal', 75.50, 'Delivered', 2),
--        (3, '5555555555', '789 Road, City, Country', 'Cash on Delivery', 150.25, 'Processing', 1),
--        (4, '1111111111', '321 Lane, City, Country', 'Bank Transfer', 200.75, 'Cancelled', 1),
--        (1, '9999999999', '654 Boulevard, City, Country', 'Credit Card', 80.00, 'Completed', 4);
-- go
-- Tạo 5 bản ghi cho bảng [OrderDetail]
-- INSERT INTO [OrderDetail] (order_id_detail, nameproduct, quatity, toal_product, product_id, order_id, sub_total)
-- VALUES (1, 'Product A', 2, 50.00, 15, 1, 100.00),
--        (2, 'Product B', 1, 30.00, 12, 1, 30.00),
--        (3, 'Product C', 3, 20.00, 10, 2, 60.00),
--        (4, 'Product D', 4, 15.00, 14, 3, 60.00),
--        (5, 'Product E', 1, 100.00, 5, 4, 100.00);
-- go
update Product
set sold = 0
where 1 = 1
go
create or alter trigger triggerOnCreateOrder
    on OrderDetail
    for INSERT
    as
begin
    update p
    set p.sold += i.quatity
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
             left join [OrderDetail] od on p.product_id = od.product_id
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
go
-- ALTER TABLE [user]
-- DROP COLUMN gg_token, hashed_pass;
-- ALTER TABLE [user]
-- ADD is_disable bit default 0;

