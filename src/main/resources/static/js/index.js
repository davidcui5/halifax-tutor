'use strict';

$(document).ready(function () {
    $("#user-login-form").submit(function (event) {
        event.preventDefault();

        if(!validateForm()){
            return;
        }

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
            alert(data.result);
            alert(data.message);
            if (data.result === "SUCCESS") {
                window.localStorage.setItem("token", data.token); //add access token to local storage
                window.location.replace(data.url);
            }
        }).fail(function (xhr, status, errorThrown) {
            //empty
        });
    });

});
