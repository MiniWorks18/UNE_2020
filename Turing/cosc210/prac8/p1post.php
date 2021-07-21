<html>

<head>
    <title>Simple Database Access</title>
</head>

<body>
    <?php

    $pw = "/220196038/";
    $user = "/tmcdon26/_apps";
    $db = "/tmcdon26_apps_prac8/";

    $conn_string = "host=127.0.0.1 port=5432 dbname=".$db." user=".$user." password=".$pw;
    $dbconn = pg_connect($conn_string);
    //connect to a database named "test" on the host "sheep" with a username and password


    // Performing SQL query
    $query = 'SELECT ssn FROM employee';
    $result = pg_query($query) or die('Query failed: ' . pg_last_error());

    ?>
        <h4>Employee Details for:</h4>
        <form method="post" action="p1.php">
        <select name="ssn">

    <?php
        while ($line = pg_fetch_array($result, null, PGSQL_ASSOC)) {
            foreach ($line as $col_value) {
            echo "\t\t<option>$col_value</option>\n";
            }

         }

    ?>

    </select>
    <input type="submit" value="Get Employee Details">
    </form>
</body>

</html>
