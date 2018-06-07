'use strict';

$(document).ready(function () {
    $("form").submit(function ( event ) {
        event.preventDefault();
        let email = $("#email").val();
        let password = $("#password").val();
        let phoneNumber = $("#phone-number").val();
        let firstName = $("#user-first-name").val();
        let lastName = $("#user-last-name").val();
        let creditCardNumber = $("#user-credit-card").val();
        let expireDate = $("#expire-date").val();
        let securityCode = $("#security-code").val();
        securityCode = parseInt(securityCode, 10);

        var tutorData = {
            "email": email,
            "password": password,
            "phoneNumber": phoneNumber,
            "firstName": firstName,
            "lastName": lastName,
            "creditCardNumber": creditCardNumber,
            "expireDate": expireDate,
            "securityCode": securityCode
        };

        $.ajax({
            url: "https://csci5308group12devint.azurewebsit/tutor",
            data: JSON.stringify(tutorData),
            type: "POST",
            contentType: "application/json",
            dataType: "text"
        }).done(function (text) {
            if (text === "registration success") {
                alert("Registration succeed!");
                window.location.replace("../index.html");
            } else {
                alert("Something went wrong: " + text);
            }

        }).fail(function (xhr, status, errorThrown) {

        });
    });
});
