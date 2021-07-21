$(function(){
  
  setInterval("timeHandler()", 1000);
  });

function timeHandler() {
    $.ajax({
        url: "http://turing.une.edu.au/~jbisho23/time/time.php",
        dataType: "json",
        success: function(data) {
            $("footer>p:last").html("Date: "+data.date+". Time: "+data.time);
        }
    })
}
  