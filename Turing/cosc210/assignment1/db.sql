/* Vender table, information about different venders */
CREATE TABLE vendor (
  vender_id     INTEGER PRIMARY KEY,
  vender_name   VARCHAR(25)
);

/* Component table, what a component is, who makes it. 
   If a vendor is discontinued (deleted) we should not lose
   our records of components we use/used. However, updating
   the vendor_id so we still identify it as the same vendor. */
CREATE TABLE component (
  comp_id       INTEGER PRIMARY KEY,
  name          VARCHAR(25),
  description   VARCHAR(25),
  v_id          INTEGER,
  foreign key (v_id) references vendor(vender_id)
	ON UPDATE CASCADE
);

/* Electroonics Inc, stores how much of what stock we have. 
   Stock_id should not be deleted if a component is deleted,
   but it should be cascaded if component is updated. */
CREATE TABLE electronics_inc (
  stock_id    INTEGER PRIMARY KEY,
  amount      INTEGER NOT NULL,
  foreign key (stock_id) references component(comp_id)
	ON UPDATE CASCADE
);

/* What a product is and it's ID */
CREATE TABLE product (
  name   VARCHAR(25),
  id     INTEGER PRIMARY KEY
);

/* Customer type can be commercial or non-commercial. Phone_num checks
   that if it's a commerical customer, it must be 12-15 digits
   and the number must start with a '+' for world code. */
CREATE TABLE customer (
  customer_id    INTEGER PRIMARY KEY,
  commerical     INTEGER NOT NULL,
  post_code      INTEGER CHECK (commerical = 0),
  phone_num      VARCHAR(15),
  name           VARCHAR(25)
 );

/* Address should be conditional on customer(commerical). 
   If customer_id is deleted, the address will be deleted as well,
   if updated it should cascade the customer_id */
CREATE TABLE address ( 
  id           INTEGER PRIMARY KEY,
  country      VARCHAR(25) NOT NULL,
  state        VARCHAR(25) NOT NULL,
  street       VARCHAR(25) NOT NULL,
  street_num   INTEGER NOT NULL,
  appart_num   INTEGER,
  foreign key (id) references customer(customer_id)
	ON DELETE SET NULL ON UPDATE CASCADE
  );

/* Records of each item purcased and their corresponding order id so that a shopping cart
   remains as a single purchase not a handful of individual purchases.
   If customer ID is deleted orders should not delete order records, same for product_id,
   if the foreign keys are changed, then cascade these updates */
CREATE TABLE orders ( 
  order_id    INTEGER PRIMARY KEY,
  customer_id INTEGER NOT NULL,
  product_id  INTEGER NOT NULL,
  quantity    INTEGER NOT NULL,
  foreign key (customer_id) references customer(customer_id)
	ON UPDATE CASCADE,
  foreign key (product_id) references product(id)
	ON UPDATE CASCADE
);
