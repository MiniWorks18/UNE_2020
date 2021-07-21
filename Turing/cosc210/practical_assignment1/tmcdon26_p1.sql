
-- COSC210 Practical Assignment 1 Template

-- Please complete the assignment questions using the view templates
-- provided below.

-- *******************************************************************
--                           IMPORTANT
-- *******************************************************************

-- Make sure that you do not alter the names of the views or their
-- attribute values. If you do your assignment will not work in the
-- auto-marking software and you may lose marks!

-- *******************************************************************



CREATE VIEW old_books(first_name, last_name, book_title, edition)
AS
  SELECT a.first_name,
         a.last_name,
         b.title,
         edition
  FROM   editions e
         JOIN books b
           ON b.book_id = e.book_id
         JOIN authors a
           ON a.author_id = b.author_id
  WHERE  e.publication < '1990-01-01';

-- This is where your bit goes: define the query as specified in the
-- assignment description



CREATE VIEW programming_or_perl(book_title)
AS
  SELECT title
  FROM   books
  WHERE  title LIKE '%Programming%'
          OR title LIKE '%Perl%';


-- This is where your bit goes: define the query as specified in the
-- assignment description

CREATE VIEW retail_price_hike(isbn, retail_price, increased_price)
AS
  SELECT isbn,
         retail,
         retail * 1.25 AS Final
  FROM   stock;


-- This is where your bit goes: define the query as specified in the
-- assignment description

CREATE VIEW book_summary(author_first_name, author_last_name, book_title,
subject)
AS
  SELECT a.first_name,
         a.last_name,
         title,
         s.subject
  FROM   books b
         JOIN subjects s
           ON s.subject_id = b.subject_id
         JOIN authors a
           ON a.author_id = b.author_id;

-- This is where your bit goes: define the query as specified in the
-- assignment description


CREATE VIEW value_summary(total_stock_cost, total_retail_cost)
AS
  SELECT cost * stock   AS cost_value,
         retail * stock AS retail_value
  FROM   stock;

-- This is where your bit goes: define the query as specified in the
-- assignment description


CREATE VIEW profits_by_isbn(book_title, edition_isbn, total_profit)
AS
  SELECT b.title, c.isbn, ( retail * count ) - ( cost * count ) AS profit
  FROM   stock c
         JOIN (SELECT Count(c_id),
                      isbn
               FROM   shipments
               GROUP  BY isbn) ship
           ON c.isbn = ship.isbn
         JOIN editions e
           ON e.isbn = c.isbn
         JOIN books b
           ON b.book_id = e.book_id;

-- This is where your bit goes: define the query as specified in the
-- assignment description


CREATE VIEW sole_python_author(author_first_name, author_last_name)
AS
  SELECT au.first_name,
         au.last_name
  FROM   books bo
         JOIN authors au
           ON au.author_id = bo.author_id
  WHERE  title LIKE '%Python%'
         AND ( (SELECT Count(a.author_id)
                FROM   (SELECT author_id
                        FROM   books
                        WHERE  title LIKE '%Python%'
                        GROUP  BY author_id) a) = 1 )
  GROUP  BY au.first_name,
            au.last_name;

-- This is where your bit goes: define the query as specified in the
-- assignment description


CREATE VIEW no_cat_customers(customer_first_name, customer_last_name)
AS
  SELECT c.first_name,
         c.last_name
  FROM   (SELECT *
          FROM   editions e
                 JOIN books b
                   ON b.book_id = e.book_id
                 JOIN shipments s
                   ON s.isbn = e.isbn
          WHERE  title = 'The Cat in the Hat') cats
         RIGHT JOIN customers c
                 ON c.c_id = cats.c_id
  WHERE  cats.title IS NULL;


-- This is where your bit goes: define the query as specified in the
-- assignment description
