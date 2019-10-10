function logPage() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.response=="true")
                $('#login').modal('show');
        }
    };
    xhttp.open("GET", "/user-home", true);
    xhttp.send();
}
function dropdown(){
    $(".dropdown-toggle").dropdown();
}
