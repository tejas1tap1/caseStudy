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
function getProductByCategory(category) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("products").innerHTML=this.responseText;
        }
    };

    var u="/products/"+category;
    xhttp.open("GET", u, true);
    xhttp.send();

}
function loadUsername() {
    console.log("here")
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("username").innerHTML=this.responseText;

            if (this.responseText!="") {
                document.getElementById("sign").style.display = "none";
                document.getElementById("log").style.display = "none";
                document.getElementById("userS").style.display = "inline-block";
                document.getElementById("logout").style.display = "inline-block";
            }
        }
    };
    xhttp.open("GET", "/user-email", true);
    xhttp.send();

}
