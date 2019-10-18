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
    $("#products").show();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var products= JSON.parse(this.responseText);
            console.log(this.responseText);
            var txt="";
            if(products.length==0)$("#products").text("No product of category "+category+" available")
            for(var i=0;i<products.length;i++)
            {
                txt+="<div class='product d-flex flex-column '><span style='display:none'>"+products[i].productId+"</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5 class=''>"+products[i].name+"</h5>"+
                    "<h6>"+products[i].categoryDTO.name+" > "+products[i].subCategoryDTOS[0].name+"</h6>"+
                    "<h5 class='font-weight-bold'>"+products[i].price+"</h5>"+
                    "<button type=\"button\" onclick=\"modifyProduct(this)\" class='modify-product-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "MODIFY PRODUCT</button></div>";
            }
            $("#products").html(txt);
        }
    };

    var u="/products/"+category;
    xhttp.open("GET", u, true);
    xhttp.send();
}

jQuery(document).ready(function ($) {
    $("#add-product-btn").click(function () {
        $("#product-heading").text("Add New Product");
        $("#product-id").text('');
        $("#product-name").val("");
        $("#product-price").val("");
        $("#product-details").val("");
        $("#product-category").val("");
        $("#product-subcategory").val("");
        $("#products").hide()
        $("#add-product").show();
        $("#add-product-btn").hide();
    })
    $("#hide-add-product").click(function () {
        $("#add-product").hide();
        $("#add-product-btn").show();
    })
    $("#product-save-btn").click(function (event) {
        event.preventDefault();
        var id=$("#product-id").text();
        if(id=="") {
            var product = {
                name: $("#product-name").val(),
                price: $("#product-price").val(),
                details: $("#product-details").val(),
                categoryDTO:{
                    name:$("#product-category").val()},
                subCategoryDTOS:[{
                    name:$("#product-subcategory").val()}],
            };
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    $("#message").text("Saved Successfully");
                    $("#message").fadeIn();
                    $("#message").fadeOut(3000);
                    $("#add-product").hide();
                    $("#add-product-btn").show();
                }
            };
            xhttp.open("POST","/products/add-product", true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(product));
        } else {
            var product = {
                productId: id,
                name: $("#product-name").val(),
                price: $("#product-price").val(),
                details: $("#product-details").val(),
                categoryDTO: {
                    name: $("#product-category").val()
                },
                subCategoryDTOS: [{
                    name: $("#product-subcategory").val()
                }],
            };
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    $("#message").text("Saved Successfully");
                    $("#message").fadeIn();
                    $("#message").fadeOut(3000);
                    $("#add-product").hide();
                }
            };
            xhttp.open("PUT","/products/update", true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(product));
        }
    })

});
function modifyProduct(Obj) {
    productId=Obj.parentNode.children[0].innerHTML;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var product= JSON.parse(this.responseText);
            $("#product-heading").text("Modify Product");
            $("#product-name").attr('value',product.name);
            $("#product-price").attr('value',product.price);
            $("#product-details").text(product.details);
            $("#product-category").attr('value',product.categoryDTO.name);
            $("#product-subcategory").attr('value',product.subCategoryDTO[0].name);
            $("#product-id").text(productId);
            $("#products").hide()
            $("#add-product").show();
        }
    };

    var u="/products/getById/"+productId;
    xhttp.open("GET", u, true);
    xhttp.send();

}