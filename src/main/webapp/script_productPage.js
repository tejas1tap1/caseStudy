function loadProduct() {
    product = JSON.parse($.cookie('current-product'));
    $("#productId").text(product.productId);
    $("#product-name").text(product.name);
    $("#product-category").text(product.categoryDTO.name);
    $("#product-details").text(product.details);
    $("#product-price").text("₹" + product.price);
    var txt = "";
    for (var i = 0; i < product.subCategoryDTOS.length; i++) {
        if (i == 5) txt += "<br>";
        txt += "<span class=\"badge badge-secondary mr-2\">" + product.subCategoryDTOS[i].name + "</span>"
    }
    $("#product-subCategories").html(txt);
}

function addToCart() {
    productId = $("#productId").text();
    if (($.cookie('user')) != '' && ($.cookie('user')) != undefined) {
        userId = JSON.parse($.cookie('user')).userId;
    } else window.location = "/login";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            window.location = "/cart";
        }
    };

    var u = "/cart/" + userId + "/add/" + productId;
    xhttp.open("POST", u, true);
    xhttp.send();
}

function checkAdmin() {
    if ($.cookie('user') == undefined || $.cookie('user') == "")
        return;
    var user = JSON.parse($.cookie('user'));
    if (user.email == "admin") {
        $("#button").text("MODIFY");
    }
}

$("#button").click(function () {

})

function redirectProduct() {
    if ($.cookie('user') == undefined || $.cookie('user') == "")
        addToCart();
    var user = JSON.parse($.cookie('user'));
    productId = $("#productId").text();

    if (user.email == "admin") {
        $.cookie('productId', productId);
        window.location = "/adminPage"
    } else addToCart();
}