$(document).ready(function () {
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
        localStorage.setItem('activeTab', $(e.target).attr('href'));
    });
    var activeTab = localStorage.getItem('activeTab');
    if (activeTab) {
        $('#myTab a[href="' + activeTab + '"]').tab('show');
    }
});

function logPage() {
    $('#login').modal('show');
}

function signUpPage() {
    $('#login').modal('hide');
    $('#signup').modal('show');
}

function dropdown() {
    $(".dropdown-toggle").dropdown();
}

function getProductByCategory(category) {
    try {
        $("#home-page-products").hide();
        $("#filters").show();
    } catch (e) {

    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            if (products.length == 0) {
                $("#products").text("No product of category " + category + " available");
                return
            }
            var otherSubcategories = [];
            for (var i = 0; i < products.length; i++) {
                txt += "<a onclick='getProductById(this)' class='product d-flex flex-column'><span style='display:none'>" + products[i].productId + "</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5>" + products[i].name + "</h5>" +
                    "<h6>" + products[i].categoryDTO.name + " > " + products[i].subCategoryDTOS[0].name + "</h6>" +
                    "<h5 class='font-weight-bold'>" + products[i].price + "</h5>" +
                    "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "ADD TO CART</button></a>";
                for (var j = 1; j < products[i].subCategoryDTOS.length; j++) {
                    otherSubcategories.push(products[i].subCategoryDTOS[j].name);
                }
            }
            $("#products").html(txt);
            var unique = new Set(otherSubcategories);
            try {
                txt = "";
                for (let item of unique) {

                    txt += "         <div class=\"form-check\">\n" +
                        "                <label class=\"form-check-label\" onclick='getFilteredProducts()'>\n" +
                        "                    <input type=\"checkbox\" class=\"form-check-input other-subcategories-check\" name=\"optradio\"><span>" + item + "</span>\n" +
                        "                </label>\n" +
                        "            </div>";
                }
                $("#other-subcategories").html(txt);
            } catch (e) {

            }
            getFilteredProducts();
        }
    };

    var u = "/products/" + category;
    xhttp.open("GET", u, true);
    xhttp.send();

}

function loadUsername() {
    if (($.cookie('user')) != '' && ($.cookie('user')) != undefined) {
        var user = JSON.parse($.cookie('user'));
        var name = user.name.split(" ");
        document.getElementById("user-name").innerHTML = name[0];
        document.getElementById("sign").style.display = "none";
        document.getElementById("log").style.display = "none";
        document.getElementById("userS").style.display = "inline-block";
        document.getElementById("logout").style.display = "inline-block";
        if (user.email == "admin") {
            $("#order-history").hide();
            $("#cart").hide();
            $("#admin").show();
        }
        return;

    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "") return;
            user = JSON.parse(this.responseText);
            var name = user.name.split(" ");
            document.getElementById("user-name").innerHTML = name[0];
            document.getElementById("sign").style.display = "none";
            document.getElementById("log").style.display = "none";
            document.getElementById("userS").style.display = "inline-block";
            document.getElementById("logout").style.display = "inline-block";
            $.cookie('user', this.responseText);
            if (user.email == "admin") {
                $("#order-history").hide();
                $("#cart").hide();
                $("#admin").show();
            }
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
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                window.location = this.responseURL;
            }
            if (this.status == 403) {
                window.location = "/home";
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
        if ($('#pwd').val() != $('#repwd').val()) {
            alert("password doesn't match");
            return;
        }
        var data = 'email=' + $('#email').val() + '&password=' + btoa($('#pwd').val()) + '&userProfileDTO.name=' + $('#first-name').val() + " " + $('#last-name').val();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                alert("successful sign up");
                window.location = "/home";
            }
            if (this.status == 403) {
                $("#message").text("Email Id already exists.");
                $("#message").fadeIn();
                $("#message").fadeOut(8000);
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
    productId = Obj.parentNode.children[0].innerHTML;
    if (($.cookie('user')) != '' && ($.cookie('user')) != undefined) {
        userId = JSON.parse($.cookie('user')).userId;
    } else {
        window.location = "/login";
    }
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
                    "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "ADD TO CART</button></div>";
            }

            $("#products").html(txt);
            $("#search-content").hide();
            $("#home-page-products").hide();
        }
    };

    var u = "/products/search/" + searchString;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function getSubcategories(Obj) {
    category = "#" + Obj;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            try {
                var subCategories = JSON.parse(this.responseText);
            } catch (e) {
                return;
            }
            var txt = "";
            for (var i = 0; i < subCategories.length; i++) {
                txt += "<a class=\"item\" href=\"#\" onclick='redirectMarked(this),loadMarkedFilters(this)' style='text-decoration: none'>" + subCategories[i].name + "</a>";
            }
            $(category).html(txt);
        }
    };
    var u = "/subCategories/" + Obj;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function getProductById(Obj) {
    console.log("here");
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

function loadHomePageProducts() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            for (var i = 0; i < products.length && i < 5; i++) {
                txt += "<a  class='product d-flex flex-column'><span style='display:none'>" + products[i].productId + "</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5>" + products[i].name + "</h5>" +
                    "<h6>" + products[i].categoryDTO.name + " > " + products[i].subCategoryDTOS[0].name + "</h6>" +
                    "<h5 class='font-weight-bold'>" + products[i].price + "</h5>" +
                    "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "ADD TO CART</button></a>";

            }
            $("#home-electronics-products").html(txt);
        }
    };

    var u = "/products/electronics";
    xhttp.open("GET", u, true);
    xhttp.send();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            for (var i = 0; i < products.length && i < 5; i++) {
                txt += "<a  class='product d-flex flex-column'><span style='display:none'>" + products[i].productId + "</span>" +
                    "<div class='text-center p-2'><i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5>" + products[i].name + "</h5>" +
                    "<h6>" + products[i].categoryDTO.name + " > " + products[i].subCategoryDTOS[0].name + "</h6>" +
                    "<h5 class='font-weight-bold'>" + products[i].price + "</h5>" +
                    "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "ADD TO CART</button></a>";

            }
            $("#home-men-products").html(txt);
        }
    };

    var u = "/products/men";
    xhttp.open("GET", u, true);
    xhttp.send();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var products = JSON.parse(this.responseText);
            var txt = "";
            for (var i = 0; i < products.length && i < 5; i++) {
                txt += "<a  class='product d-flex flex-column'><span style='display:none'>" + products[i].productId + "</span>" +
                    "<div class='text-center p-2'><i  class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i></div>\n" +
                    "<h5>" + products[i].name + "</h5>" +
                    "<h6>" + products[i].categoryDTO.name + " > " + products[i].subCategoryDTOS[0].name + "</h6>" +
                    "<h5 class='font-weight-bold'>" + products[i].price + "</h5>" +
                    "<button type=\"button\" onclick=\"addToCart(this)\" class='add-to-cart-btn'><i class=\"fa fa-plus\" aria-hidden=\"true\"></i>\n" +
                    "ADD TO CART</button></a>";

            }
            $("#home-books-products").html(txt);
        }
    };

    var u = "/products/books";
    xhttp.open("GET", u, true);
    xhttp.send();
}

function loadMarkedFilters(Obj) {
    var category = Obj.parentNode.id;
    loadFilters(category);
    $.cookie('marked-subCategory', Obj.innerHTML);
    getProductByCategory(category);
}

function redirect(category) {
    if (window.location.href.search("products") == -1 && window.location.href.search("home") == -1) {
        $.cookie("redirect-category", category);
        window.location = "/products";
    }
}

function redirectMarked(Obj) {
    if (window.location.href.search("products") == -1 && window.location.href.search("home") == -1) {
        var str = Obj.parentNode.id + " " + Obj.innerHTML
        $.cookie('marked-filter-obj', str);
        console.log(Obj.parentNode.id + Obj.innerHTML);
        window.location = "/products";
    }

}