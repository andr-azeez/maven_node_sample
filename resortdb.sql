CREATE database RESORTDB;
-- create user 'MyUser'@'localhost' identified by 'myuser';
-- grant all on resortdb.* to 'MyUser'@'LOCALHOST';
use resortdb;

-- members table
create table members (
	memberId int primary key,
    memberName varchar(50) not null,
    email varchar(100) not null unique,
    phone varchar(12) not null,
    walletbalance double not null,
    passkey varchar(25) not null
);

alter table members add membershipdate date not null;

-- employees table
create table employees (
	employeeid int primary key,
    employeename varchar(50) not null,
    phone varchar(12) not null,
    email varchar(100) not null unique,
    passkey varchar(25) not null
);

-- services table
create table amenities (
	amenityId int primary key,
    employeeId int not null,
    amenityname varchar(100) not null unique,
    price double not null,
    amenityCatagory varchar(50) not null
);

-- bookings table
create table bookings (
	bookingid int primary key,
    bookingdate date not null,
    numRooms int not null,
    totalamt double not null,
    serviceId int not null,
    memberid int not null,
    employeeid int not null
);

-- coupon table
create table coupon (
	couponid varchar(15) primary key,
    expirydate date not null,
    discount double not null,
    employeeid int not null
);

-- table for availed coupons
create table availed (
	couponid varchar(15) not null,
    memberid int not null
);

-- adding foreign key constraints
alter table amenities add foreign key (employeeid) references employees(employeeid);
alter table bookings add foreign key (serviceId) references amenities(amenityId);
alter table bookings add foreign key (memberid) references members(memberId);
alter table bookings add foreign key (employeeid) references employees(employeeid);
alter table coupon add foreign key (amenityId) references amenities(amenityId);
alter table availed add foreign key (couponid) references coupon(couponid);
alter table availed add foreign key (memberid) references members(memberid);

-- adding a new column to bookings table
alter table bookings add bookingstatus varchar(20) not null;


show tables;
desc bookings;

insert  into members (memberid, membername, email, phone, walletbalance, passkey) values
(103,'Carine Schmitt','carrie@abc.com','872919191',21000.00,'carrie123'),
(112,'Jean King','jking@xyz.com','7025551838', 71800.00, 'king123'),
(114,'Peter Ferguson','petef@xyz.com','9520034555', 72300.00, 'pete123'),
(119,'Janine Labrune','janie@abc.com','4067118555',48200.00,'janie123'),
(124,'Susan Nelson','susan@bcd.co.in','4155551450', 21050.00, 'susan123'),
(128,'Roland Keitel','roland.k@xyz.com','696905551',59700.00, 'rk@123'),
(129,'Julie Murphy','julie.murph@bcd.co.in','650555578',64600.00, 'julie123'),
(131,'Mary Lee','lee.m@abc.com', '2125557818', 114900.00, 'lee123');

select * from members;

insert  into employees(employeeid,employeeName, phone, email, passkey) values 
(1002,'Diane Murphy', '384579292', 'dmurphy@classicresorts.com','diane123'),
(1056,'Mary Patterson','46112424','mpatterson@classicresorts.com','mary123'),
(1076,'Jeff Firrelli','927323423','jfirrelli@classicresorts.com','jeff123'),
(1088,'William Patterson','487124242','wpatterson@classicresorts.com','wpat123'),
(1102,'Gerard Bondur','540824242','gbondur@classicresorts.com','gb123'),
(1143,'Anthony Bow','5428224234','abow@classicresorts.com','tony123'),
(1165,'Leslie Jennings','32934241','ljennings@classicresorts.com','leslie123'),
(1166,'Larry Thompson','42323065','lthompson@classicresorts.com','larry123'),
(1188,'Andrea Knowlings','2342342173','aknowlings@classicresorts.com','andy123'),
(1216,'Steve Mayors','445353334','smayors@classicresorts.com','steve123');

-- to display all the data
select * from employees;

-- to retrieve specific columns from a table
select employeeid, employeename, email, passkey FROM employees;

select * from employees where employeeid > 1100;

select * from employees where employeeId > 1100 and employeeid < 1150;

select * from employees where employeename LIKE 'L%' and employeeId > 1100;
select * from employees where employeename LIKE 'L%' OR employeeId > 1100;

select * from employees where employeeid between 1100 and 1150;

select * from employees where employeename LIKE 'L%' and employeeId > 1100 
order by employeename;

select * from employees where employeeId in (1002, 1088, 1188, 1143, 1102);

select LEFT(employeename, 3) AS empName from employees;

update employees
set email = REPLACE(email, '@classicresorts.com', '@blackstoneresorts.com');

select substring('dmurphy@blackstoneresorts.com', 6, 5);

show character set;

select length('dmurphy');

insert into availed values ('FREE100' , 112),
	('FREE100', 103),
    ('FIRST50', 112);
    
SELECT COUPON.COUPONID, COUPON.EXPIRYDATE, COUPON.DISCOUNT, COUPON.EMPLOYEEID FROM COUPON 
LEFT JOIN
AVAILED ON COUPON.COUPONID = AVAILED.COUPONID
WHERE AVAILED.MEMBERID = 112;

SELECT * FROM MEMBERS WHERE MEMBERID = (SELECT MAX(MEMBERID) FROM MEMBERS);

SELECT COUPON.COUPONID, COUPON.EXPIRYDATE, COUPON.DISCOUNT, COUPON.EMPLOYEEID FROM COUPON 
LEFT JOIN
AVAILED ON COUPON.COUPONID = AVAILED.COUPONID
WHERE AVAILED.MEMBERID <> 112;
