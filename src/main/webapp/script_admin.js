function logout() {
    $.removeCookie('username');
    $.removeCookie('userId');
    $.removeCookie('user');
    window.location.href = "/logout";

}
function loadUsername() {
    if($.cookie('username')!='' && $.cookie('username')!=undefined)
    {
        document.getElementById("user-name").innerHTML=$.cookie('username');
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
                document.getElementById("userS").style.display = "inline-block";
                document.getElementById("logout").style.display = "inline-block";
            }
        }
    };
    xhttp.open("GET", "/user-email", true);
    xhttp.send();

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
                    "<p>"+products[i].subCategoryDTO.name+"</p>"+
                    "<button type=\"button\" onclick=\"addToCart(this)\"><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "MODIFY PRODUCT</button></div>";
            }

            $("#products").html(txt);
        }
    };

    var u="/products/"+category;
    xhttp.open("GET", u, true);
    xhttp.send();

}