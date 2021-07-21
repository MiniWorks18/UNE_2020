# From https://www.w3resource.com/sql-exercises/sql-subqueries-exercises.php#SQLEDITOR

# 1
select first_name, last_name from employees where salary > (select salary from employees where employee_id = 163);

# 2
select first_name||' '||last_name as name, salary, department_id, job_id 
from employees 
where job_id = (
	select job_id 
	from employees 
	where employee_id = 169);

# 3
select first_name, last_name, salary, department_id
from employees
where salary in
(select min(salary) from employees
group by department_id);

# 4
select employee_id,first_name,last_name 
from employees
where salary > 
(select avg(salary)
from employees);

# 5
select first_name,last_name,employee_id,salary
from employees
where manager_id = 
(select employee_id 
from employees 
where first_name = 'Payam');

# 6 
select e.department_id, e.first_name, e.last_name, e.job_id, d.department_name 
from employees e, departments d
where e.department_id = d.department_id
and d.department_name = 'Finance';

# 7 
select *
from employees
where salary = 3000 
and manager_id = 121;

# 8 
select *
from employees
where employee_id in (134,159,183);

# 9 
select *
from employees
where salary >= 1000
and salary <= 3000;

# 13
select first_name, last_name hire_date 
from employees
where department_id = (
select department_id 
from employees
where first_name = 'Clara')
and first_name != 'Clara';