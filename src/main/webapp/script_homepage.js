function logPage() {
    $('#login').modal('show');
}

function dropdown(){
    $(".dropdown-toggle").dropdown();
}
function getProductByCategory(category) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var products= JSON.parse(this.responseText);
            var txt="";
            if(products.length==0)$("#products").text("No product of category "+category+" available")
            for(var i=0;i<products.length;i++)
            {
                     txt+="<div class='product'><span style='display:none'>"+products[i].productId+"</span>" +
                            "<h>"+products[i].name+"</h><br>"+
                            "<p>"+products[i].price+"</p>"+
                            "<p>"+products[i].details+"</p>"+
                            "<p>"+products[i].categoryDTO.name+"</p>"+
                            "<p>"+products[i].subCategoryDTO.name+"</p></div>";

            }
            
            $("#products").html(txt);
        }
    };

    var u="/products/"+category;
    xhttp.open("GET", u, true);
    xhttp.send();

}
function loadUsername() {
    if($.cookie('username')!='' && $.cookie('username')!=undefined)
    {
        document.getElementById("user-name").innerHTML=$.cookie('username');
        document.getElementById("sign").style.display = "none";
        document.getElementById("log").style.display = "none";
        document.getElementById("userS").style.display = "inline-block";
        document.getElementById("logout").style.display = "inline-block";
        return;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var name=this.responseText.split(" ");
            document.getElementById("user-name").innerHTML=name[0];
            $.cookie('username',name[0]);
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
jQuery(document).ready(function ($) {
    $('#loginform').submit(function (event) {
        event.preventDefault();
        var data = 'username=' + $('#username').val() + '&password=' + btoa($('#password').val());
        $.ajax({
            data: data,
            timeout: 1000,
            type: 'POST',
            url: '/login'
        }).done(function(data, textStatus, jqXHR) {
            window.location = window.location;
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert('Booh! Wrong credentials, try again!');
        });
    });
});
jQuery(document).ready(function ($) {
    $('#signupform').submit(function (event) {
        event.preventDefault();
        var data = 'email=' + $('#email').val() + '&password=' + btoa($('#pwd').val()) + '&userProfileDTO.name='+ $('#name').val();
        $.ajax({
            data: data,
            timeout: 1000,
            type: 'POST',
            url: '/add-user'
        }).done(function(data, textStatus, jqXHR) {
            alert("successful sign up");
            window.location=window.location;
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert('Booh! Wrong credentials, try again!');
        });
    });
});
function logout() {
    $.removeCookie('username');
    $.removeCookie('userId');
    $.removeCookie('user');
    window.location.href = "/logout";

}