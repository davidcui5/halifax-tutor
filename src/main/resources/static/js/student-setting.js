'use strict';
var Doc;

$(document).ready(function () {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    if (token != null) {
        $.get({
            url: "https://csci5308group12devint.azurewebsites.net/check_account",
            data: JSON.stringify(objToken),
            contentType: "application/json",
            type: "GET",
            dataType: "text"
        }).done(function (succeed) {
            Doc = succeed;
            if (succeed === false) {
                // similar behavior as an HTTP redirect
                window.location.replace("https://csci5308group12devint.azurewebsites.net");
            }
        }).fail(function (xhr, status, errorThrown) {
            window.location.replace("https://csci5308group12devint.azurewebsites.net");
        });
    }
});