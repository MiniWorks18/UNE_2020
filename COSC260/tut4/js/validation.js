// Window onLoad listener
window.addEventListener("load", function (e) {
    // Wait until DOM is loaded
    document.getElementById("myform").addEventListener("submit", validate);
});


// Add an error message to the #errors element
function addError(message) {
    var p = document.createElement("p");
    var text = document.createTextNode(message);
    p.appendChild(text);
    document.getElementById("errors").appendChild(p);
}


// Clear all error messages from the #errors element
function clearErrors(){
    document.getElementById("errors").innerHTML = "";

}


// Validate the form, returning true if valid, false otherwise
function validate(e) {
    var success = true;
    form = document.getElementById("myform");
    e.preventDefault();
    clearErrors();
    
    var firstname = form.elements["first"].value;
    var lastname = form.elements["last"].value;
    var email = form.elements["email"].value;
    var phone = form.elements["mobile"].value;
    
    
    var type = form.elements["type"].value;
    var onCampus = document.getElementById("mode_on");
    var offCampus = document.getElementById("mode_off");

    // Check not null
    if (firstname.length === 0 || lastname.length === 0 || email.length === 0 || phone.length === 0) {
        success = false;
        addError("All values must be entered!")
    } else
    
    
    // Check alphabetical name
    if (!/^[a-zA-Z'-]+$/.test(firstname) || !/^[a-zA-Z]+$/.test(lastname)) {
        success = false;
        addError("Name must be alphabetical only.");
    }
    
    // Check valid UNE email
    if (!/^[a-zA-Z0-9]+@(une|myune)[.]edu[.]au/.test(email)) {
        success = false;
        addError("Email must be a UNE email!")
    }
    
    // Check valid phone number
    if (!/^[0-9]+$/.test(phone) || !/^04/) {
        success = false;
        addError("Phone number must be Australian!")
    }
    
    // Check non-null degree
    if (type === "none") {
        success = false;
        addError("You must select a degree!");
    }
    
    // Check study mode selected
    if (!onCampus.checked && !offCampus.checked) {
        success = false;
        addError("You must select study mode!");
    }
    
    

    if (success) {
        alert("The information you provided is valid.");
    }
    return success;
}
