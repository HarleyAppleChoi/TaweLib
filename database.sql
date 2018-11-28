CREATE TABLE resource (
    resourceID char(6) not null,
    title char(70)not null,
    _year char(30)not null,
    image char(100)not null,
    numAvCopies char(4)not null,
    duration char(4)not null

);

CREATE TABLE book (
    resourceID char(6)not null,
    author char(70) not null,
    publisher char(70)not null,
    genre char(100),
    ISBN varchar(4),
    _language varchar(4),
    foreign key resourceID references resource(resourceID)
);

CREATE TABLE DVD (
    resourceID char(6) not null,
    direction char(70) not null,
    runtime char(70) not null,
    _language char(100),
    foreign key resourceID references resource(resourceID)
);

CREATE TABLE DVD_subtitle (
    resourceID char(10) not null,
    subtitle char(30) not null,
    foreign key resourceID references DVD(resourceID)
);

CREATE TABLE laptop (
    resourceID char(10) not null,
    manufacturer char(30)not null,
    model char(60) not null,
    operatingSystem char(70) not null,
    foreign key resourceID references resource(resourceID)

);

CREATE TABLE user (
    username char(40) not null,
    firstname char(50) not null,
    lastname char(50) not null,
    mobileNo int not null,
    address char(100) not null,
    image char(100) not null,
    primary key(username)

);

CREATE TABLE normal_user (
    username char(40) not null,
    balance int not null,
    foreign key username references user(username)

);

CREATE TABLE librarian (
    username char(40) not null,
    employmentDate char(30) not null,
    staffNo int not null,
    foreign key username references user(username)

);

CREATE TABLE borrowing (
    borrowingID char(10) not null,
    borrowDate char(10) not null,
    dueDate char(10) not null,
    resourceID char(10) not null,
    onLoan char(30) not null,
    primary key(borrowingID),
    foreign key resourceID references resource(resourceID)

);

CREATE TABLE current_borrow_his (
    resourceID char(10) not null,
    borrowingID not null,
    foreign key resourceID references resource(resourceID),
    foreign key borrowingID references borrowing(borrowingID)

);

CREATE TABLE current_borrowing (
    username char(30) not null,
    borrowingID char(10) not null,
    foreign key username references user(username),
    foreign key borrowingID references borrowing(borrowingID)
    
);

CREATE TABLE returned_his (
    username char(30) not null,
    borrowingID char(10) not null,
    foreign key username references borrowing(borrowingID),
    foreign key borrowingID references user(username)

);

CREATE TABLE transaction (
    transID char(10) not null,
    debit/credit char(20) not null,
    amount char(10) not null,
    primary key(transID)

);

CREATE TABLE transaction_his (
    username char(30) not null,
    transID char(10) not null,
    foreign key username references user(username),
    foreign key transID references transaction(transID) 

);

CREATE TABLE overdue_transaction (
    transID char(10) not null,
    borrowingID char(10) not null,
    foreign key transID references transaction(transID),
    foreign key borrowingID references borrowing(borrowingID)

);

CREATE TABLE reserved_item (
    username char(30) not null,
    resourceID char(10) not null,
    foreign key username references user(username),
    foreign key resourceID references resource(resourceID)

);

CREATE TABLE request_item (
    username char(30) not null,
    resourceID char(10) not null,
    foreign key username references user(username),
    foreign key resourceID references resource(resourceID)

);