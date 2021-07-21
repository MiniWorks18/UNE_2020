CREATE FUNCTION hello() RETURNS varchar AS $$

BEGIN
  RETURN 'Hello,World';
END
$$ LANGUAGE plpgsql;


CREATE FUNCTION add_func() RETURNS integer AS $$
DECLARE
    value_1 integer := 30;
    value_2 integer := 25;
    value_3 integer := 0;
BEGIN
    value_1 := 10;
    value_2 := 5;
    value_3 := value_1 + value_2;

    RETURN value_3;
END;
$$ LANGUAGE plpgsql;


CREATE FUNCTION get_author (integer) RETURNS text AS $$
  DECLARE
    auth_id ALIAS FOR $1;
    found_author authors%ROWTYPE;
  BEGIN
    SELECT INTO found_author * FROM authors WHERE author_id = auth_id;
    RETURN found_author.first_name || ' ' || found_author.last_name;
  END;
  $$ LANGUAGE 'plpgsql';
