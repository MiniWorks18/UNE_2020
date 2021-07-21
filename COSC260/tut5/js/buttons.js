// jQuery onLoad: this will run once the DOM has been loaded
// This is a jQuery shortcut for document.onload()
$(function() {
    /* This will run once the DOM is loaded */
    initButtonListeners();
});

function initButtonListeners() {
    
    // Find all objects with button class
    var $buttons = $('.button.down');
    
    // Loop over each button, accessed using the variable 'this'
    $buttons.each(function() {
        
        // Attach a new function to the 'click' event of this button
        $(this).click(function() {
            console.log(this); // Print the click
            $(this).next().toggle(500);
        })
    })
}
