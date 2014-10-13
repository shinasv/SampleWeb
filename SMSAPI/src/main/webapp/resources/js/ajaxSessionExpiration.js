function ajaxSessionTimeout()
{
    window.location.reload();
}
 
!function( $ )
{
    $.ajaxSetup({
        statusCode: 
        {
            901: ajaxSessionTimeout
        }
    });
}(window.jQuery);