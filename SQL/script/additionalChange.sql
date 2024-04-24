-- insert into [user] (username, email, hashed_pass)
-- values ('username11', 'email@gmail.com', '123')

alter table [user]
add sdt varchar(15);


INSERT INTO [Order] (customer_id, phone_number, delivery_address, payment_method, total_amount, order_status)
VALUES
    (3, '123456789', '123 Main Street, City, Country', 'Paypal', 100.50, 'Need payment'),
    (2, '987654321', '456 Elm Street, City, Country', 'PayPal', 75.25, 'Paid'),
    (1, '111222333', '789 Oak Street, City, Country', 'Cash', 150.75, 'Pending'),
    (3, '444555666', '321 Pine Street, City, Country', 'Credit Card', 200.00, 'Paid'),
    (2, '777888999', '654 Maple Street, City, Country', 'Credit Card', 50.00, 'Paid');

create table [Payment] (
    sys_payment_id          int             primary key identity ,
    payment_id              nvarchar(255)   unique ,
    payer_id                nvarchar(255)   not null ,
    total_amount            float           not null ,
    currency                nvarchar(10)    default 'USD',
    method                  nvarchar(20)    default 'Paypal',
    intent                  nvarchar(255)   default 'sale',
    description             nvarchar(255)   ,
    payment_status          nvarchar(50)    not null default 'Processing',
    date_created            datetime        default getdate(),
    date_updated            datetime ,
    order_id                int             foreign key references [Order](order_id)
)

-- CREATE OR ALTER TRIGGER trigger_insert_payment
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