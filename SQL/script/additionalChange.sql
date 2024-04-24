-- insert into [user] (username, email, hashed_pass)
-- values ('username11', 'email@gmail.com', '123')

alter table [user]
add sdt varchar(15);
use m2m_clothing
go
INSERT INTO [Order] (customer_id, phone_number, delivery_address, payment_method, total_amount, order_status)
VALUES
    (3, '123456789', '123 Main Street, City, Country', 'Paypal', 100.50, 'Wait for confirmation'),
    (2, '987654321', '456 Elm Street, City, Country', 'PayPal', 75.25, 'Confirmed successfully'),
    (1, '111222333', '789 Oak Street, City, Country', 'Cod', 150.75, 'Confirmed successfully'),
    (3, '444555666', '321 Pine Street, City, Country', 'PayPal', 200.00, 'Confirmed successfully'),
    (2, '777888999', '654 Maple Street, City, Country', 'PayPal', 50.00, 'Confirmed successfully');

create table [Payment] (
    sys_payment_id          int             primary key identity ,
    payment_id              nvarchar(255)   unique ,
    payer_id                nvarchar(255)   not null ,
    total_amount            float           not null ,
    currency                nvarchar(10)    default 'USD',
    method                  nvarchar(20)    default 'Paypal',
    intent                  nvarchar(255)   default 'Hot sale deal',
    description             nvarchar(255)   ,
    order_id                int             foreign key references [Order](order_id)
)