function getDetails() {
    var userId= getUserId();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var user = JSON.parse(this.responseText);
            document.getElementById("display-name").innerHTML=user.name;
            document.getElementById("first-name").value=user.name;
            document.getElementById("emailId").value=user.email;
            document.getElementById("mobile-number").value=user.phone;
            var addresses=JSON.parse(user.addresses);
            var txt="";
            for(var i=0;i<addresses.length;i++)
            {
                txt+="<div class='address'><span style='display:none'>"+addresses[i].addressId+"</span>" +
                    "<h> Address "+ 1 +"</h><br>"+
                    "<p>"+addresses[i].street+", "+ addresses[i].city+"</p>"+
                    "<p>"+addresses[i].state+" - "+addresses[i].pincode+"</p>"+
                    "</div>";
            }
            $("#addresses").html(txt);
        }
    };

    var u= "/user-profile/"+userId;
    xhttp.open("GET",u , false);
    xhttp.send();
}
function updatedetails() {


}
function getUserId() {
    var userId;
    if($.cookie('userId')!='' && $.cookie('userId')!=undefined)
    {
        console.log($.cookie('userId'));
        return $.cookie('userId');
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            userId=JSON.parse(this.responseText);
            $.cookie('userId');

        }
    };
    xhttp.open("GET", "/user-id", false);
    xhttp.send();
    return userId;

}