// After DOM loads, add event listener for submit button
window.addEventListener("load", function (e) {
    // Trigger validate function on submit form event
    document.getElementById("registration").addEventListener("submit", validate);
    document.getElementById("registration").addEventListener("reset", clearErrors);
});


// Add errors when they occur
function addError(message) {
    var p = document.createElement("p");
    var text = document.createTextNode(message);
    
    p.appendChild(text);
    document.getElementById("user_message").appendChild(p);
    $('#user_message').addClass('error');
    $('#user_message').removeClass('invisible');
}


// Clear errors
function clearErrors() {
    document.getElementById("user_message").innerHTML = "";
    $('#user_message').addClass('invisible');
    // Toggeable test to bypass registration
    //override();
}


// Form validation
function validate(e) {
    e.preventDefault();
    clearErrors();
    console.log("Submit event cought, checking validity");
    form = document.getElementById("registration");
    error = document.getElementById("user_info");
    
    var success = true;
    var name = form.elements["name"].value;
    var age = form.elements["age"].value;
    var email = form.elements["email"].value;
    var phone = form.elements["phone"].value;
    
    // Check name
    if (name.length === 0 || name.length < 2 || name.length > 100 || !/^[a-zA-Z'-]+$/.test(name)) {
        success = false;
        addError("Invalid name");
    }
    
    // Check age
    if (age.length === 0 || age < 13 || age > 131 || !/^[0-9]+$/.test(age)) {
        success = false;
        addError("Invalid age (must be 13-130)");
    }
    
    // Check email, CREDIT https://emailregex.com/
    if (email.length === 0 || !/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email)) {
        success = false;
        addError("Invalid email");
    }
        
    // Check phone
    if (phone.length > 0 && (phone.length != 10 || !/^[0-9]+$/.test(phone) || !/^04/.test(phone))) {
        success = false;
        addError("Invalid phone number (optional, leave blank)");
    }
    
    if(success) {
        console.log("Validated, calling request");
        register();
    }
  
}  

// Pass registration information to server
function register() {
    form = document.getElementById("registration");
    var uName = form.elements["name"].value;
    var uAge = form.elements["age"].value;
    var uEmail = form.elements["email"].value;
    var uPhone = form.elements["phone"].value;
    
    $.post("http://turing.une.edu.au/~jbisho23/assignment2/register.php",
        {
        name: uName,
        age: uAge,
        email: uEmail,
        phone: uPhone
        },
        function(data, status) {
        
        // Handle AJAX response
        if (status === "success") {
            document.getElementById("user_id").innerHTML = data.user_id;
            getQuestionIDs();
            console.log("Successful registration. User now logged in.");
        } else {
            console.error("Register request failed: "+data.error);
        }
    }
    );
}


// Testing purposes
function override() {
    document.getElementById("user_id").innerHTML = "696969";
    $("#registration").addClass("hidden");
    $("#quiz").removeClass("hidden");
    $("#score").removeClass("hidden");
    getQuestionIDs();
}