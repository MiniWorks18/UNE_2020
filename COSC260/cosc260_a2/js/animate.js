// Wait until DOM has loaded
$(function() {
    setInterval("fadeImages()", 6000);
}); 


// Called every x seconds to trigger the next slide
function fadeImages() {
    
    // Current slide and next slide to transition to
    var $current = $('#img_animation img:not(.hidden)');
    var $next = $current.next();
    
    // Revert to top image if there are no more slides
    if ($next.length === 0) { 
        $next = $('#img_animation img:first()');
    } 
    
    // Transition
    $current.fadeTo(1500, 0.0, function() {
        $current.addClass('hidden');
    });
    $next.fadeTo(1500, 1.0, function() {
        $next.removeClass('hidden'); 
    }); 
} 