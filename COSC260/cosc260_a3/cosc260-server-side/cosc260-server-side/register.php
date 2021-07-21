<?php
require_once  __DIR__ .'/class/Database.php';

header('Content-Type: application/json; charset=utf-8');
header('Access-Control-Allow-Origin: *');

$responses = [
    400 => "Bad Request",
    404 => "Not Found",
    405 => "Method Not Allowed",
    500 => "Internal server error"
];

$db = new Database();
$success = true;

// Send errors to the client if there is a problem
function send_error($code, $message) {
    global $responses, $success;
    $success = false;
    header($_SERVER['SERVER_PROTOCOL']." ".$code." - ".$responses[$code]);
    echo json_encode(["error" => $code." - ".$responses[$code].": ".$message]);

}

// Handle a POST request
if ($_SERVER['REQUEST_METHOD'] === "POST") {
    $name = getParameter('name');
    $age = getParameter('age');
    $email = getParameter('email');
    $phone = getParameter('phone');

    // Check if the post data exists
    if (!empty($_POST)) {
        // Name validation
        if ($name === false) {
            send_error(400, "Name not found");
        } else if (strlen($name) < 2 || strlen($name) > 100) {
            send_error(405, "Name must be between 2 and 100 characters long, found: " . strlen($name));
        } else if (preg_match("/[^a-zA-Z' -]/i", $name)) {
            send_error(405, "Name must contain only a-z, A-Z, -, or '");
        }

        // Age validation
        else if ($age === false) {
            send_error(400, "Age not found");
        } else if ($age < 13 || $age > 130) {
            send_error(405, "Age must be between 13 and 130");
        }
        // Check email validation, CREDIT: https://emailregex.com/
        else if ($email === false) {
            send_error(400, "Email not found");
        } else if (!preg_match('/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/', $email)) {
            send_error(405, "Invalid email");
        }
        // Phone validation
        else if ($phone !== false) {
            if (strlen($phone) != 10) {
                send_error(405, "Phone number must be 10 digits long");
            } else if (preg_match('/[^0-9]/i', $phone)) {
                send_error(405, "Phone number can only be digits");
            } else if (!preg_match('/04/', $phone)) {
                send_error(405, "Phone number must begin with 04");
            }
        }
    } else {
        send_error(400, "No data found");
    }
// If GET request is received, return error
} else if ($_SERVER['REQUEST_METHOD'] === "GET") {
    send_error(400, "Please use a POST request");
}

// User ID: If no errors in POST, return a user ID and write to database
if ($success) {
    $id = rand(0,99999);
    echo json_encode(["user_id"=>$id]);
    $db->setUserID($id);
    $db->setName($name);
    $db->setAge($age);
    $db->setEmail($email);
    $db->setPhone($phone);
    $db->jsonSerialize();
}

// Handle fetching parameters from a POST request
function getParameter($p) {
    if (isset($_POST[$p]) && $_POST[$p] != '') {
        return $_POST[$p];
    } else {
        return FALSE;
    }
}