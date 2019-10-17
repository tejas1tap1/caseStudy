function getDetails() {
    var userId= getUserId();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var user = JSON.parse(this.responseText);
            $.cookie('user',this.responseText);
            var name =user.name.split(" ");
            document.getElementById("display-name").innerHTML=name[0];
            document.getElementById("first-name").value=name[0];
            document.getElementById("last-name").value=name[1];
            document.getElementById("emailId").value=user.email;
            document.getElementById("mobile-number").value=user.phone;
            var addresses=user.addresses;
            var txt="";
            for(var i=0;i<addresses.length;i++)
            {
                txt+="<div class='address'><div class=\"address-option\">\n" +
                    "    <i class=\"fa fa-cog address-option-btn\" aria-hidden=\"true\"></i>\n" +
                    "    <div class='bg-white text-center address-options'>\n" +
                    "        <div class=\"edit-address border border-bottom-0\" onclick='editAddress(this)'>\n" +
                    "            Edit\n" +"<span style='display: none'>"+i+"</span>"+
                    "        </div >\n" +
                    "        <div class=\"delete-address border\" onclick='deleteAddress(this)'>\n" +
                    "            Delete\n" +"<span style='display: none'>"+i+"</span>"+
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div><span style='display:none'>"+
                    addresses[i].addressId+"</span>" +
                    "<h> Address "+ (i+1) +"</h><br>"+
                    "<p>"+addresses[i].street+", "+ addresses[i].city+"</p>"+
                    "<p>"+addresses[i].state+" - "+addresses[i].pincode+"</p>"+
                    "</div>";
            }
            $("#addresses").html(txt);
        }
    };

    var u= "/user-profile/"+userId;
    xhttp.open("GET",u , true);
    xhttp.send();
}
function deleteAddress(Obj)
{
    var id=Obj.children[0].innerHTML;
    var user = JSON.parse($.cookie('user'));
    user.addresses.splice(id,1);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            $("#message-address").text("Deleted Successfully");
            $("#message-address").fadeIn();
            $("#message-address").fadeOut(3000);

            //Obj.parentNode.parentNode.parentNode.parentNode.removeChild(Obj.parentNode.parentNode.parentNode);
            getDetails();
        }

    };
    xhttp.open("PUT","/user-profile/update", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(user));
}
function editAddress(Obj)
{
    var id=Obj.children[0].innerHTML;
    var user = JSON.parse($.cookie('user'));
    $("#address-heading").text("Edit Address");
    $("#street").attr('value',user.addresses[id].street);
    $("#city").attr('value',user.addresses[id].city);
    $("#pincode").attr('value',user.addresses[id].pincode);
    $("#state").attr('value',user.addresses[id].state);
    $("#address-id").text(id);
    $("#add-address").show();

}
jQuery(document).ready(function ($){
    $("#get-orders").click(function () {
        window.location="/orderHistory";
    })
    $("#add-address-btn").click(function(){
        console.log("here");
        $("#address-heading").text("Add New Address");
        $("#address-id").text('');
        $("#street").attr('value','');
        $("#city").attr('value','');
        $("#pincode").attr('value','');
        $("#state").attr('value','');
        $("#add-address").show();
        $("#add-address-btn").hide();
    });
    $("#hide-add-address").click(function () {
        $("#add-address").hide();
        $("#add-address-btn").show();
    })
    $("#manage-addresses-btn").click(function () {
        $("#manage-addresses-btn").css("color","dodgerblue");
        $("#personal-info-btn").css("color","black");
        $("#personal-info-bar").hide();
        $("#manage-addresses-bar").show();
    })
    $("#personal-info-btn").click(function () {
        $("#personal-info-btn").css("color","dodgerblue");
        $("#manage-addresses-btn").css("color","black");
        $("#personal-info-bar").show();
        $("#manage-addresses-bar").hide();
        $("#add-address").hide();
        $("#add-address-btn").show();

    })
    $("#edit-personal-info").click(function(){
        if ($("#edit-personal-info").text()=="Edit") {
            $("#edit-personal-info").text("Cancel");
            $("#name-save-btn").show();
            $("#first-name").removeAttr("readonly");
            $("#last-name").removeAttr("readonly");
            return;
        }
        if ($("#edit-personal-info").text()=="Cancel") {
            $("#edit-personal-info").text("Edit");
            $("#name-save-btn").hide();
            $("#first-name").attr('readonly', true);
            $("#last-name").attr('readonly', true);
            return;
        }
    });
    $("#edit-email").click(function(){

        if ($("#edit-email").text()=="Edit") {
            $("#edit-email").text("Cancel");
            $("#email-save-btn").show();
            $("#emailId").removeAttr("readonly");
            return;
        }
        if ($("#edit-email").text()=="Cancel") {
            $("#edit-email").text("Edit");
            $("#email-save-btn").hide();
            $("#emailId").attr('readonly', true);
            return;
        }
    });
    $("#edit-mobile-number").click(function(){
        if ($("#edit-mobile-number").text()=="Edit") {
            $("#edit-mobile-number").text("Cancel");
            $("#phone-save-btn").show();
            $("#mobile-number").removeAttr("readonly");
            return;
        }
        if ($("#edit-mobile-number").text()=="Cancel") {
            $("#edit-mobile-number").text("Edit");
            $("#phone-save-btn").hide();
            $("#mobile-number").attr('readonly', true);
            return;
        }
    });
    $("#name-save-btn").click(function (event) {
        event.preventDefault();
      var user = JSON.parse($.cookie('user'));
      user.name=$("#first-name").val()+" "+$("#last-name").val();
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
             $("#message").text(this.responseText);
                $("#message").fadeIn();
                $("#message").fadeOut(3000);
                $("#edit-personal-info").text("Edit");
                $("#name-save-btn").hide();
                $("#first-name").attr('readonly', true);
                $("#last-name").attr('readonly', true);
                $.cookie('user',user);
                getDetails();
            }

      };
      xhttp.open("PUT","/user-profile/update", true);
      xhttp.setRequestHeader("Content-type", "application/json");
      xhttp.send(JSON.stringify(user));
    })
    $("#email-save-btn").click(function (event) {
        event.preventDefault();
        var user = JSON.parse($.cookie('user'));
        user.email=$("#emailId").val();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                $("#message").text(this.responseText);
                $("#message").fadeIn();
                $("#message").fadeOut(3000);
                $("#edit-email").text("Edit");
                $("#email-save-btn").hide();
                $("#emailId").attr('readonly', true);
                $.cookie('user',user);
                getDetails();
            }

        };
        xhttp.open("PUT","/user-profile/update", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(user));
    })
    $("#phone-save-btn").click(function (event) {
        event.preventDefault();
        var user = JSON.parse($.cookie('user'));
        user.phone=$("#mobile-number").val();
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                $("#message").text(this.responseText);
                $("#message").fadeIn();
                $("#message").fadeOut(3000);
                $("#edit-mobile-number").text("Edit");
                $("#phone-save-btn").hide();
                $("#mobile-number").attr('readonly', true);
                $.cookie('user',user);
                getDetails();
            }

        };
        xhttp.open("PUT","/user-profile/update", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(user));
    })
    $("#address-save-btn").click(function (event) {
        event.preventDefault();
        var user = JSON.parse($.cookie('user'));
        var id=$("#address-id").text();
        if(id=="") {
            var address = {
                street: $("#street").val(),
                city: $("#city").val(),
                pincode: $("#pincode").val(),
                state: $("#state").val(),
            };
            user.addresses.push(address);
        }
       else{user.addresses[id].street= $("#street").val();
            user.addresses[id].city= $("#city").val();
            user.addresses[id].pincode= $("#pincode").val();
            user.addresses[id].state= $("#state").val();
       }
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                $("#message-address").text("Saved Successfully");
                $("#message-address").fadeIn();
                $("#message-address").fadeOut(3000);
                $("#add-address").hide();
                $("#add-address-btn").show();
                $.cookie('user',user);
                getDetails();
            }
        };
        xhttp.open("PUT","/user-profile/update", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(user));
    })

});
function getUserId() {
    var userId;
    if($.cookie('userId')!='' && $.cookie('userId')!=undefined)
    {
        return $.cookie('userId');
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            userId=JSON.parse(this.responseText);
            $.cookie('userId',userId);

        }
    };
    xhttp.open("GET", "/user-id", false);
    xhttp.send();
    return userId;

}