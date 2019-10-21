function redirectCategoryPage() {
    if ($.cookie('redirect-category') != null) {
        category = $.cookie('redirect-category');
        loadFilters(category);
        getProductByCategory(category);
        $.removeCookie('redirect-category');
    }
}

function redirectMarkedPage() {
    if ($.cookie('marked-filter-obj') != null) {
        var a = $.cookie('marked-filter-obj').split(" ");

        var category = a[0];
        loadFilters(category);
        $.cookie('marked-subCategory', a[1]);
        getProductByCategory(category);
        $.removeCookie('marked-filter-obj');
    }
}

function loadFilters(category) {
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
                txt += "   <div class=\"form-check\">\n" +
                    "                <label class=\"form-check-label\" onclick='getFilteredProducts()'>\n" +
                    "                    <input type=\"radio\" class=\"form-check-input main-subcategories-check\" name=\"optradio\"><span>" + subCategories[i].name + "</span>\n" +
                    "                </label>\n" +
                    "            </div>";
            }
            $("#main-subcategories").html(txt);
            $("#category-name").text(category);
        }
    };
    var u = "/subCategories/" + category;
    xhttp.open("GET", u, true);
    xhttp.send();
}

function getFilteredProducts() {
    let category = $("#category-name").text().toLowerCase();
    var elements = document.getElementsByClassName("main-subcategories-check");
    var checkedElements = [];
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].parentNode.children[1].textContent == $.cookie('marked-subCategory')) {
            elements[i].checked = true;
            $.removeCookie('marked-subCategory');
        }
        if (elements[i].checked == true) {
            checkedElements.push(elements[i].parentNode.children[1].textContent);
        }
    }
    elements = document.getElementsByClassName("other-subcategories-check");
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].checked == true) {
            checkedElements.push(elements[i].parentNode.children[1].textContent);
        }
    }


    var filters = {
        minPrice: $("#min-price").val(),
        maxPrice: $("#max-price").val(),
        subCategories: checkedElements,
    };
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
        }
    };
    var u = "/products/" + category + "/getFilteredProducts";
    xhttp.open("POST", u, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(filters));
}
