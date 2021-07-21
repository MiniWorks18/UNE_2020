// After DOM loads, add event listener for submit button
window.addEventListener("load", function(e) {
    document.getElementById("quiz").addEventListener("submit", function(e) {
        e.preventDefault();
        // Stops submit button if there are no more questions
        if (!end) {
            checkAnswer();
        }
    })
})


// Question list
var qList;
// Index of question currently on
var current = 0;
// Question id
var qID;
// True if quiz is over
var end = false;
// If the quiz has been displayed
var displayed = false;
// Quiz scoring
var attempted = 0;
var total = 0;
var correct = 0;
var incorrect = 0;


// Fetches a list of question ids for quiz
function getQuestionIDs() {
    $.get("http://turing.une.edu.au/~jbisho23/assignment2/quiz.php", 
          function(list, status) {
            if (status === "success") {
                qList = list;
                total = list.questions.length;
                updateScore();
                nextQuestion();
            } else {
                console.error("Failed to get question ids: "+qList.error);
            }
    });
}


// Make sure there is a next question.
function nextQuestion() {
    if (qList.questions[current] !== undefined) {
        getQuestion(qList.questions[current]);
        current++;
    } else { // No more questions, quiz ended
        end = true;
    }
}


// Request question and choices of a particular ID, pass it to be inserted
function getQuestion(i) {
    $.post("http://turing.une.edu.au/~jbisho23/assignment2/quiz.php",
        {
        q: i
        },
        function(data, status) {
            qID = i;
            insertQuestion(data);
        }
    );
}


// Insert a question into the quiz fieldset
function insertQuestion(d) {
    var quiz = document.getElementById("quiz_question");
    var child = document.getElementById("answers").firstElementChild;
    // Question
    quiz.innerHTML = d.text;
    // Choices
    for (a in d.choices) {
        child.firstElementChild.innerHTML = d.choices[a];
         child = child.nextElementSibling;
     }
    
    if (!displayed) {
        // Display quiz
        $("#registration").addClass("hidden");
        $("#quiz").removeClass("hidden");
        $("#score").removeClass("hidden");
        displayed = true;
    }
}


// Confirm a choice is selected, then check if it's correct or not
function checkAnswer() {
    var ans = document.getElementsByClassName("answer");
    var answer = null;
    // Looping through buttons to find if one is pressed
    for (i = 0; i < ans.length; i++) {
        if (ans[i].checked) {
            ans[i].checked = false;
            answer = ans[i].value;
            break;
        }
    }
    
    // If user entered an answer, check and move the next question
    if (answer !== null) {
        // Checking with server for whether the answer is correct or not
        $.post("http://turing.une.edu.au/~jbisho23/assignment2/quiz.php",
            {
            q: qID,
            a: answer
            },
            function(result, status) {
                if (status === "success") {
                    console.log("Answer correct?: "+result.correct);
                    // Increment score
                    attempted++;
                    if (result.correct) {
                        correct++;
                    } else {
                        incorrect++;
                    }
                    updateScore();
                    nextQuestion();
                } else {
                    console.error("Answer was not properly passed to server");
                }
            }
        );
    } else {
        alert("Please choose an answer");
    }
    
}


// Update scores on sidebar
function updateScore() {
    document.getElementById("attempted").innerHTML = "Attempted: "+attempted +"/"+total;
    document.getElementById("correct").innerHTML = "Correct: "+correct;
    document.getElementById("incorrect").innerHTML = "Incorrect: "+incorrect;
}
