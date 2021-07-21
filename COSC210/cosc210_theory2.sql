create table S (
sno varchar(4) primary key,
sname varchar(20),
status integer,
city varchar(20)
);

create table P (
pno varchar(4) primary key,
pname varchar(20),
color varchar(20),
weight float(6),
city varchar(20)
);

create table J (
jno varchar(4) primary key,
jname varchar(20),
city varchar(20)
);

create table SPJ (
sno varchar(4),
pno varchar(4),
jno varchar(4),
qty integer,
foreign key (sno) references S(sno),
foreign key (pno) references P(pno),
foreign key (jno) references J(jno)
);

insert into S(sno,sname,city) values('S1','Smith','London');
insert into S(sno,sname,city) values('S2','Jones','Paris');
insert into S(sno,sname,city) values('S3','Blake','Paris');
insert into S(sno,sname,city) values('S4','Clark','London');
insert into S(sno,sname,city) values('S5','Adams','Athens');	

insert into P(pno,pname,color,city) values('P1','Nut','Red','London');
insert into P(pno,pname,color,city) values('P2','Bolt','Green','Paris');
insert into P(pno,pname,color,city) values('P3','Screw','Blue','Rome');
insert into P(pno,pname,color,city) values('P4','Screw','Red','London');
insert into P(pno,pname,color,city) values('P5','Cam','Blue','Paris');
insert into P(pno,pname,color,city) values('P6','Cog','Red','London');

insert into J(jno,jname,city) values('J1','Sorter','Paris');
insert into J(jno,jname,city) values('J2','Display','Rome');
insert into J(jno,jname,city) values('J3','OCR','Athens');
insert into J(jno,jname,city) values('J4','Console','Athens');
insert into J(jno,jname,city) values('J5','RAID','London');
insert into J(jno,jname,city) values('J6','EDS','Oslo');
insert into J(jno,jname,city) values('J7','Tape','London');

insert into SPJ(sno,jno,pno,qty) values('S1','P1','J1',200);
insert into SPJ(sno,jno,pno,qty) values('S1','P1','J4',700);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J1',400);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J2',200);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J3',200);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J4',500);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J5',600);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J6',400);
insert into SPJ(sno,jno,pno,qty) values('S2','P3','J7',800);
insert into SPJ(sno,jno,pno,qty) values('S2','P5','J1',100);
insert into SPJ(sno,jno,pno,qty) values('S3','P3','J2',200);
insert into SPJ(sno,jno,pno,qty) values('S3','P4','J7',500);
insert into SPJ(sno,jno,pno,qty) values('S4','P6','J2',300);
insert into SPJ(sno,jno,pno,qty) values('S4','P6','J4',300);
insert into SPJ(sno,jno,pno,qty) values('S5','P2','J5',500);