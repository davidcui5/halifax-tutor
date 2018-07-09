'use strict';

$(document).ready(function () {
    $("form").submit(function (event) {
        event.preventDefault();
        let type = $("#type").val();
        let email = $("#email option:selected").text()
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
            type: "GET",
            dataType: "json"
        }).done(function (data) {
            if (data.result === "Success") {
                alert("Welcome Back!");
                window.sessionStorage.accessToken = data.token;
                window.location.replace(data.url);
            } else {
                alert(data.detail);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });
});