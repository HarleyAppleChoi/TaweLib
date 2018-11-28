CREATE TABLE resource (
    id char(6) not null,
    title char(70)not null,
    _year char(30)not null,
    image char(100)not null,
    numAvCopies varchar(4)not null,
    duration varchar(4)not null
);

CREATE TABLE book (
    id char(6)not null,
    author char(70) not null,
    publisher char(70)not null,
    genre char(100),
    ISBN varchar(4),
    _language varchar(4)
    foreign key id references resource(id)
);

CREATE TABLE DVD (
    id char(6) not null,
    direction char(70) not null,
    runtime char(70) not null,
    _language char(100),
    foreign key id references resource(id)
);

CREATE TABLE DVD_subtitle(
    id char(10) not null,
    subtitle char(30) not null,
    foreign key id reference DVD(id)
);

CREATE TABLE laptop (
    id char(10) not null,
    manufacturer char(30)not null,
    model char (60) not null,
    foreign key id reference resource(id)

);
