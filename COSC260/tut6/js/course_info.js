/* On DOM Load, update the information in the right hand column */
$(function(){
    /* code here runs when DOM is loaded */
    updateTimetable();
    updateStaff();

})


/**
 * Add a <li> list item to the specified HTML element
 *
 * @param {string} selector jQuery selector for the list element, e.g. <ul>, <ol>
 * @param {string} text The text to be inserted
 */
function addListItem(selector, text){
  $(selector).append($("<li>").text(text));
}


/*
 * Perform an AJAX request to get timetable information.
 * Update the timetable information in the right hand column in the callback. 
 */

function updateTimetable(){
    $.ajax({
        url: "http://turing.une.edu.au/~jbisho23/tutorial6/timetable.php",
        dataType: "json",
        success: function(d) {
            d.lectures.forEach(function(e) {addListItem("#lecture_timetable", e)});
            addListItem("#tutorial_timetable", d.tutorials);
            $("#timetable_button").click();
        }
    });
    
}

/*
 * Perform an AJAX request to get information on the teaching staff.
 * Update the teaching staff information in the right hand column in the callback. 
 */

 function updateStaff() {
     $.ajax({
         url: "http://turing.une.edu.au/~jstover/COSC260-2018/tutorial6/staff.php",
         dataType: "json",
         success: function(d) {
             
//             d.coordinator.forEach(function(e) {
//                 addListItem("#coordinator_info", e);
//             });
//             d.lecturer.forEach(function(e) {
//                 addListItem("#lecturer_info", e);
//             });
//             d.tutorials.forEach(function(e) {
//                 addListItem("#tutorials_info", e);
//             });
         }
     });
 }