'use strict';

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();
        let type = $("#type").val();
        let email = $("#email").val();
        let password = $("#password").val();

        var loginData = {
            "type": type,
            "email": email,
            "password": password
        };

        $.ajax({
            url: location.origin + "/login",
            data: JSON.stringify(loginData),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            if (data.result === "SUCCESS") {
                alert("Welcome Back!");
                window.localStorage.setItem("token", data.token); //add access token to local storage
                window.location.replace(data.url);
            } else {
                alert(data.detail);
            }
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });
});