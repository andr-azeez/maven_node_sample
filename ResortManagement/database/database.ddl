CREATE database RESORTDB;

use RESORTDB;

-- members table
create table members (
	memberId int primary key,
    memberName varchar(50) not null,
    email varchar(100) not null unique,
    phone varchar(12) not null,
    walletbalance double not null,
    passkey varchar(25) not null
);

-- employees table
create table employees (
	employeeid int primary key,
    employeename varchar(50) not null,
    phone varchar(12) not null,
    email varchar(100) not null unique,
    passkey varchar(25) not null
);

-- services table
create table services (
	serviceId int primary key,
    employeeId int not null,
    servicename varchar(100) not null,
    price double not null,
    serviceCatagory varchar(50) not null
);

-- bookings table
create table bookings (
	bookingId int primary key,
    bookingdate date not null,
    numRooms int not null,
    totalamt double not null,
    serviceId int not null,
    memberId int not null,
    employeeId int not null
);

-- adding foreign key constraints
alter table services add foreign key (employeeid) references employees(employeeid);
alter table bookings add foreign key (serviceId) references services(serviceId);
alter table bookings add foreign key (memberid) references members(memberId);
alter table bookings add foreign key (employeeid) references employees(employeeid);