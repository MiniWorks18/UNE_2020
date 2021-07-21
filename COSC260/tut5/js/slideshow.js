// jQuery onLoad function
$(function(){
  // run the changeImage function every 5000 ms
  setInterval("fadeElements('#slide_info div.active', '#slide_info div:first')", 5000);
  setInterval("fadeElements('#slide_img img.active', '#slide_img img:first')", 5000);
});

// current = which child is currently displayed, first = which is first in line
function fadeElements(current, first) {
    // Fetch currently displayed element
    var $current = $(current);
    // Fetch next element to display
    var $next = $current.next();
    
    // Ensure next element exists, if not, revert to first element
    if ($next.length === 0) {
        $next = $(first);
    }
    
    // Fade current to 0.0 oppacity, then remove from active class
    $current.fadeTo(1000, 0.0, function() {
        $current.removeClass('active');
    });
    
    // Fade next to 1.0 oppacity, then add to active class
    $next.fadeTo(1000, 1.0, function() {
        $next.addClass('active');
    });
}

