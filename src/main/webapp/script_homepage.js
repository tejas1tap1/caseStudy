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
                     txt+="<div class='product d-flex flex-column'><span style='display:none'>"+products[i].productId+"</span>" +
                            "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                            "<h5>"+products[i].name+"</h5>"+
                            "<h6>"+products[i].categoryDTO.name+" > "+products[i].subCategoryDTOS[0].name+"</h6>"+
                            "<h5 class='font-weight-bold'>"+products[i].price+"</h5>"+
                            "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                         "ADD TO CART</button></div>";
            }
            
            $("#products").html(txt);
        }
    };

    var u="/products/"+category;
    xhttp.open("GET", u, true);
    xhttp.send();

}
function loadUsername() {
    if( ($.cookie('user'))!='' &&  ($.cookie('user'))!=undefined)
    {
        var user = JSON.parse($.cookie('user'));
        var name=user.name.split(" ");
        document.getElementById("user-name").innerHTML=name[0];
        document.getElementById("sign").style.display = "none";
        document.getElementById("log").style.display = "none";
        document.getElementById("userS").style.display = "inline-block";
        document.getElementById("logout").style.display = "inline-block";
        return;

    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           if(this.responseText=="") return;
           user = JSON.parse(this.responseText)
           var name=user.name.split(" ");
           document.getElementById("user-name").innerHTML=name[0];
            document.getElementById("sign").style.display = "none";
            document.getElementById("log").style.display = "none";
            document.getElementById("userS").style.display = "inline-block";
            document.getElementById("logout").style.display = "inline-block";
            $.cookie('user',this.responseText);
        }
    };
    xhttp.open("GET", "/current-user", true);
    xhttp.send();

}
jQuery(document).ready(function ($) {
    $('#loginform').submit(function (event) {
        event.preventDefault();
        var data = 'username=' + $('#username').val() + '&password=' + btoa($('#password').val());
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                window.location=this.responseURL;
            }
        };
        xhttp.open("POST", "/login", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(data);
    });
});
jQuery(document).ready(function ($) {
    $('#signupform').submit(function (event) {
        event.preventDefault();
        var data = 'email=' + $('#email').val() + '&password=' + btoa($('#pwd').val()) + '&userProfileDTO.name='+ $('#name').val();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                alert("successful sign up");
                window.location="/home";
            }
        };
        xhttp.open("POST", "/add-user", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send(data);
    });

});
function logout() {
    $.removeCookie('username');
    $.removeCookie('userId');
    $.removeCookie('user');
    window.location.href = "/logout";

}
function addToCart(Obj) {
    productId=Obj.parentNode.children[0].innerHTML;
    if( ($.cookie('user'))!='' &&  ($.cookie('user'))!=undefined)
    {
    userId=JSON.parse($.cookie('user')).userId;
    }
    else
    {
        window.location="/login";
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        window.location="/cart";
        }
    };

    var u="/cart/"+userId+"/add/"+productId;
    xhttp.open("POST", u, true);
    xhttp.send();

}
// function getUserId() {
//     var userId;
//     if($.cookie('userId')!='' && $.cookie('userId')!=undefined)
//     {
//         return $.cookie('userId');
//     }
//     var xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         if (this.readyState == 4 && this.status == 200) {
//             userId=JSON.parse(this.responseText);
//             $.cookie('userId',userId);
//
//         }
//     };
//     xhttp.open("GET", "/user-id", false);
//     xhttp.send();
//     return userId;
//
// }
function searchOptions() {
    var searchString=$("#search-string").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var products= JSON.parse(this.responseText);
            var txt="";
            for(var i=0;i<products.length && i<10;i++)
            {
                txt+="<a>"+products[i].name+" <span class='text-muted'>-product</span></a>";
            }
            $("#search-content").html(txt);
            $("#search-content").show();
        }
    };

    var u="/products/search/"+searchString;
    xhttp.open("GET", u, true);
    xhttp.send();
}
function searchResult() {
    console.log("here");
    var searchString=$("#search-string").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var products= JSON.parse(this.responseText);
            var txt="";
            if(products.length==0)$("#products").text("No product of category "+category+" available")
            for(var i=0;i<products.length;i++)
            {
                txt+="<div class='product d-flex flex-column'><span style='display:none'>"+products[i].productId+"</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5>"+products[i].name+"</h5>"+
                    "<h6>"+products[i].categoryDTO.name+" > "+products[i].subCategoryDTOS[0].name+"</h6>"+
                    "<h5 class='font-weight-bold'>"+products[i].price+"</h5>"+
                    "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "ADD TO CART</button></div>";
            }

            $("#products").html(txt);
            $("#search-content").hide();
        }
    };

    var u="/products/search/"+searchString;
    xhttp.open("GET", u, true);
    xhttp.send();

}