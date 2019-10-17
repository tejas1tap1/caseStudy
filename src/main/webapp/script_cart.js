function decQuantity(Obj) {
    var q=Obj.parentNode.children[1].value;
    q--;
    Obj.parentNode.children[1].value=q;
    changeQuantity(Obj.parentNode.children[1]);
}
function incQuantity(Obj) {
    var q=Obj.parentNode.children[1].value;
    q++;
    Obj.parentNode.children[1].value=q;
    changeQuantity(Obj.parentNode.children[1]);
}
function loadCartItems() {
    getUserId();
    var userId=$.cookie('userId');
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var cartItems=JSON.parse(this.responseText);
            var txt="";
            var totalPrice=0;
            for(var i=0;i<cartItems.length;i++)
            {
                totalPrice+=cartItems[i].product.price*cartItems[i].quantity;
                txt+="<div class=\"item border\">\n" +
                    "                    <div class=\"d-flex\">\n" +
                    "                        <div class=\"p-4 d-flex flex-column text-center\">\n" +
                    "                            <i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i>\n" +
                    "                            <div class=\"d-flex\">\n" +
                    "                                <button type=\"button\" class=\"minus-quantity rounded-circle\" onclick=\"decQuantity(this)\">-</button>\n" +
                    "                                <input type=\"number\" class=\"quantity rounded mx-2 text-center\" onchange='changeQuantity(this)' value='"+cartItems[i].quantity+"' >\n" +
                    "                                <button type=\"button\" class=\"plus-quantity rounded-circle\" onclick=\"incQuantity(this)\">+</button>\n" +
                    "                            </div>\n" +
                    "                        </div>\n" +
                    "                        <div class=\"d-flex flex-column p-4\" >\n" +
                    "                            <span style=\"display: none\">"+cartItems[i].product.productId+"</span>\n" +
                    "                            <h5 class=\"font-weight-bold\">"+cartItems[i].product.name+"</h5>\n" +
                    "                            <h5 class=\"font-weight-bolder\">Category: "+cartItems[i].product.category.name+"</h5>\n" +
                    "                            <h5 class=\"font-weight-bolder\">₹"+cartItems[i].product.price+"</h5>\n" +
                    "                            <button type=\"button\" onclick='removeProduct(this)' class=\"remove-item mt-auto font-weight-bold border border-none\">Remove</button>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </div>"
            }
            $("#cart-items").html(txt);
            $(".no-of-cartItems").text(cartItems.length);
            $(".total-price").text("₹ "+totalPrice);
        }
    };

    var u= "/cart/"+userId+"/get-cart";
    xhttp.open("GET",u, true);
    xhttp.send();

}
function removeProduct(Obj) {
    var productId=Obj.parentNode.children[0].innerHTML;
    var userId=$.cookie('userId');
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            loadCartItems();
        }
    };
    var u= "/cart/"+userId+"/remove/"+productId;
    xhttp.open("GET",u, true);
    xhttp.send();
}
function changeQuantity(Obj) {
    var q=Obj.value;
    console.log("here");
    console.log(q);
    var productId=Obj.parentNode.parentNode.parentNode.children[1].children[0].innerHTML;
    var userId=$.cookie('userId');
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            loadCartItems();
        }
    };
    var u= "/cart/"+userId+"/changeQuantity/"+productId;
    xhttp.open("PUT",u, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(q));
}