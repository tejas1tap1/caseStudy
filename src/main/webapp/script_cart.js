function decQuantity(Obj) {
    var q=Obj.parentNode.children[1].value;
    q--;
    if(q<=0)
    {
        Obj.style.backgroundColor="white";
        return;
    }
    Obj.parentNode.children[1].value=q;
    changeQuantity(Obj.parentNode.children[1]);
}
function incQuantity(Obj) {
    var q=Obj.parentNode.children[1].value;
    q++;
    Obj.parentNode.children[1].value=q;
    changeQuantity(Obj.parentNode.children[1]);
}
function loadAddresses() {
    var user = JSON.parse($.cookie('user'));
   var txt="";
   var addresses=user.addresses;
   if(addresses.length==0)return;
   for(var i=0;i<user.addresses.length;i++)
   {
       txt+="<option value='"+addresses[i].addressId+"'>"+addresses[i].street+", "+
           addresses[i].city+", "+addresses[i].pincode+", "+addresses[i].state+"</option>";
   }
   $("#addresses").html(txt);
}
function loadCartItems() {
    var userId=JSON.parse($.cookie('user')).userId;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        var totalPrice=0;
        if (this.readyState == 4 && this.status == 200) {
            var cartItems=JSON.parse(this.responseText);
            var txt="";

            for(var i=0;i<cartItems.length;i++)
            {
                totalPrice+=cartItems[i].product.price*cartItems[i].quantity;
                txt+="<div class=\"item border\">\n" +
                    "                    <div class=\"d-flex\">\n" +
                    "                        <div class=\"p-4 d-flex flex-column text-center\">\n" +
                    "                            <i class=\"fa fa-dropbox \" style=\"font-size: 7em\" aria-hidden=\"true\"></i>\n" +
                    "                            <div class=\"d-flex\">\n" +
                    "                                <button type=\"button\" class=\"minus-quantity rounded-circle shadow\" onclick=\"decQuantity(this)\">-</button>\n" +
                    "                                <input type=\"number\" min='1' class=\"quantity rounded mx-2 text-center\" oninput=\"validity.valid||(value='');\" onchange='changeQuantity(this)' value='"+cartItems[i].quantity+"' >\n" +
                    "                                <button type=\"button\" class=\"plus-quantity rounded-circle shadow\" onclick=\"incQuantity(this)\">+</button>\n" +
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
        var txt="";
        if(this.status==204)
        {
            txt+="<h1 class=\"item border text-center font-weight-bold text-primary\">\n" +
                "Cart is Empty"+
                "  </h1>";
            $("#cart-items").html(txt);
            $(".total-price").text("₹ "+totalPrice);
            $("place-order").hide();
        }
    };

    var u= "/cart/"+userId+"/get-cart";
    xhttp.open("GET",u, true);
    xhttp.send();

}
function removeProduct(Obj) {
    var productId=Obj.parentNode.children[0].innerHTML;
    var userId=JSON.parse($.cookie('user')).userId;
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
    var productId=Obj.parentNode.parentNode.parentNode.children[1].children[0].innerHTML;
    var userId=JSON.parse($.cookie('user')).userId;
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
function placeOrder() {
    var addressId=$("select.address-option").children("option:selected").val();
    var userId=JSON.parse($.cookie('user')).userId;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           window.location="/orderHistory"
        }
        if(this.status==204 && this.readyState==4)
        {
            alert("Your cart is empty");
        }
    };
    var u= "/order/"+userId+"/createOrder";
    xhttp.open("POST",u, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(addressId));
}