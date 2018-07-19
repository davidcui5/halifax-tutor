'use strict';

$(document).ready(function () {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    $.ajax({
        url: location.origin + "/admin/setting/access",
        data: JSON.stringify(objToken),
        contentType: "application/json",
        type: "POST",
        dataType: "text"
    }).done(function (data) {
        if (data === SUCCESS) {
            //stay
        }
        else {
            alert("You do not have access to this page.");
            window.location.replace("../index.html");
        }
    }).fail(function (xhr, status, errorThrown) {

    });


});