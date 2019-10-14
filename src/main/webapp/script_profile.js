function getDetails() {
    var userId;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            userId=JSON.parse(this.responseText);

        }
    };
    xhttp.open("GET", "/user-id", false);
    xhttp.send();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var user = JSON.parse(this.responseText);
            document.getElementById("display-name").innerHTML=user.name;
            document.getElementById("first-name").value=user.name;
            document.getElementById("email").value=user.email;
            document.getElementById("mobile-number").value=user.phone;
        }
    };

    var u= "/user-profile/"+userId;
    xhttp.open("GET",u , false);
    xhttp.send();

}