function logout() {
    $.removeCookie('username');
    $.removeCookie('userId');
    $.removeCookie('user');
    window.location.href = "/logout";

}

function loadUsername() {
    if (($.cookie('user')) != '' && ($.cookie('user')) != undefined) {
        var user = JSON.parse($.cookie('user'));
        var name = user.name.split(" ");
        document.getElementById("user-name").innerHTML = name[0];
        document.getElementById("userS").style.display = "inline-block";
        document.getElementById("logout").style.display = "inline-block";
        return;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "") return;
            user = JSON.parse(this.responseText)
            var name = user.name.split(" ");
            document.getElementById("user-name").innerHTML = name[0];
            document.getElementById("userS").style.display = "inline-block";
            document.getElementById("logout").style.display = "inline-block";
            $.cookie('user', this.responseText);
        }
    };
    xhttp.open("GET", "/current-user", true);
    xhttp.send();
}

function getProductByCategory(category) {
    $("#add-product").hide();
    $("#products").show();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            if (products.length == 0) $("#products").text("No product of category " + category + " available")
            for (var i = 0; i < products.length; i++) {
                txt += "<div class='product d-flex flex-column '><span style='display:none'>" + products[i].productId + "</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5 class=''>" + products[i].name + "</h5>" +
                    "<h6>" + products[i].categoryDTO.name + " > " + products[i].subCategoryDTOS[0].name + "</h6>" +
                    "<h5 class='font-weight-bold'>" + products[i].price + "</h5>" +
                    "<button type=\"button\" onclick=\"modifyProduct(this)\" class='modify-product-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "MODIFY PRODUCT</button></div>";
            }
            $("#products").html(txt);
        }
    };

    var u = "/products/" + category;
    xhttp.open("GET", u, true);
    xhttp.send();
}

jQuery(document).ready(function ($) {
    $("#add-product-btn").click(function () {
        getSubcategories()
        $("#product-heading").text("Add New Product");
        $("#product-id").text('');
        $("#product-name").val("");
        $("#product-price").val("");
        $("#product-details").val("");
        $("#product-category").val("");
        $("#product-subcategory").val("");
        $("#sub-categories").html("");
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
        var id = $("#product-id").text();
        var subCategories = [];
        subCategories.push({name: $("#product-subcategory").val()});
        var elements = document.getElementsByClassName("sub-category");

        for (var i = 0; i < elements.length; i++) {
            if (elements[i].value == "")
                continue;
            subCategory = {name: elements[i].value,};
            subCategories.push(subCategory);
        }

        if (id == "") {
            var product = {
                name: $("#product-name").val(),
                price: $("#product-price").val(),
                details: $("#product-details").val(),
                categoryDTO: {
                    name: $("#select-category").val().toLowerCase(),
                },
                subCategoryDTOS: subCategories,
            };
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    $("#message").text("Saved Successfully");
                    $("#message").fadeIn();
                    $("#message").fadeOut(3000);
                    $("#add-product").hide();
                    $("#add-product-btn").show();
                    var xhttp1 = new XMLHttpRequest();
                    xhttp1.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                        }
                    };
                    var u = "/addSubCategory/" + $("#select-category").val().toLowerCase();
                    xhttp1.open("POST", u, true);
                    xhttp1.setRequestHeader("Content-type", "application/json");
                    xhttp1.send(JSON.stringify(subCategories[0]));
                }
            };
            xhttp.open("POST", "/products/add-product", true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(product));

        } else {

            var product = {
                productId: id,
                name: $("#product-name").val(),
                price: $("#product-price").val(),
                details: $("#product-details").val(),
                categoryDTO: {
                    name: $("#select-category").val().toLowerCase(),
                },
                subCategoryDTOS: subCategories,
            };
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    $("#message").text("Saved Successfully");
                    $("#message").fadeIn();
                    $("#message").fadeOut(3000);
                    $("#add-product").hide();
                    var xhttp1 = new XMLHttpRequest();
                    xhttp1.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {

                        }
                    };
                    var u = "/addSubCategory/" + $("#select-category").val().toLowerCase();
                    xhttp1.open("POST", u, true);
                    xhttp1.setRequestHeader("Content-type", "application/json");
                    xhttp1.send(JSON.stringify(subCategories[0]));
                }
            };
            xhttp.open("PUT", "/products/update", true);
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(product));
        }
    })
    $("#add-subcategory-btn").click(function () {
        var txt = "           <div class=\"input-group\" >\n" +
            "                    <input class=\"form-control sub-category\" type=\"text\" placeholder=\"SubCategory\" required>\n" +
            "                    <div class=\"input-group-append\">\n" +
            "                        <a href=\"#\" class=\"btn btn-primary text-white \" onclick=\"removeSubCategory(this)\"><i class=\"fa fa-minus\" aria-hidden=\"true\"></i></a>\n" +
            "                    </div>\n" +
            "                </div>";
        $("#sub-categories").prepend(txt);
    })


});

function redirectModify() {
    if($.cookie('productId')== undefined ||$.cookie('productId')==0)
    {
        return;
    }
    modifyProduct();
}

function modifyProduct(Obj) {
    getSubcategories();
    try {
        productId = Obj.parentNode.children[0].innerHTML;
    } catch (e) {
        productId = $.cookie('productId');
    }
    $.removeCookie('productId');
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var product = JSON.parse(this.responseText);
            $("#product-heading").text("Modify Product");
            $("#product-name").val(product.name);
            $("#product-price").val(product.price);
            $("#product-details").val(product.details);
           // $("#product-category").val(product.categoryDTO.name);
            document.getElementById("select-category").value = product.categoryDTO.name;

            $("#product-subcategory").val(product.subCategoryDTOS[0].name);
            var txt = "";
            for (var i = 1; i < product.subCategoryDTOS.length; i++) {
                txt += "           <div class=\"input-group\" >\n" +
                    "                    <input class=\"form-control sub-category\" type=\"text\" placeholder=\"SubCategory\" value='" + product.subCategoryDTOS[i].name + "' required>\n" +
                    "                    <div class=\"input-group-append\">\n" +
                    "                        <a href=\"#\" class=\"btn btn-primary text-white \" onclick=\"removeSubCategory(this)\"><i class=\"fa fa-minus\" aria-hidden=\"true\"></i></a>\n" +
                    "                    </div>\n" +
                    "                </div>";
            }
            $("#sub-categories").html(txt);
            $("#product-id").text(productId);
            $("#products").hide()
            $("#add-product").show();
        }
    };

    var u = "/products/getById/" + productId;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function removeSubCategory(Obj) {
    var elem = Obj.parentElement.parentElement;
    elem.remove();
}

function getSubcategories() {
    category = $("#select-category").val();
    category = category.toLowerCase();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            try {
                var subCategories = JSON.parse(this.responseText);
            } catch (e) {
                var subCategories = [];
            }

            var txt = "";
            for (var i = 0; i < subCategories.length; i++) {
                txt += "<a class=\"item\" href=\"#\" onclick='fillSubCategory(this)'>" + subCategories[i].name + "</a>";
            }
            $("#main-subcategories").html(txt);
        }
    };
    var u = "/subCategories/" + category;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function fillSubCategory(Obj) {
    $("#product-subcategory").val(Obj.innerHTML);
}

function searchOptions() {
    var searchString = $("#search-string").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            for (var i = 0; i < products.length && i < 10; i++) {
                txt += "<a onclick='getProductById(this)'>" + products[i].name + " <span style='display: none'>" + products[i].productId + "</span><span class='text-muted'>-product</span></a>";
            }
            $("#search-content").html(txt);
            $("#search-content").show();
        }
    };

    var u = "/products/search/" + searchString;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function searchResult() {
    var searchString = $("#search-string").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            if (products.length == 0) $("#products").text("No product of category " + category + " available")
            for (var i = 0; i < products.length; i++) {
                txt += "<div class='product d-flex flex-column'><span style='display:none'>" + products[i].productId + "</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5>" + products[i].name + "</h5>" +
                    "<h6>" + products[i].categoryDTO.name + " > " + products[i].subCategoryDTOS[0].name + "</h6>" +
                    "<h5 class='font-weight-bold'>" + products[i].price + "</h5>" +
                    "<button type=\"button\" onclick=\"modifyProduct(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "MODIFY PRODUCT</button></div>";
            }

            $("#products").html(txt);
            $("#search-content").hide();
        }
    };

    var u = "/products/search/" + searchString;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function getProductById(Obj) {

    productId = Obj.children[0].innerHTML;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            product = JSON.parse(this.responseText);
            $.cookie('current-product', this.responseText);
            window.location = "/productPage";
        }
    };
    var u = "/products/getById/" + productId;
    xhttp.open("GET", u, true);
    xhttp.send();
}