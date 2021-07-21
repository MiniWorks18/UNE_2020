CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    address VARCHAR(200),
    city VARCHAR(50),
    state VARCHAR(3),
    postcode VARCHAR(8),
    CONSTRAINT chk_state CHECK (
      state IN ('NSW','VIC','QLD','ACT','TAS','NT','SA','WA'))
);

CREATE TABLE movies (
    movie_id INTEGER PRIMARY KEY,
    movie_title VARCHAR(100) NOT NULL,
    director_last_name VARCHAR(50) NOT NULL,
    director_first_name VARCHAR(50) NOT NULL,
    genre VARCHAR(20),
    media_type VARCHAR(20),
    release_date DATE,
    studio_name VARCHAR(50),
    retail_price INTEGER CHECK (retail_price>0),
    current_stock INTEGER CHECK (current_stock>=0),
    CONSTRAINT chk_genre CHECK (
      genre IN ('Action','Adventure','Comedy','Romance','Science Fiction',
                     'Documentary','Drama','Horror')),
    CONSTRAINT chk_media_type CHECK (media_type IN ('DVD','Blu-Ray'))
);

CREATE TABLE shipments (
  shipment_id INTEGER PRIMARY KEY,
  customer_id INTEGER NOT NULL,
  movie_id INTEGER NOT NULL,
  shipment_date DATE,
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
      ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
      ON DELETE RESTRICT ON UPDATE CASCADE
);
