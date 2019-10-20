function loadProduct() {
    product=JSON.parse($.cookie('current-product'));
    $("#product-id").text(product.productId);
    $("#product-name").text(product.name);
    $("#product-category").text(product.categoryDTO.name);
    $("#product-details").text(product.details);
    $("#product-price").text("₹"+product.price);
    var txt="";
    for(var i=0;i<product.subCategoryDTOS.length;i++)
    {
        if(i==5)txt+="<br>";
        txt+="<span class=\"badge badge-secondary mr-2\">"+product.subCategoryDTOS[i].name+"</span>"
    }
    $("#product-subCategories").html(txt);
}
function addToCart() {
    productId=$("#product-id").innerHTML;
    if( ($.cookie('user'))!='' &&  ($.cookie('user'))!=undefined)
    {
        userId=JSON.parse($.cookie('user')).userId;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            window.location="/cart";
        }
    };

    var u="/cart/"+userId+"/add/"+productId;
    xhttp.open("POST", u, true);
    xhttp.send();

}