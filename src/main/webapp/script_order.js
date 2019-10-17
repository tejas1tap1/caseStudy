function loadOrderHistory() {
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

            }

        }
    };

    var u= "/cart/"+userId+"/get-cart";
    xhttp.open("GET",u, true);
    xhttp.send();

}