jQuery(document).ready(function ($) {
    $("#sign").click(function () {
        $('#login').hide();
        $('#signup').show();
    })
    $("#log").click(function () {
        $('#signup').hide();
        $('#login').show();
    })
});
