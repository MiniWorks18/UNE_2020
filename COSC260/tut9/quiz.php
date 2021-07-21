<?php

    // Tutorial 9 code
require "class/Question.php";


spl_autoload_register(function($class_name){
	require_once __DIR__ . "/class/" . $class_name . ".php";
});


$questions = array(
	new Question(97513,
		"Salamander belongs to which class?",
		["Aves", "Reptiles", "Pisces", "Amphibian"],
		"D"
	),
	new Question(39546,
		"Which class has the largest number of animals?",
		["Mammals", "Fishes", "Insects", "Reptiles"],
		"C"
	)
);


function getParameter($k) {
	if (isset($_GET) && isset($_GET[$k])) {
		return $_GET[$k];
	} elseif (isset($_POST) && isset($_POST[$k])) {
		return $_POST[$k];
	} else {
		return NULL;
	}
}

$q = getParameter("q");
$a = getParameter("a");

function getQuestionByID($question_list, $qid) {
	for ($i=0; $i<count($question_list); $i++){
		if ($question_list[i] ->id == $qid) {
			return $question_list[$i];
		}
	}
	return NULL;
}


$response = []; // Data to be returned to client
$errors = []; // Track any errors that occurred
if ($q === NULL && $a === NULL){
 // No parameters given: return a list of random question ID's
}
elseif ($a == NULL){
 // Only q= provided, return question data or error if invalid ID
}
elseif ($q == NULL){
 // Only a= provided, invalid parameter combination, return error
}
else {
 // Both q and a provided, respond with answer correctness
}

?>
