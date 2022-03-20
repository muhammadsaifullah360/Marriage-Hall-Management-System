---------------------------------Employee Schema------------------------------
create table Employee_Detail
(
    ID           int primary key,
    Name         varchar2(36)  not null,
    Phone_Number varchar2(12)  not null,
    Email        varchar2(256) null,
    Address      varchar2(256) not null,
    Date_Time    date default sysdate
);

create table Employee_Education_Detail
(
    Employee_ID       int references Employee (ID) on delete cascade,
    Certificate_Title varchar2(256)  not null,
    Organization_Name varchar2(255)  not null,
    Verification_No   varchar2(36)   not null,
    Date_Issued_On    date           not null,
    Description       varchar2(1000) not null,
    Date_Time         date default sysdate
);

create table Employee_Skill_Detail
(
    Employee_ID int references Employee (ID) on delete cascade,
    Skill_Title varchar2(255)  not null,
    Field_Title varchar2(255)  not null,
    Description varchar2(1000) not null,
    Date_Time   date default sysdate
);

create table Employee_Experience_Detail
(
    Employee_ID       int references Employee (ID) on delete cascade,
    Job_Title         varchar2(256) not null,
    Organization_Name varchar2(255) not null,
    Field_Name        varchar2(255) not null,
    Duration          float         not null,
    Date_Time         date default sysdate
);

create table Employee_Duty_Detail
(
    Employee_ID   int references Employee (ID) on delete cascade,
    Team_ID       int references Team (ID) null,
    Job_Title     varchar2(256)            not null,
    Shift         varchar2(36)             not null,
    Working_Hours int                      not null,
    Salary        int                      not null,
    Joining_Date  date default sysdate,
    Date_Time     date default sysdate
);

create table Team
(
    ID        int primary key,
    Task_Type varchar2(256)
);

---------------------------------Event Schema---------------------------------
create table Customer
(
    ID           int primary key,
    Name         varchar2(256) not null,
    Phone_Number varchar2(12)  not null,
    Email        varchar2(256) null,
    Address      varchar2(256) not null,
    Date_Time    date default sysdate
);

create table Booking
(
    ID           int primary key,
    Customer_ID  int references Customer (ID) not null,
    Team_ID      int references Team (ID)     not null,
    Type         varchar2(256)                not null,
    Booking_Date date                         not null,
    Start_Time   date default sysdate,
    End_Time   date default sysdate,
    Duration     int                          not null,
    Person_Count int                          not null,
    Location     varchar2(256)                null,
    Date_Time    date default sysdate
);

create table Menu
(
    Booking_ID   int references Booking (ID) not null,
    Menu_Service varchar2(256),
    Decoration   varchar2(256),
    Facility     varchar2(256),
    Description  varchar2(1000),
    CONSTRAINT Chk_Menu_Service check ( Menu_Service in ('Self', 'Hall') ),
    CONSTRAINT Chk_Decoration check ( Decoration in ('Simple', 'Fancy', 'Custom') ),
    CONSTRAINT Chk_Facility check ( Facility in ('AC', 'Heater', 'Photographer') )
);

create table Billing
(
    Booking_ID       int references Booking (ID) not null,
    Total_Amount     int                         not null,
    Advance_Payment  int                         not null,
    Per_Head_Charges int                         not null,
    Invoice_No       varchar2(15)                not null,
    Date_Time        date default sysdate
);


create table Dish
(
    Booking_ID int references Booking (ID),
    Dish_Name  varchar2(256)
);

---------------------------------Supplier Schema---------------------------------
create table Supplier
(
    ID           int primary key,
    Name         varchar2(256) not null,
    Phone_Number varchar2(12)  not null,
    Email        varchar2(256) null,
    Address      varchar2(256) not null,
    Date_Time    date default sysdate
);

create table Material
(
    ID                 int primary key,
    Material_Name      varchar2(256)  not null,
    Quantity_Available int            not null,
    Description        varchar2(1000) null,
    Date_Time          date default sysdate
);

create table Material_Purchase
(
    Supplier_ID    int references Supplier (ID) not null,
    Material_ID    int references Material (ID) not null,
    Quantity       int                          not null,
    Unit_Price     int                          not null,
    Invoice_Number varchar2(36)                 not null,
    Date_Time      date                         not null
);

create table SignUp
(
    username    varchar2(36)  		not null,
    password    varchar2(36) 		 	not null,
    Name       varchar2(36)                         not null,
    Email     varchar2(36)                          not null,
    phone_No varchar2(36)                 not null,
    Date_Time      date                         not null
);

CREATE VIEW Event_Booking
AS

SELECT
   booking.ID ,
    Type         ,
    Booking_Date  ,
    Start_Time   ,
    End_Time   ,
    Duration    ,
    Person_Count ,
    Location     ,
    Menu_Service ,
    Decoration   ,
    Facility     ,
    Description  ,
    Phone_Number ,
    Email        ,
    Address
FROM Booking ,MENU,BILLING,Customer;
