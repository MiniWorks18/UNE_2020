# name, number, and average salary of department employees which work for departments that have an average higher than 30,000
select dname,dnumber,avg(salary) from department,employee 
where dnumber=dno 
group by dnumber
having avg(salary) > 30000;

select fname||' '||lname as "Employee" from employee where lname like '%s';

select fname||' '||lname as "Employee",pname from employee,project where pname like '%Product%';

create view mgrInfo as select dname,fname,salary from employee,department where ssn=mgrssn;

create view researchInfo as select e.fname as "Employee",e.salary,s.fname as "Supervisor" from employee e,employee s where e.superssn=s.ssn;

create table student(
name varchar(20),
student_number integer primary key,
class integer,
major varchar(10));

create table course(
course_name varchar(20),
course_number varchar(10) primary key,
credit_hours integer,
department varchar(5)
);

create table section(
section_id integer primary key,
course_number varchar(10),
semester varchar(5),
year integer,
instructor varchar(10),
foreign key (course_number) references course(course_number)
on delete restrict on update cascade);

create table grade_report(
student_number integer primary key,
section_identifier integer,
grade varchar(3),
foreign key (student_number) references student(student_number)
on delete cascade on update cascade,
foreign key (section_identifier) references section(section_id)
on delete restrict on update cascade);

create table prerequisite(
course_number varchar(10) primary key,
prerequisite_number varchar(10),
foreign key (course_number) references course(course_number)
on delete cascade on update cascade,
foreign key (prerequisite_number) references course(course_number)
on delete cascade on update cascade
);