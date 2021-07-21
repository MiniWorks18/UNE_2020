<?php

class Database implements JsonSerializable {
    private $name;
    private $age;
    private $email;
    private $phone;
    private $userID;

    // Set properties
    public function setUserID($user_id) {
        $this->userID = $user_id;
    }
    public function setName($name) {
        $this->name = $name;
    }
    public function setAge($age) {
        $this->age = $age;
    }
    public function setEmail($email) {
        $this->email = $email;
    }
    public function setPhone($phone) {
        $this->phone = $phone;
    }

    // Write class properties to database file
    public function jsonSerialize() {
        $database = fopen("userDatabase.txt", 'a');
        $entry =  json_encode([
            'user_id' => $this->userID,
            'name' => $this->name,
            'age' => $this->age,
            'email' => $this->email,
            'phone' => $this->phone])."\n";
        fwrite($database, $entry);
        fclose($database);

    }
}