function loadOrderHistory() {
    var userId = JSON.parse($.cookie('user')).userId;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var orders = JSON.parse(this.responseText);
            var txt = "";
            for (var i = 0; i < orders.length; i++) {
                var total = 0;
                txt += "                <div class=\"order border mb-3\">\n" +
                    "                    <div class=\"bg-light p-3\">\n" +
                    "                        <div class=\"ordre-id d-inline px-4 py-2 shadow\">\n Order Id: " +
                    orders[i].orderId +
                    "                        </div>\n" +
                    "                    </div>\n";
                for (var j = 0; j < orders[i].orderItemDTOS.length; j++) {
                    total += orders[i].orderItemDTOS[j].quantity * orders[i].orderItemDTOS[j].productDTO.price;
                    txt += "              <div class=\"d-flex\">\n" +
                        "                        <div class=\"p-4 text-center\" >\n" +
                        "                            <i class=\"fa fa-dropbox\" aria-hidden=\"true\" style=\"font-size: 7em\"></i>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"d-flex flex-column p-4 w-25\">\n" +
                        "                           <span style=\"display: none\"></span>\n" +
                        "                            <h5 class=\"font-weight-bold\">" + orders[i].orderItemDTOS[j].productDTO.name + "</h5>\n" +
                        "                            <h5 class=\"font-weight-bolder\">Category:" + orders[i].orderItemDTOS[j].productDTO.categoryDTO.name + "</h5>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"p-4 w-25\">\n" +
                        "                            <h5 class=\"font-weight-bolder\">" + orders[i].orderItemDTOS[j].productDTO.price + "</h5>\n" +
                        "                        </div>\n" +
                        "                        <div class=\"p-4\">\n" +
                        "                            <h5>Address</h5>\n" +
                        "                            <p>" + orders[i].address.street + ", " + orders[i].address.city + ", " + orders[i].address.pincode + ", " + orders[i].address.state + "</p>\n" +
                        "                        </div>\n" +
                        "                    </div>\n";

                }
                txt += " <div class=\"border d-flex p-2\">\n" +
                    "                        <div class=\"pr-2 order-total d-inline ml-auto \">Order Total <span class=\"total-price-value\">₹" + total + "</span></div>\n" +
                    "                    </div>\n" +
                    "                </div>";

            }
            $("#orders").html(txt);

        }
    };
    var u = "/order/" + userId + "/getOrders";
    xhttp.open("GET", u, true);
    xhttp.send();

}